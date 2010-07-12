package command;

import flight.IFlightObject;

public class RotateCommand extends FlightObjectCommand
{
	private double _vDegrees;
	private double _aDegrees;
	
	public RotateCommand(double vD)
	{
		this(null, vD);
	}
	
	public RotateCommand(IFlightObject target, double vD)
	{
		this(target, vD, 0);
	}
	
	public RotateCommand(double vD, double aD)
	{
		this(null, vD, aD);
	}
	
	public RotateCommand(IFlightObject target, double vD, double aD)
	{
		super(target);
		_vDegrees = vD;
		_aDegrees = aD;
	}
	
	/**
	 * others
	 */
	public void update()
	{
		if (null != _target)
		{
			_vDegrees += _aDegrees;
			_target.setRotation(_target.getRotation() + _vDegrees);
		}
		
		super.update();
	}
}
