package game;

import java.io.Serializable;

public class ControlData implements Serializable {
	private int _time;
	private boolean _up;
	private boolean _down;
	private boolean _left;
	private boolean _right;
	private boolean _fire;
	private boolean _fireAlt;
	
	public ControlData
	(
		int time,
		boolean up,
		boolean down,
		boolean left,
		boolean right,
		boolean fire,
		boolean fireAlt
	)
	{
		_time = time;
		_up = up;
		_down = down;
		_left = left;
		_right = right;
		_fire = fire;
		_fireAlt = fireAlt;
	}
	
	public int getTime() {return _time;}
	public boolean isUp() {return _up;}
	public boolean isDown() {return _down;}
	public boolean isLeft() {return _left;}
	public boolean isRight() {return _right;}
	public boolean isFire() {return _fire;}
	public boolean isFireAlt() {return _fireAlt;}
}
