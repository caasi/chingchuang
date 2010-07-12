package command;

import flight.IFlightObject;

public class FireToTargetCommand extends FireCommand
{
	public FireToTargetCommand(int type)
	{
		this(null, type);
	}
	
	public FireToTargetCommand(IFlightObject target, int type)
	{
		super(target, type);
	}
	
	public void update()
	{
		if (_target != null)
		{
			IFlightObject pc = _target.getTarget();
			if (pc != null)
			{
				double radians = Math.atan2
				(
					pc.getCenterY() - _target.getCenterY(),
					pc.getCenterX() - _target.getCenterX()
				);
				_target.setRadians(radians);
			}
		}
		super.update();
	}
}
