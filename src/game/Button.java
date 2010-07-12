package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.GObject;

public class Button extends GObject
{
	private String _text;
	private boolean _pressed;
	private boolean _actived;
	
	public Button()
	{
		super();
		_text = "";
		_pressed = false;
		_actived = false;
	}
	
	public int getWidth() {return 12 * _text.length();} // dirty
	public String getText() {return _text;}
	public boolean isActived()
	{
		return _actived;
	}
	
	public void setText(String text) {_text = text;}
	public void setActived(boolean b) {_actived = b;}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		Color c;
		if (_pressed)
			c = new Color(0xFFFFFF);
		else if (_actived)
			c = new Color(0x06A4DF);
		else
			c = new Color(0x003B57);
		g2d.setColor(c);
		g2d.setFont(new Font("Arial Black", Font.PLAIN, 12));
		g2d.drawString(_text, (int)getGlobalX(), (int)getGlobalY());
		super.draw(g);
	}
	
	// events
	public void onPressed()
	{
		_pressed = true;
	}
	public void onReleased()
	{
		_pressed = false;
	}
}
