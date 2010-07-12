package command;

import flight.IFlightObject;

public class MoveCommand extends FlightObjectCommand
{
	protected double _vX;
	protected double _vY;
	private double _aX;
	private double _aY;
	
	public MoveCommand(double vX, double vY)
	{
		this(null, vX, vY);
	}
	
	public MoveCommand(IFlightObject target, double vX, double vY)
	{
		this(target, vX, vY, 0.0, 0.0);
	}
	
	public MoveCommand(double vX, double vY, double aX, double aY)
	{
		this(null, vX, vY, aX, aY);
	}
	
	public MoveCommand(IFlightObject target, double vX, double vY, double aX, double aY)
	{
		super(target);
		_vX = vX;
		_vY = vY;
		_aX = aX;
		_aY = aY;
	}
	
	/**
	 * others
	 */
	public void update()
	{
		if (null != _target)
		{
			_vX += _aX;
			_vY += _aY;
			_target.setX(_target.getX() + _vX);
			_target.setY(_target.getY() + _vY);
		}
		
		super.update();
	}
}
