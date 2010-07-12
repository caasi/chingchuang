package flight;

import java.awt.Rectangle;
import game.ShootingGame;
import weapon.CircleGun;
import core.SpriteManager;

public class Enemy extends FlightObject
{
	public Enemy()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("f_multi"));
		setLiveTime(140);
		setWeapon(new CircleGun());
		
		// private vars
		_centerOffsetX = 16;
		_centerOffsetY = 16;
		// redonly vars
		_damage = 5;
		setHp(_damage);
		_hitRect = new Rectangle(12, 12, 8, 8);
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
