package weapon;

import game.ShootingGame;
import command.MoveCommand;
import flight.EnemyGunBullet;
import flight.IFlightObject;
import util.GameMath;
import util.GamePoint;

public class CircleGun extends Weapon
{
	private GamePoint _speed;
	
	public CircleGun()
	{
		super();
		_maxCoolDown = 10;
		_speed = new GamePoint(2, 0);
	}
	
	/**
	 * others
	 */
	public void fire()
	{
		if (null != _parent)
		{	
			for (int i = 0; i < 12; ++i)
			{
				IFlightObject bullet;
				bullet = new EnemyGunBullet();
				bullet.setRotation(_parent.getRotation());
				bullet.setFaction(_parent.getFaction() + "-bullets");
				bullet.setCenterX(_parent.getCenterX());
				bullet.setCenterY(_parent.getCenterY());
				GamePoint speed = new GamePoint(_speed);
				speed.rotate(GameMath.degreesToRadians(_parent.getRotation() + 30 * i));
				MoveCommand bulletMove = new MoveCommand(speed.getX(), speed.getY());
				bullet.setCommand(bulletMove);
				ShootingGame.getInstance().add(bullet);
			}
		}
		super.fire();
	}
}
