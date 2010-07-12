package flight;

import game.ShootingGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import util.GameMath;
import util.LoggerManager;
import weapon.Weapon;
import command.FlightObjectCommand;
import core.GObject;
import core.Sprite;

public class FlightObject extends GObject implements IFlightObject
{	
	public interface Delegate
	{
		public void onCreated(IFlightObject fo);
		public void onHpChanged(IFlightObject fo);
		public void onHpZero(IFlightObject fo);
		public void onLiveTimeZero(IFlightObject fo);
	}
	
	private class NullDelegate implements Delegate
	{
		public void onCreated(IFlightObject fo) {}
		public void onHpChanged(IFlightObject fo) {}
		public void onHpZero(IFlightObject fo) {}
		public void onLiveTimeZero(IFlightObject fo) {}
	}
	
	private Delegate _delegate;
	protected Sprite _spt;
	protected double _radians;
	protected double _centerOffsetX;
	protected double _centerOffsetY;
	protected int _hp;
	protected int _maxHp;
	protected IFlightObject _target;
	protected String _faction;
	protected int _damage;
	protected int _liveTime;
	protected Rectangle _hitRect;
	protected FlightObjectCommand _command;
	protected Weapon _weapon;
	protected Weapon _altWeapon;
	protected Rectangle _boundRect;
	protected ArrayList<IFlightObject> _hitList;

	public FlightObject()
	{
		this(0.0, 0.0);
	}
	
	public FlightObject(double x, double y)
	{
		this(x, y, GameMath.LONG_ENOUGH);
	}
	
	public FlightObject(double x, double y, int liveTime)
	{
		super(x, y);
		_liveTime = liveTime;
		
		_delegate = new NullDelegate();
		_spt = null;
		_radians = 0.0;
		_centerOffsetX = 0.0;
		_centerOffsetY = 0.0;
		_maxHp = 100;
		_target = null;
		_faction = "";
		_damage = 0;
		// (0, 0, 0, 0) can't itersects with any rect
		_hitRect = new Rectangle(1, 1, 1, 1);
		_command = null;
		_weapon = null;
		_altWeapon = null;
		_hitList = new ArrayList<IFlightObject>();
		setHp(0);
		onCreated();
	}

	// overwritten GObject methods
	public void update()
	{	
		int i;
		int length = _hitList.size();
		int oldHp = _hp;
		for (i = 0; i < length; ++i)
		{
			oldHp -= _hitList.get(i).getDamage();
		}
		setHp(oldHp);
		if (_hp <= 0)
		{
			onHpZero();
			if (null != _parent)
				_parent.remove(this);
		}
		if (1 == _liveTime)
		{
			onLiveTimeZero();
			if (null != _parent)
				_parent.remove(this);
		}
		if (_liveTime > 0)
			--_liveTime;
		
		// update controll commands.
		// if update them first, onHpZero() and onLiveTimeZero() will be invoke in wrong time.
		if (null != _command && !_command.isComplete())
			_command.update();
		if (null != _weapon)
			_weapon.update();
		if (null != _altWeapon)
			_altWeapon.update();

		super.update();
	}

	public void draw(Graphics g)
	{
		if (_spt != null)
		{
			_spt.draw(g, (int)getGlobalX(), (int)getGlobalY(), (int)getRotation());
		}
		
		super.draw(g);
		
		// dirty, may find another way to tell all FlightObject in the future
		if (ShootingGame.getInstance().willDrawHitRect())
			drawHitRect(g);
			
		if (_spt != null)
			_spt.nextFrame();
	}
	// end of GObject methods
	
	public void drawHitRect(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.GREEN);
		Rectangle hitbox = getHitRect();
		hitbox.translate((int)getGlobalX(), (int)getGlobalY());
		g2d.fill(hitbox);
	}

	// methods in IFlightObject, also include overwritten methods which about affine matrix
	/**
	 * getters
	 */
	public Delegate getDelegate() {return _delegate;}
	public int getHp() {return _hp;}
	public int getMaxHp() {return _maxHp;}
	public IFlightObject getTarget() {return _target;}
	public String getFaction() {return _faction;}
	public double getRotation() {return GameMath.radiansToDegrees(_radians);}
	public double getRadians() {return _radians;}
	public double getCenterX() {return getX() + _centerOffsetX;}
	public double getCenterY() {return getY() + _centerOffsetY;}
	public double getGlobalCenterX() {return getGlobalX() + _centerOffsetX;}
	public double getGlobalCenterY() {return getGlobalY() + _centerOffsetY;}
	public int getLiveTime() {return _liveTime;}
	public int getDamage() {return _damage;}
	
	public Rectangle getHitRect()
	{
		Rectangle newRect = new Rectangle(_hitRect);
		return newRect;
	}
	
	/**
	 * setters
	 */
	public void setDelegate(Delegate delegate)
	{
		_delegate = delegate;
		if (null == _delegate)
			_delegate = new NullDelegate();
	}
	public void setHp(int hp)
	{
		if (_hp != hp)
		{
			_hp = hp;
			onHpChanged();
		}
	}
	public void setSprite(Sprite s)
	{
		_spt = s;
		setWidth(_spt.getWidth());
		setHeight(_spt.getHeight());
	}
	public void setTarget(IFlightObject fo) {_target = fo;}
	public void setFaction(String f) {_faction = f;}
	public void setX(double x) {super.setX(x);}
	public void setY(double y) {super.setY(y);}
	public void setRotation(double degrees)
	{
		degrees = (degrees + 360) % 360;
		_radians = GameMath.degreesToRadians(degrees);
		//_boundDirty = true;
	}
	public void setRadians(double radians) {_radians = radians;}
	public void setCenterX(double x) {setX(x - _centerOffsetX);}
	public void setCenterY(double y) {setY(y - _centerOffsetY);}
	public void setLiveTime(int time) {_liveTime = time;}
	public void setCommand(FlightObjectCommand command)
	{
		_command = command;
		if (null != command)
			command.setTarget(this);
	}
	public void setWeapon(Weapon w)
	{
		_weapon = w;
		if (null != w)
			w.setParent(this);
	}
	public void setAltWeapon(Weapon w)
	{
		_altWeapon = w;
		if (null != w)
			w.setParent(this);
	}
	
	/**
	 * others
	 */
	public void hit(IFlightObject target)
	{
		Rectangle rect = getHitRect();
		rect.translate((int)getGlobalX(), (int)getGlobalY());
		Rectangle targetRect = target.getHitRect();
		targetRect.translate((int)target.getGlobalX(), (int)target.getGlobalY());
		if (rect.intersects(targetRect))
		{
			LoggerManager.log(LoggerManager.INFO, "<IFlightObject> " + rect + " hit with " + targetRect + ".");
			addToHitList(target);
			target.addToHitList(this);
		}
	}
	
	public void clearHitList()
	{
		_hitList.removeAll(_hitList);
	}

	public void addToHitList(IFlightObject fo)
	{
		if (!_hitList.contains(fo))
		{
			_hitList.add(fo);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<FlightObject> FlightObject is already in hit list.");
		}
	}
	
	public void fire()
	{
		if (null != _weapon && _weapon.isCoolDownOver())
			_weapon.fire();
	}
	
	public void fireAlt()
	{
		if (null != _altWeapon && _altWeapon.isCoolDownOver())
			_altWeapon.fire();
	}

	// event method
	protected void onCreated()
	{
		LoggerManager.log(LoggerManager.INFO, "<FlightObject> FlightObject created.");
		_delegate.onCreated(this);
	}
	protected void onHpChanged()
	{
		LoggerManager.log(LoggerManager.INFO, "<FlightObject> FlightObject been hit.");
		_delegate.onHpChanged(this);
		//ShootingGame.getInstance().onFlightObjectHit(this);
	}
	protected void onHpZero()
	{
		LoggerManager.log(LoggerManager.INFO, "<FlightObject> FlightObject is dead.");
		_delegate.onHpZero(this);
		//ShootingGame.getInstance().onFlightObjectHit(this);
	}
	protected void onLiveTimeZero()
	{
		LoggerManager.log(LoggerManager.INFO, "<FlightObject> Remove instance because of timeout.");
		_delegate.onLiveTimeZero(this);
	}
}
