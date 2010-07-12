package flight;

import core.SpriteManager;

public class GunBulletHit extends FlightObject
{
	public GunBulletHit()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("fx_gun"));
		setLiveTime(_spt.getLength());
		setHp(1);
		_centerOffsetX = 8;
		_centerOffsetY = 8;
	}
}
