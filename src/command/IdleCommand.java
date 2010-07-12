package command;

import flight.IFlightObject;

public class IdleCommand extends FlightObjectCommand
{	
	public IdleCommand()
	{
		this(null);
	}
	
	public IdleCommand(IFlightObject target)
	{
		super(target);
	}
}
