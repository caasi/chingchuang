package flight;

import java.awt.Rectangle;

import core.SpriteManager;

public class Building extends FlightObject
{
	public Building()
	{
		super();
		setSprite(SpriteManager.getInstance().aCopyOf("m_building"));
		_damage = 6666;
		setHp(_damage);
		_centerOffsetX = 32;
		_centerOffsetY = 32;
		_hitRect = new Rectangle(0, 0, 64, 64);
	}
}
