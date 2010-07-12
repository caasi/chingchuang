package command;

import flight.IFlightObject;

public class SeriesCommands extends FlightObjectCommandContainer
{
	private FlightObjectCommand _currentCommand;
	
	public SeriesCommands()
	{
		this(null);
	}
	
	public SeriesCommands(IFlightObject target)
	{
		super(target);
		_currentCommand = null;
		super.setLiveTime(0);
	}
	
	public void setLiveTime(int liveTime)
	{
		return;
	}
	
	public void add(FlightObjectCommand command)
	{
		if (!contains(command))
		{
			super.setLiveTime(getLiveTime() + command.getLiveTime());
			if (null == _currentCommand)
				_currentCommand = command;
		}
		// still pass it to super for error handling
		super.add(command);
	}
	
	public void remove(FlightObjectCommand command)
	{
		if (contains(command))
		{
			super.setLiveTime(getLiveTime() - command.getLiveTime());
			if (command == _currentCommand)
			{
				nextCommand();
			}
		}
		super.remove(command);
	}
	
	public void update()
	{
		if (null != _currentCommand)
		{
			_currentCommand.update();
			if (_currentCommand.isComplete())
			{
				nextCommand();
			}
		}
		
		super.update();
	}
	
	private void nextCommand()
	{
		int nextIndex = _commands.indexOf(_currentCommand) + 1;
		if (nextIndex < _commands.size())
			_currentCommand = _commands.get(nextIndex);
		else
			_currentCommand = null;
	}
}
