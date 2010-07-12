package weapon;

import flight.IFlightObject;

public class Weapon
{
	protected IFlightObject _parent;
	private int _coolDown;
	protected int _maxCoolDown;
	
	public Weapon()
	{
		_coolDown = 0;
		_maxCoolDown = 0;
	}
	
	/**
	 * getters
	 */
	
	/**
	 * setters
	 */
	public void setParent(IFlightObject fo)
	{
		_parent = fo;
	}
	
	/**
	 * others
	 */
	public boolean isCoolDownOver()
	{
		return 0 == _coolDown;
	}
	
	public void update()
	{
		if (_coolDown > 0)
			--_coolDown;
	}
	
	public void fire()
	{
		if (null == _parent)
			return;
		_coolDown = _maxCoolDown;
	}
}
