package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Replay implements Serializable
{
	public static Replay read(String path)
	{
		Replay ret = null;
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			ret = (Replay)in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private long _startTime;
	private ArrayList<ControlData> _controlData;
	
	public Replay(long startTime)
	{
		_startTime = startTime;
		_controlData = new ArrayList<ControlData>();
	}
	
	public void add(ControlData data)
	{
		_controlData.add(data);
	}
	
	public ControlData get(int time)
	{
		ControlData data = null;
		Iterator<ControlData> itr = _controlData.iterator();
		while (itr.hasNext())
		{
			ControlData tmp = itr.next();
			if (tmp.getTime() == time)
			{
				data = tmp;
				itr.remove();
			}
		}
		
		return data;
	}
	
	public long getStartTime()
	{
		return _startTime;
	}
	
	public void setStartTime(long time)
	{
		_startTime = time;
	}
	
	public void write()
	{
		ObjectOutput out = null;
		//Date date = new Date(_startTime);
		try {
			out = new ObjectOutputStream(new FileOutputStream("replay/" + _startTime + ".sav"));
			out.writeObject(this);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
