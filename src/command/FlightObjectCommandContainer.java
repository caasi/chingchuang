package command;

import java.util.ArrayList;
import util.LoggerManager;
import flight.IFlightObject;

public class FlightObjectCommandContainer extends FlightObjectCommand
{
	protected ArrayList<FlightObjectCommand> _commands;
	
	public FlightObjectCommandContainer()
	{
		this(null);
	}
	
	public FlightObjectCommandContainer(IFlightObject target)
	{
		super(target);
		_commands = new ArrayList<FlightObjectCommand>();
	}
	
	public boolean contains(FlightObjectCommand command)
	{
		return _commands.contains(command);
	}
	
	public void add(FlightObjectCommand command)
	{
		if (!contains(command))
		{
			_commands.add(command);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<FlightObjectCommandContainer> Command already exists.");
		}
	}
	
	public void remove(FlightObjectCommand command)
	{
		if (contains(command))
		{
			_commands.remove(command);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<FlightObjectCommandContainer> Command dosen't exist.");
		}
	}
}
