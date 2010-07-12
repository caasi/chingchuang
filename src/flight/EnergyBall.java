package flight;

import java.awt.Rectangle;
import game.ShootingGame;
import core.SpriteManager;

public class EnergyBall extends FlightObject
{
	public EnergyBall()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("b_energy"));
		setHp(1);
		_damage = 90;
		_centerOffsetX = 16;
		_centerOffsetY = 16;
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
