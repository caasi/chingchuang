package command;

import flight.IFlightObject;

public class MoveTowardCommand extends MoveCommand
{
	public MoveTowardCommand(double vX, double vY)
	{
		this(null, vX, vY);
	}
	
	public MoveTowardCommand(IFlightObject target, double vX, double vY)
	{
		this(target, vX, vY, 0.0, 0.0);
	}
	
	public MoveTowardCommand(double vX, double vY, double aX, double aY)
	{
		this(null, vX, vY, aX, aY);
	}
	
	public MoveTowardCommand(IFlightObject target, double vX, double vY, double aX, double aY)
	{
		super(target, vX, vY, aX, aY);
	}
	
	/**
	 * others
	 */
	public void update()
	{
		if (_target != null)
		{
			_target.setRadians(Math.atan2(_vX, _vY));
		}
		
		super.update();
	}
}
