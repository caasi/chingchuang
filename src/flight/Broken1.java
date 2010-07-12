package flight;

import core.SpriteManager;

public class Broken1 extends FlightObject
{
	public Broken1()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("m_broken1"));
		setHp(1);
		_centerOffsetX = 16;
		_centerOffsetY = 16;
	}
}
