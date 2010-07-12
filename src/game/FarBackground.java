package game;

import java.awt.Graphics;

import core.GObject;
import core.Sprite;
import core.SpriteManager;

public class FarBackground extends GObject
{
	private Sprite _spt;
	
	public FarBackground()
	{
		super();
		_spt = SpriteManager.getInstance().aCopyOf("back");
	}
	
	public void draw(Graphics g)
	{
		_spt.draw(g, (int)getGlobalX(), (int)getGlobalY(), 0);
		super.draw(g);
	}
}
