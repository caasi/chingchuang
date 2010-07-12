package flight;

import java.awt.Rectangle;
import game.ShootingGame;
import core.SpriteManager;

public class GunBullet extends FlightObject
{
	public GunBullet()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("b_gun"));
		
		setHp(1);
		_damage = 10;
		_centerOffsetX = 8;
		_centerOffsetY = 8;
		_hitRect = new Rectangle(4, 4, 8, 8);
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
