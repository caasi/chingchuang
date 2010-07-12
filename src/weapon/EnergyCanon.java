package weapon;

import game.ShootingGame;
import command.MoveCommand;
import flight.EnergyBall;
import flight.IFlightObject;
import util.GamePoint;

public class EnergyCanon extends Weapon
{
	private int counter;
	private GamePoint _gun;
	private GamePoint _speed;
	
	public EnergyCanon()
	{
		super();
		_maxCoolDown = 30;
		_gun = new GamePoint(22, -1);
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
			bullet = new EnergyBall();
			bullet.setRotation(_parent.getRotation());
			bullet.setFaction(_parent.getFaction() + "-bullets");
			bullet.setCenterX(_parent.getCenterX() + p.getX());
			bullet.setCenterY(_parent.getCenterY() + p.getY());
			bullet.setLiveTime(120);
			GamePoint speed = new GamePoint(_speed);
			speed.rotate(radians);
			MoveCommand bulletMove = new MoveCommand(speed.getX(), speed.getY());
			bulletMove.setLiveTime(bullet.getLiveTime());
			bullet.setCommand(bulletMove);
			ShootingGame.getInstance().add(bullet);
			
			++counter;
		}
		super.fire();
	}
}
