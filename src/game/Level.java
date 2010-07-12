package game;

import java.util.ArrayList;
import java.util.Iterator;

import util.GamePoint;

public abstract class Level
{
	public static final GamePoint TOP1 = new GamePoint(0, -32);
	public static final GamePoint TOP2 = new GamePoint(120, -32);
	public static final GamePoint TOP3 = new GamePoint(240, -32);
	public static final GamePoint TOP4 = new GamePoint(360, -32);
	public static final GamePoint TOP5 = new GamePoint(480, -32);
	
	public static final GamePoint LEFT1 = new GamePoint(-32, 0);
	public static final GamePoint LEFT2 = new GamePoint(-32, 120);
	public static final GamePoint LEFT3 = new GamePoint(-32, 240);
	public static final GamePoint LEFT4 = new GamePoint(-32, 360);
	
	public static final GamePoint RIGHT1 = new GamePoint(512, 0);
	public static final GamePoint RIGHT2 = new GamePoint(512, 120);
	public static final GamePoint RIGHT3 = new GamePoint(512, 240);
	public static final GamePoint RIGHT4 = new GamePoint(512, 360);
	
	protected ArrayList<LevelData> _data;
	
	public Level()
	{
		_data = new ArrayList<LevelData>();
	}
	
	public ArrayList<LevelData> getData(int time)
	{
		ArrayList<LevelData> ret = new ArrayList<LevelData>();
		Iterator<LevelData> itr = _data.iterator();
		while (itr.hasNext())
		{
			LevelData ld = itr.next();
			if (ld.getTime() == time)
			{
				ret.add(ld);
			}
		}
		return ret;
	}
}