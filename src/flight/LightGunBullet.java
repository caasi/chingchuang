package flight;

import java.awt.Rectangle;
import game.ShootingGame;
import command.*;
import core.SpriteManager;

public class LightGunBullet extends FlightObject
{
	private FlightObjectCommandContainer _parallel;
	
	public LightGunBullet()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("b_lightgun"));
		// prepare rotate command
		_parallel = new ParallelCommands(this);
		super.setCommand(_parallel);
		FlightObjectCommand command = new RotateCommand(this, 15);
		_parallel.add(command);
		
		setHp(1);
		_damage = 10;
		_centerOffsetX = 8;
		_centerOffsetY = 8;
		_hitRect = new Rectangle(4, 4, 8, 8);
	}
	
	public void setCommand(FlightObjectCommand command)
	{
		_parallel.add(command);
		command.setTarget(this);
	}
	
	public void onHpZero()
	{
		super.onHpZero();
		IFlightObject fx = new GunBulletHit();
		fx.setRotation(getRotation());
		fx.setCenterX(getCenterX());
		fx.setCenterY(getCenterY());
		ShootingGame.getInstance().addFX(fx);
	}
}
