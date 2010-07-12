package flight;

import core.SpriteManager;

public class EnergyBallHit extends FlightObject
{
	public EnergyBallHit()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("fx_energy"));
		setLiveTime(_spt.getLength());
		setHp(1);
		_centerOffsetX = 16;
		_centerOffsetY = 16;
	}
}
