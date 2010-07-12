package game;

import flight.IFlightObject;

public class LevelData
{
	public static final int UNKNOWN = -1;
	public static final int ADD = 0;
	public static final int FX = 1;
	public static final int BACK = 2;
	public static final int END = 3;
	
	private int _time;
	private int _action;
	private IFlightObject _target;
	
	public LevelData()
	{
		_time = 0;
		_action = UNKNOWN;
		_target = null;
	}
	
	public int getTime() {return _time;}
	public int getAction() {return _action;}
	public IFlightObject getFlightObject() {return _target;}
	
	public void setTime(int time) {_time = time;}
	public void setAction(int action) {_action = action;}
	public void setTarget(IFlightObject fo) {_target = fo;}
}
