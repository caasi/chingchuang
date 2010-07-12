package game;

import java.awt.Graphics;

import core.GObject;
import core.Sprite;
import core.SpriteManager;

public class ScoreView extends GObject
{
	private int _score;
	
	public ScoreView()
	{
		super();
		setWidth(6 * 12);
		setHeight(16);
	}
	
	public void draw(Graphics g)
	{
		int score = _score;
		int x = (int)getGlobalX();
		int y = (int)getGlobalY();
		for (int i = 5; i >= 0; --i)
		{
			int rem = score % 10;
			Sprite spt = SpriteManager.getInstance().aCopyOf("ui_" + rem);
			spt.draw(g, x + 12 * i, y, 0);
			score /= 10;
		}
		
		super.draw(g);
	}
	
	public int getScore() {return _score;}
	public void setScore(int score) {_score = score;}
}
