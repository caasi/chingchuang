package command;

import java.awt.Rectangle;

import util.GameMath;
import flight.IFlightObject;
import game.ControlData;
import game.Replay;
import game.ShootingGame;

public class ReplayCommand extends FlightObjectCommand
{	
	private Replay _replay;
	
	public ReplayCommand(String path)
	{
		this(null, path);
	}
	
	public ReplayCommand(IFlightObject target, String path)
	{
		super(target);
		super.setLiveTime(GameMath.FOREVER);
		
		_replay = Replay.read(path);
	}
	
	/**
	 * getters
	 */
	public long getReplayStartTime()
	{
		return _replay.getStartTime();
	}

	/**
	 * setters
	 */
	public void setLiveTime(int liveTime)
	{
		return;
	}
	
	/**
	 * others
	 */
	public void update()
	{	
		if (null != _target)
		{
			ControlData data = _replay.get(ShootingGame.getInstance().getTime());
			
			if (data != null)
			{
				ShootingGame game = ShootingGame.getInstance();
				Rectangle bound = game.getBoundRect();
				bound.translate((int)game.getGlobalX(), (int)game.getGlobalY());
				Rectangle hitRect = _target.getHitRect();
				hitRect.translate((int)_target.getGlobalX(), (int)_target.getGlobalY());
				double x = hitRect.getX();
				double y = hitRect.getY();
				double offsetX = 0;
				double offsetY = 0;
				if(data.isUp())
					offsetY -= 2;
				if(data.isDown())
					offsetY += 2;
				if(data.isLeft())
					offsetX -= 2;
				if(data.isRight())
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
				
				if (data.isFire())
					_target.fire();
				if (data.isFireAlt())
					_target.fireAlt();
			}
		}
		
		super.update();
	}
}
