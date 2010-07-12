package flight;

import core.SpriteManager;

public class EnemyGunBulletHit extends FlightObject
{
	public EnemyGunBulletHit()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("fxe_gun"));
		setLiveTime(_spt.getLength());
		setHp(1);
		_centerOffsetX = 8;
		_centerOffsetY = 8;
	}
}
