package game;

import java.awt.Graphics;

import core.GObject;
import core.Sprite;
import core.SpriteManager;

public class LifeView extends GObject
{
	private Sprite _ship;
	private Sprite _x;
	private int _life;
	
	public LifeView()
	{
		super();
		_ship = SpriteManager.getInstance().aCopyOf("ui_ship");
		_x = SpriteManager.getInstance().aCopyOf("ui_x");
		setWidth(_ship.getWidth() + 24);
		setHeight(_ship.getHeight());
		_life = 0;
	}
	
	public void draw(Graphics g)
	{
		_ship.draw(g, (int)getGlobalX(), (int)getGlobalY(), 0);
		_x.draw(g, (int)getGlobalX() + 12, (int)getGlobalY(), 0);
		Sprite spt = SpriteManager.getInstance().aCopyOf("ui_" + _life % 10);
		spt.draw(g, (int)getGlobalX() + 24, (int)getGlobalY(), 0);
		
		super.draw(g);
	}
	
	public int getLife() {return _life;}
	public void setLife(int life)
	{
		_life = life;
		if (_life < 0)
			_life = 0;
	}
}
