package command;

import util.GameMath;
import util.LoggerManager;
import flight.IFlightObject;

public class FlightObjectCommand
{
	protected IFlightObject _target;
	private int _liveTime;
	
	public FlightObjectCommand()
	{
		this(null);
	}
	
	public FlightObjectCommand(IFlightObject target)
	{
		setTarget(target);
		_liveTime = GameMath.LONG_ENOUGH;
	}
	
	/**
	 * getters
	 */
	public int getLiveTime()
	{
		return _liveTime;
	}

	/**
	 * setters
	 */
	public void setLiveTime(int liveTime)
	{
		_liveTime = liveTime;
	}
	
	public void setTarget(IFlightObject target)
	{
		_target = target;
	}
	
	/**
	 * others
	 */
	public boolean isComplete()
	{
		return 0 == getLiveTime();
	}
	
	public void update()
	{
		int liveTime = getLiveTime();
		if (1 == liveTime)
			onComplete();
		if (liveTime > 0)
			--_liveTime;
	}
	
	// events
	public void onComplete()
	{
		LoggerManager.log(LoggerManager.INFO, "<FlightObjectCommand> Command completed.");
	}
}
