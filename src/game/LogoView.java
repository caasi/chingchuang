package game;

import java.awt.Graphics;

import core.GObject;
import core.Sprite;
import core.SpriteManager;

public class LogoView extends GObject
{
	private Sprite _spt;
	
	public LogoView()
	{
		super();
		_spt = SpriteManager.getInstance().aCopyOf("ui_logo");
		setWidth(_spt.getWidth());
		setHeight(_spt.getHeight());
	}
	
	public void draw(Graphics g)
	{
		_spt.draw(g, (int)getGlobalX(), (int)getGlobalY(), 0);
		super.draw(g);
	}
}
