package util;

public class GamePoint
{
	private double _x;
	private double _y;
	private boolean _lengthDirty;
	private double _length;
	
	public GamePoint()
	{
		this(0, 0);
	}
	
	public GamePoint(double x, double y)
	{
		setX(x);
		setY(y);
	}
	
	public GamePoint(GamePoint point)
	{
		setX(point.getX());
		setY(point.getY());
	}
	
	// getters
	public double getX() {return _x;}
	public double getY() {return _y;}
	public double getLength()
	{
		if (_lengthDirty)
		{
			_length = Math.sqrt(_x * _x + _y * _y);
			_lengthDirty = false;
		}
		return _length;
	}
	
	//setters
	public void setX(double x)
	{
		_x = x;
		_lengthDirty = true;
	}
	public void setY(double y)
	{
		_y = y;
		_lengthDirty = true;
	}
	
	public void rotate(double theta)
	{
		double cosTheta = Math.cos(theta);
		double sinTheta = Math.sin(theta);
		double oldX = getX();
		double oldY = getY();
		setX((oldX * cosTheta - oldY * sinTheta));
		setY((oldX * sinTheta + oldY * cosTheta));
	}
}
