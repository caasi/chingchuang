package flight;

import core.SpriteManager;

public class Wall extends FlightObject
{
	public Wall()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("m_wall"));
		setHp(1);
		_centerOffsetX = 32;
		_centerOffsetY = 32;
	}
}
