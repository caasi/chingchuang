package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.media.*;

import command.FlightObjectCommand;
import command.KeyboardCommand;
import command.MoveCommand;
import util.GameMath;
import util.LoggerManager;
import core.*;
import flight.*;

public class ShootingGame extends Game implements FlightObject.Delegate
{
	public interface Delegate
	{
		public void onGameOver();
	}
	
	private class NullDelegate implements Delegate
	{
		public NullDelegate() {};
		public void onGameOver() {};
	}
	
	// hard coded D:
	private static ShootingGame _instance = null;

	public static ShootingGame getInstance()
	{
		if (_instance == null)
		{
			_instance = new ShootingGame();
		}
		return _instance;
	}

	private Delegate _delegate;
	private ShootingGameData _data;
	private HashMap<String, Faction> _factions;
	private IGObject _back;
	private IGObject _nearBack;
	private IGObject _fx;
	private IGObject _ui;
	private IFlightObject _fighter;
	private int _lifeLeft;
	private int _gameOverCount;
	private int _score;
	private int _chain;
	
	// uis
	private HpView _hpView;
	private LifeView _lifeView;
	private ScoreView _scoreView;
	
	private boolean _pause;
	private boolean _drawHitRect;
	
	private Player _player;

	@SuppressWarnings("restriction")
	private ShootingGame()
	{
		super();
		setWidth(480);
		setHeight(640);
		_delegate = new NullDelegate();
		_data = null;
		_factions = null;
		_ui = null;
		_fighter = null;
		_lifeLeft = 0;
		_gameOverCount = 0;
		_score = 0;
		_chain = 0;
		_player = null;
		
		_pause = false;
		_drawHitRect = false;
		
		_keyHandler = new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_P:
						_pause = !_pause;
						if (_pause)
							if (_player != null)
								_player.stop();
						else
							if (_player != null)
								_player.start();
						break;
						// debug
					case KeyEvent.VK_M:
						setLife(10);
						break;
					case KeyEvent.VK_N:
						_drawHitRect = !_drawHitRect;
						break;
					case KeyEvent.VK_ESCAPE:
						break;
				}
			}
		};
	}
	
	private void initFighter()
	{
		// infact, all fighter should use same KeyboardCommand for recording control.
		_fighter = new Wagon();
		_fighter.setFaction("roc");
		_fighter.setCommand(_data.getCommand());
		_fighter.setRotation(-90);
		_fighter.setCenterX(_data.getInitPosition().getX());
		_fighter.setCenterY(_data.getInitPosition().getY());
		_fighter.setLiveTime(GameMath.FOREVER);
		add(_fighter);
		// how about also set _hpView as a delegate?
		_hpView.setPercent(1.0);
	}
	
	private void initUI()
	{
		_ui = new GObject();
		add(_ui);
		_scoreView = new ScoreView();
		_scoreView.setX((480 - _scoreView.getWidth()) / 2);
		_ui.add(_scoreView);
		_hpView = new HpView();
		_hpView.setY(640 - _hpView.getHeight());
		_hpView.setPercent(1.0);
		_ui.add(_hpView);
		_lifeView = new LifeView();
		_lifeView.setX(480 - _lifeView.getWidth());
		_lifeView.setY(640 - _lifeView.getHeight());
		_ui.add(_lifeView);
	}
	
	@SuppressWarnings("restriction")
	public void initWithData(ShootingGameData data)
	{
		_data = data;
		if (_data != null)
		{
			// JMF mp3 player
			/*
			File file = new File(_data.getMusic());
			
			try {
				_player = Manager.createRealizedPlayer(file.toURL());
			} catch (NoPlayerException e1) {
				// TODO Auto-generated catch block
				LoggerManager.log(LoggerManager.ERROR, "<ShootingGame> Can't open music.");
				e1.printStackTrace();
			} catch (CannotRealizeException e1) {
				// TODO Auto-generated catch block
				LoggerManager.log(LoggerManager.ERROR, "<ShootingGame> Can't open music.");
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				LoggerManager.log(LoggerManager.ERROR, "<ShootingGame> Can't open music.");
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				LoggerManager.log(LoggerManager.ERROR, "<ShootingGame> Can't open music.");
				e1.printStackTrace();
			}
			*/
			
			_timer = 0;
			_score = 0;
			_chain = 0;
			
			_children.removeAll(_children);
			
			remove(_back);
			remove(_nearBack);
			remove(_fx);
			
			_factions = new HashMap<String, Faction>();	
			_back = new FarBackground();
			add(_back);
			_nearBack = new NearBackground(2.0);
			add(_nearBack);
			_fx = new GObject();
			add(_fx);
			
			initUI();
			_fighter = null;
			setLife(_data.getLife());
		}
	}

	public void add(IFlightObject fo)
	{
		String faction = fo.getFaction();
		if (!_factions.containsKey(faction))
		{
			LoggerManager.log(LoggerManager.WARN, "<ShootingGame> Create new faction: " + faction + ".");
			Faction newFaction = new Faction();
			_factions.put(faction, newFaction);
			super.add(newFaction);
		}
		_factions.get(faction).add(fo);
		
		// add fighter as target to enemy
		if ("enemy" == faction)
			fo.setTarget(_fighter);
		
		// set delegate
		fo.setDelegate(this);
	}

	public void remove(IFlightObject fo)
	{
		String faction = fo.getFaction();
		if (_factions.containsKey(faction))
		{
			_factions.get(faction).remove(fo);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<ShootingGame> Remove FlightObject from unknow faction: " + faction + ".");
		}
	}
	
	public void addFX(IFlightObject fx)
	{
		_fx.add(fx);
	}
	
	public void addBack(IFlightObject fo)
	{
		_nearBack.add(fo);
	}
	
	@SuppressWarnings("restriction")
	public void update()
	{
		if (_pause)
			return;
		/*
		if (0 == _timer)
			_player.start();
		*/
		// do hit tests between factions
		int i;
		Collection<Faction> factionCollection = _factions.values();
		Faction[] factionArray = factionCollection.toArray(new Faction[0]);
		int length = factionArray.length;
		for (i = 0; i < length; ++i)
		{
			factionArray[i].clearHitLists();
		}
		
		Faction faction;
		faction = _factions.get("roc");
		if (null != faction)
		{
			faction.hitTest(_factions.get("enemy"));
			faction.hitTest(_factions.get("enemy-bullets"));
		}
		faction = _factions.get("enemy");
		if (null != faction)
			faction.hitTest(_factions.get("roc-bullets"));
		// end of hit tests

		ArrayList<LevelData> data = _data.getLevel().getData(_timer);
		Iterator<LevelData> itr = data.iterator();
		while (itr.hasNext())
		{
			LevelData ld = itr.next();
			switch(ld.getAction())
			{
				case LevelData.ADD:
					add(ld.getFlightObject());
					break;
				case LevelData.FX:
					addFX(ld.getFlightObject());
					break;
				case LevelData.BACK:
					addBack(ld.getFlightObject());
					break;
				case LevelData.END:
					FlightObjectCommand command = null;
					command = _data.getCommand();
					if (command instanceof KeyboardCommand)
						((KeyboardCommand)_data.getCommand()).saveReplay();
					if (_fighter != null)
					{
						_fighter.setLiveTime(360);
						FlightObjectCommand gameOverMove = new MoveCommand(0, 0, 0, -0.5)
						{
							public void onComplete()
							{
								super.onComplete();
								_delegate.onGameOver();
							}
						};
						gameOverMove.setLiveTime(360);
						_fighter.setCommand(gameOverMove);
					}
					break;
				case LevelData.UNKNOWN:
				default:
			}
		}
		
		if (120 == _gameOverCount)
		{
			if (_player != null)
				_player.stop();
			_delegate.onGameOver();
		}
		if (_gameOverCount != 0)
			++_gameOverCount;
		
		// make sure _fx on the top
		add(_fx);
		add(_ui);

		super.update();
	}
	
	public void draw(Graphics g)
	{
		// clip graphics
		Graphics newG = g.create((int)getX(), (int)getY(), getWidth(), getHeight());
		newG.translate(-(int)getX(), -(int)getY());
		
		super.draw(newG);
		
		newG.dispose();
	}

	// Provide useful information for pc and npc
	public int getLife() {return _lifeLeft;}
	public int getScore() {return _score;}
	//public IFlightObject getPC() {return _fighter;}
	public Rectangle getBoundRect()
	{
		return new Rectangle(0, 0, getWidth(), getHeight());
	}
	public boolean willDrawHitRect() {return _drawHitRect;}
	
	// setters
	public void setDelegate(Delegate d)
	{
		_delegate = d;
		if (null == _delegate)
			_delegate = new NullDelegate();
	}
	
	private void resetFighter()
	{
		if (_fighter != null)
			return;
		if (_lifeLeft > 0)
		{
			initFighter();
			--_lifeLeft;
			_lifeView.setLife(_lifeLeft);
		}
		else
		{
			_gameOverCount = 1;
		}
	}
	
	public void setLife(int life)
	{
		_lifeLeft = life;
		_lifeView.setLife(_lifeLeft);
		resetFighter();
	}
	
	public void setScore(int score)
	{
		_score = score;
		_scoreView.setScore(score);
	}
	
	// FlightObject.Delegate
	public void onCreated(IFlightObject fo)
	{
		
	}
	
	public void onHpChanged(IFlightObject fo)
	{
		if (fo == _fighter)
		{
			_chain = 0;
			_hpView.setPercent(_fighter.getHp() / (double)_fighter.getMaxHp());
		}
	}
	
	public void  onHpZero(IFlightObject fo)
	{
		if (fo.getFaction() == "enemy")
		{
			++_chain;
			setScore(getScore() + 10 * _chain);
		}
		else if (fo == _fighter)
		{
			_fighter = null;
			resetFighter();
		}
	}
	
	public void onLiveTimeZero(IFlightObject fo)
	{
		
	}
}
