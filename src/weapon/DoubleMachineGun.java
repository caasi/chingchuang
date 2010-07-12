package weapon;

import game.ShootingGame;
import command.MoveCommand;
import flight.LightGunBullet;
import flight.IFlightObject;
import util.GamePoint;

public class DoubleMachineGun extends Weapon
{
	private GamePoint _gun1;
	private GamePoint _gun2;
	private GamePoint _speed;
	
	public DoubleMachineGun()
	{
		super();
		_maxCoolDown = 4;
		_gun1 = new GamePoint(16, -7);
		_gun2 = new GamePoint(16, 7);
		_speed = new GamePoint(10, 0);
	}
	
	/**
	 * others
	 */
	public void fire()
	{
		if (null != _parent)
		{	
			GamePoint p = new GamePoint(_gun1);
			double radians = _parent.getRadians();
			p.rotate(radians);
			IFlightObject bullet;
			bullet = new LightGunBullet();
			bullet.setRotation(_parent.getRotation());
			bullet.setFaction(_parent.getFaction() + "-bullets");
			// buggy, getRelativeX is better
			bullet.setCenterX(_parent.getCenterX() + p.getX());
			bullet.setCenterY(_parent.getCenterY() + p.getY());
			bullet.setLiveTime(120);
			GamePoint speed = new GamePoint(_speed);
			speed.rotate(radians);
			MoveCommand bulletMove = new MoveCommand(speed.getX(), speed.getY());
			bulletMove.setLiveTime(bullet.getLiveTime());
			bullet.setCommand(bulletMove);
			ShootingGame.getInstance().add(bullet);
			
			p = new GamePoint(_gun2);
			radians = _parent.getRadians();
			p.rotate(radians);
			bullet = new LightGunBullet();
			bullet.setRotation(_parent.getRotation());
			bullet.setFaction(_parent.getFaction() + "-bullets");
			bullet.setCenterX(_parent.getCenterX() + p.getX());
			bullet.setCenterY(_parent.getCenterY() + p.getY());
			bullet.setLiveTime(120);
			speed = new GamePoint(_speed);
			speed.rotate(radians);
			bulletMove = new MoveCommand(speed.getX(), speed.getY());
			bulletMove.setLiveTime(bullet.getLiveTime());
			bullet.setCommand(bulletMove);
			ShootingGame.getInstance().add(bullet);
		}
		super.fire();
	}
}
