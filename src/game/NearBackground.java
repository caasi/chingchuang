package game;

import core.GObject;
import core.IGObject;

public class NearBackground extends GObject
{
	private double _speed;
	
	public NearBackground(double speed)
	{
		super();
		_speed = speed;
	}
	
	public void update()
	{
		super.update();
		for (IGObject go : _children)
		{
			double y = go.getY();
			go.setY(y + _speed);
		}
	}
}
