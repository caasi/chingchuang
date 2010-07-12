package flight;

import java.awt.Rectangle;

import game.ShootingGame;
import weapon.EnemyMachineGun;
import weapon.EnergyCanon;
import core.SpriteManager;

public class Blade extends FlightObject
{
	public Blade()
	{
		super();
		// vars with setter
		setSprite(SpriteManager.getInstance().aCopyOf("f_blade"));
		setWeapon(new EnemyMachineGun());
		setAltWeapon(new EnergyCanon());
		// no setter or getter, real internal vars
		_centerOffsetX = 16;
		_centerOffsetY = 16;
		// readonly hitrect
		setHp(10);
		_damage = 100;
		_hitRect = new Rectangle(8, 8, 16, 16);
	}
	
	protected void onHpZero()
	{
		super.onHpZero();
		IFlightObject fx = new EnergyBallHit();
		fx.setCenterX(getCenterX());
		fx.setCenterY(getCenterY());
		ShootingGame.getInstance().addFX(fx);		
	}
}
