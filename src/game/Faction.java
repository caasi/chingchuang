package game;

import core.GObject;
import core.IGObject;
import flight.IFlightObject;

public class Faction extends GObject
{
	public Faction()
	{
		super();
	}

	public IFlightObject get(int index)
	{
		IGObject ret = _children.get(index);
		if (ret instanceof IFlightObject)
			return (IFlightObject)ret;
		return null;
	}
	
	public int size()
	{
		return _children.size();
	}

	public void clearHitLists()
	{
		int i;
		int length = size();
		for (i = 0; i < length; ++i)
		{
			IFlightObject fo = get(i);
			if (null != fo)
				fo.clearHitList();
		}
	}

	public void hitTest()
	{
		int i, j;
		int length = _children.size();
		for (i = 0; i < length; ++i)
		{
			for (j = i + 1; j < length; ++j)
			{
				get(i).hit(get(j));
			}
		}
	}
	
	public void hitTest(Faction anotherFaction)
	{
		if (null == anotherFaction)
			return;
		int i, j;
		int length = _children.size();
		for (i = 0; i < length; ++i)
		{
			int targetLength = anotherFaction.size();
			for (j = 0; j < targetLength; ++j)
			{
				get(i).hit(anotherFaction.get(j));
			}
		}
	}
}
