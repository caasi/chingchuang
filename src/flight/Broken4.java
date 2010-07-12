package flight;

import core.SpriteManager;

public class Broken4 extends FlightObject
{
	public Broken4()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("m_broken4"));
		setHp(1);
		_centerOffsetX = 32;
		_centerOffsetY = 32;
	}
}
