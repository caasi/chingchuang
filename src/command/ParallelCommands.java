package command;

import flight.IFlightObject;

public class ParallelCommands extends FlightObjectCommandContainer
{
	public ParallelCommands()
	{
		this(null);
	}
	
	public ParallelCommands(IFlightObject target)
	{
		super(target);
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
			int liveTime = getLiveTime();
			int newLiveTime = command.getLiveTime();
			if (newLiveTime > liveTime)
				super.setLiveTime(newLiveTime);
		}
		// still pass it to super for error handling
		super.add(command);
	}
	
	public void remove(FlightObjectCommand command)
	{
		if (contains(command))
		{
			int liveTime = getLiveTime();
			int removedLiveTime = command.getLiveTime();
			if (removedLiveTime == liveTime)
			{
				int maxLiveTime = 0;
				int length = _commands.size();
				// find new livetime
				for (int i = 0; i < length; ++i)
				{
					int newLiveTime = _commands.get(i).getLiveTime();
					if (newLiveTime > maxLiveTime)
						maxLiveTime = newLiveTime;
				}
				setLiveTime(maxLiveTime);
			}
		}
		super.remove(command);
	}
	
	public void update()
	{
		int length = _commands.size();
		for (int i = 0; i < length; ++i)
		{
			_commands.get(i).update();
		}
		
		super.update();
	}
}
