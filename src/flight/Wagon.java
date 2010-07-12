package flight;

import java.awt.Rectangle;
import game.ShootingGame;
import weapon.MachineGun;
import core.SpriteManager;

public class Wagon extends FlightObject
{
	public Wagon()
	{
		super();
		// vars with setter
		setSprite(SpriteManager.getInstance().aCopyOf("f_wagon"));
		setWeapon(new MachineGun());
		// no setter or getter, real internal vars
		_centerOffsetX = 16;
		_centerOffsetY = 16;
		// readonly hitrect
		_maxHp = 30;
		setHp(_maxHp);
		_damage = 1000;
		_hitRect = new Rectangle(15, 3, 3, 3);
	}
	
	public void onHpZero()
	{
		super.onHpZero();
		IFlightObject fx = new EnergyBallHit();
		fx.setCenterX(getCenterX());
		fx.setCenterY(getCenterY());
		ShootingGame.getInstance().addFX(fx);
	}
}
