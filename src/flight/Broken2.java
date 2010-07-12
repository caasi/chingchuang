package flight;

import core.SpriteManager;

public class Broken2 extends FlightObject
{
	public Broken2()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("m_broken2"));
		setHp(1);
		_centerOffsetX = 16;
		_centerOffsetY = 16;
	}
}
