package command;

import flight.IFlightObject;

public class PolarMoveCommand extends FlightObjectCommand
{
	protected double _vRadians;
	private double _vLength;
	private double _aRadians;
	private double _aLength;
	
	public PolarMoveCommand(double vR, double vL)
	{
		this(null, vR, vL);
	}
	
	public PolarMoveCommand(IFlightObject target, double vR, double vL)
	{
		this(target, vR, vL, 0.0, 0.0);
	}
	
	public PolarMoveCommand(double vR, double vL, double aR, double aL)
	{
		this(null, vR, vL, aR, aL);
	}
	
	public PolarMoveCommand(IFlightObject target, double vR, double vL, double aR, double aL)
	{
		super(target);
		_vRadians = vR;
		_vLength = vL;
		_aRadians = aR;
		_aLength = aL;
	}
	
	/**
	 * others
	 */
	public void update()
	{
		if (null != _target)
		{
			double vX, vY, aX, aY;
			// a lot of sin & cos
			vX = _vLength * Math.cos(_vRadians);
			vY = _vLength * Math.sin(_vRadians);
			aX = _aLength * Math.cos(_aRadians);
			aY = _aLength * Math.sin(_aRadians);
			vX += aX;
			vY += aY;
			_target.setX(_target.getX() + vX);
			_target.setY(_target.getY() + vY);
			_vRadians = Math.atan2(vY, vX);
			_vLength = Math.sqrt(vX * vX + vY * vY);
		}
		
		super.update();
	}
}
