package flight;

import core.SpriteManager;

public class Broken3 extends FlightObject
{
	public Broken3()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("m_broken3"));
		setHp(1);
		_centerOffsetX = 16;
		_centerOffsetY = 16;
	}
}
