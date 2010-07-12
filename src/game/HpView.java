package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.GObject;
import core.Sprite;
import core.SpriteManager;
import flight.IFlightObject;

public class HpView extends GObject
{
	private Sprite _spt;
	private double _percent;
	
	public HpView()
	{
		super();
		_spt = SpriteManager.getInstance().aCopyOf("ui_hp");
		setWidth(_spt.getWidth());
		setHeight(_spt.getHeight());
		_percent = 0.0;
	}
	
	public void draw(Graphics g)
	{
		_spt.draw(g, (int)getGlobalX(), (int)getGlobalY(), 0);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0x08A1DD));
		Rectangle hp = new Rectangle(18, 2, (int)(108 * _percent), 11);
		hp.translate((int)getGlobalX(), (int)getGlobalY());
		g2d.fill(hp);
		super.draw(g);
	}
	
	public double getPercent() {return _percent;}
	public void setPercent(double percent) {_percent = percent;}
}
