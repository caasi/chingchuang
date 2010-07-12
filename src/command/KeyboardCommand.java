package command;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import util.GameMath;
import flight.IFlightObject;
import game.ControlData;
import game.Replay;
import game.ShootingGame;

public class KeyboardCommand extends FlightObjectCommand
{
	private KeyAdapter _keyHandler;
	private boolean _up;
	private boolean _down;
	private boolean _left;
	private boolean _right;
	private boolean _fire1;
	private boolean _fire2;
	
	private Replay _replay;
	
	public KeyboardCommand()
	{
		this(null);
	}
	
	public KeyboardCommand(IFlightObject target)
	{
		super(target);
		super.setLiveTime(GameMath.FOREVER);
		
		_up = false;
		_down = false;
		_left = false;
		_right = false;
		_fire1 = false;
		_fire2 = false;
		_replay = null;
		
		_keyHandler = new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						_up = true;
						break;
					case KeyEvent.VK_DOWN:
						_down = true;
						break;
					case KeyEvent.VK_LEFT:
						_left = true;
						break;
					case KeyEvent.VK_RIGHT:
						_right = true;
						break;
					case KeyEvent.VK_Z:
						_fire1 = true;
						break;
					case KeyEvent.VK_X:
						_fire2 = true;
						break;
				}
			}

			public void keyReleased(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						_up = false;
						break;
					case KeyEvent.VK_DOWN:
						_down = false;
						break;
					case KeyEvent.VK_LEFT:
						_left = false;
						break;
					case KeyEvent.VK_RIGHT:
						_right = false;
						break;
					case KeyEvent.VK_Z:
						_fire1 = false;
						break;
					case KeyEvent.VK_X:
						_fire2 = false;
						break;
				}
			}
		};
	}
	
	/**
	 * getters
	 */
	public KeyAdapter getKeyHandler()
	{
		return _keyHandler;
	}

	/**
	 * setters
	 */
	public void setLiveTime(int liveTime)
	{
		return;
	}
	public void setReplay(Replay replay)
	{
		_replay = replay;
	}
	
	/**
	 * others
	 */
	public void saveReplay()
	{
		if(_replay != null)
			_replay.write();
	}
	
	private void saveControlData()
	{
		if (!_up && !_down && !_left && !_right && !_fire1 && !_fire2)
			return;
		ControlData data = new ControlData
		(
			ShootingGame.getInstance().getTime(),
			_up, _down, _left, _right, _fire1, _fire2
		);
		if (_replay != null)
			_replay.add(data);
	}
	
	public void update()
	{	
		if (null != _target)
		{
			saveControlData();
			
			ShootingGame game = ShootingGame.getInstance();
			Rectangle bound = game.getBoundRect();
			bound.translate((int)game.getGlobalX(), (int)game.getGlobalY());
			Rectangle hitRect = _target.getHitRect();
			hitRect.translate((int)_target.getGlobalX(), (int)_target.getGlobalY());
			double x = hitRect.getX();
			double y = hitRect.getY();
			double offsetX = 0;
			double offsetY = 0;
			if(_up)
				offsetY -= 2;
			if(_down)
				offsetY += 2;
			if(_left)
				offsetX -= 2;
			if(_right)
				offsetX += 2;
			// normalize
			if (offsetX != 0 || offsetY != 0)
			{
				double speed = 5.0;
				double radians = Math.atan2(offsetY, offsetX);
				offsetX = speed * Math.cos(radians);
				offsetY = speed * Math.sin(radians);

				// test and recovery
				hitRect.setLocation((int)x, (int)(y + offsetY));
				if (!hitRect.intersects(bound))
				{
					offsetY = 0;
				}
				hitRect.setLocation((int)(x + offsetX), (int)y);
				if (!hitRect.intersects(bound))
				{
					offsetX = 0;
				}
				
				_target.setX(_target.getX() + offsetX);
				_target.setY(_target.getY() + offsetY);
			}
			
			if (_fire1)
				_target.fire();
			if (_fire2)
				_target.fireAlt();
		}
		
		super.update();
	}
}
