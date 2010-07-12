import core.GameEngine;
import game.Credits;
import game.Level;
import game.Level1;
import game.Replay;
import game.ReplayMenu;
import game.ShootingGame;
import game.ShootingGameData;
import game.Title;

import javax.swing.SwingUtilities;

import util.GameMath;

import command.FlightObjectCommand;
import command.KeyboardCommand;
import command.ReplayCommand;

public class ChingChuang extends GameEngine implements Title.Delegate, ShootingGame.Delegate, ReplayMenu.Delegate, Credits.Delegate
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ChingChuang();
			}
		});
	}
	
	private Title _title;
	private ReplayMenu _replayMenu;
	private Credits _credits;
	private ShootingGameData _data;

	public ChingChuang()
	{
		super(60, 1280, 1024);
		_title = new Title();
		_title.setDelegate(this);
		_replayMenu = new ReplayMenu();
		_replayMenu.setDelegate(this);
		_credits = new Credits();
		_credits.setDelegate(this);
		_data = new ShootingGameData();
		_data.setMusic("");
		_data.setLife(3);
		_data.setInitPosition(480 / 2, 640 - 32);
		
		ShootingGame.getInstance().setDelegate(this);
		
		setKeyHandler(_title.getKeyHandler());
		setGame(_title);
		start();
	}
	
	// Title.Delegate
	public void onStart()
	{
		// prepare random seed
		long time = System.currentTimeMillis();
		GameMath.setRandSeed(time);
		Replay replay = new Replay(time);
		
		// create level, need random seed
		Level level = new Level1();
		_data.setLevel(level);
		
		// create KeyboardCommand, it will log keyboard by Replay
		KeyboardCommand newCommand = new KeyboardCommand();
		newCommand.setReplay(replay);
		_data.setCommand(newCommand);
		
		// init game with those data
		ShootingGame.getInstance().initWithData(_data);		
		removeKeyHandler(_title.getKeyHandler());
		setKeyHandler(newCommand.getKeyHandler());
		setKeyHandler(ShootingGame.getInstance().getKeyHandler());
		setGame(ShootingGame.getInstance());
	}
	
	public void onReplay()
	{	
		_replayMenu.loadReplays();
		removeKeyHandler(_title.getKeyHandler());
		setKeyHandler(_replayMenu.getKeyHandler());
		setGame(_replayMenu);
	}
	
	public void onCredits()
	{
		removeKeyHandler(_title.getKeyHandler());
		setKeyHandler(_credits.getKeyHandler());
		setGame(_credits);
	}
	
	public void onExit()
	{
		System.exit(0);
	}
	
	// ReplayMenu.Delegate
	public void onReplaySelected(String _path)
	{
		// load Replay by ReplayCommand
		ReplayCommand newCommand = new ReplayCommand(_path);
		_data.setCommand(newCommand);
		// prepare random seed
		long time = newCommand.getReplayStartTime();
		GameMath.setRandSeed(time);
		
		// create level
		Level level = new Level1();
		_data.setLevel(level);
		
		// init game
		ShootingGame.getInstance().initWithData(_data);
		removeKeyHandler(_replayMenu.getKeyHandler());
		setKeyHandler(ShootingGame.getInstance().getKeyHandler());
		setGame(ShootingGame.getInstance());
	}
	
	public void onBack()
	{
		removeKeyHandler(_replayMenu.getKeyHandler());
		setKeyHandler(_title.getKeyHandler());
		setGame(_title);
	}
	
	// Credits.Delegate
	public void onCreditsBack()
	{
		removeKeyHandler(_credits.getKeyHandler());
		setKeyHandler(_title.getKeyHandler());
		setGame(_title);
	}
	
	// ShootingGame.Delegate
	public void onGameOver()
	{
		FlightObjectCommand command = _data.getCommand();
		if (command instanceof KeyboardCommand)
			removeKeyHandler(((KeyboardCommand)command).getKeyHandler());
		removeKeyHandler(ShootingGame.getInstance().getKeyHandler());
		setKeyHandler(_title.getKeyHandler());
		setGame(_title);
	}
}
