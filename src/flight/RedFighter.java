package flight;

import java.awt.Rectangle;
import game.ShootingGame;
import weapon.DoubleMachineGun;
import weapon.EnergyCanon;
import core.SpriteManager;

public class RedFighter extends FlightObject
{
	public RedFighter()
	{
		super();
		// vars with setter
		setSprite(SpriteManager.getInstance().aCopyOf("f_redfighter"));
		setWeapon(new DoubleMachineGun());
		setAltWeapon(new EnergyCanon());
		// no setter or getter, real internal vars
		_centerOffsetX = 16;
		_centerOffsetY = 16;
		// readonly hitrect
		setHp(10);
		_damage = 100;
		_hitRect = new Rectangle(8, 8, 16, 16);
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
