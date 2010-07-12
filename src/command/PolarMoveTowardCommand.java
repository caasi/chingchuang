package command;

import flight.IFlightObject;

public class PolarMoveTowardCommand extends PolarMoveCommand
{
	public PolarMoveTowardCommand(double vR, double vL)
	{
		this(null, vR, vL);
	}
	
	public PolarMoveTowardCommand(IFlightObject target, double vR, double vL)
	{
		this(target, vR, vL, 0.0, 0.0);
	}
	
	public PolarMoveTowardCommand(double vR, double vL, double aR, double aL)
	{
		this(null, vR, vL, aR, aL);
	}
	
	public PolarMoveTowardCommand(IFlightObject target, double vR, double vL, double aR, double aL)
	{
		super(target, vR, vL, aR, aL);
	}
	
	/**
	 * others
	 */
	public void update()
	{
		if (null != _target)
		{
			_target.setRadians(_vRadians);
		}
		
		super.update();
	}
}
