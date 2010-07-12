package weapon;

import game.ShootingGame;
import command.MoveCommand;
import flight.HeavyGunBullet;
import flight.IFlightObject;
import util.GamePoint;

public class MachineGun extends Weapon
{
	private GamePoint _gun;
	private GamePoint _speed;
	
	public MachineGun()
	{
		super();
		_maxCoolDown = 2;
		_gun = new GamePoint(20, 0);
		_speed = new GamePoint(10, 0);
	}
	
	/**
	 * others
	 */
	public void fire()
	{
		if (null != _parent)
		{	
			GamePoint p = new GamePoint(_gun);
			double radians = _parent.getRadians();
			p.rotate(radians);
			IFlightObject bullet;
			bullet = new HeavyGunBullet();
			bullet.setRotation(_parent.getRotation());
			bullet.setFaction(_parent.getFaction() + "-bullets");
			bullet.setCenterX(_parent.getCenterX() + p.getX());
			bullet.setCenterY(_parent.getCenterY() + p.getY());
			GamePoint speed = new GamePoint(_speed);
			speed.rotate(radians);
			MoveCommand bulletMove = new MoveCommand(speed.getX(), speed.getY());
			bullet.setCommand(bulletMove);
			ShootingGame.getInstance().add(bullet);
		}
		super.fire();
	}
}
