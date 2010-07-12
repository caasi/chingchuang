package command;

import flight.IFlightObject;

public class FireCommand extends FlightObjectCommand
{
	public static int MAIN = 1 << 0;
	public static int ALT = 1 << 1;
	
	private int _type;
	
	public FireCommand(int type)
	{
		this(null, type);
	}
	
	public FireCommand(IFlightObject target, int type)
	{
		super(target);
		_type = type;
	}
	
	public void update()
	{
		if (_target != null)
		{
			if ((_type & MAIN) == MAIN)
				_target.fire();
			if ((_type & ALT) == ALT)
				_target.fireAlt();
		}
		super.update();
	}
}
