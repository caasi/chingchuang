package game;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import core.ImageManager;
import core.Sprite;
import core.SpriteManager;

public class Credits extends Game
{
	public interface Delegate
	{
		public void onCreditsBack();
	}
	
	private class NullDelegate implements Delegate
	{
		public NullDelegate() {}
		public void onCreditsBack() {}
	}
	
	private Delegate _delegate;
	private Sprite _spt;
	
	public Credits()
	{
		super();
		_delegate = new NullDelegate();
		
		initImages();
		initSprites();
		
		_spt = SpriteManager.getInstance().aCopyOf("ui_credits");
		
		setWidth(480);
		setHeight(640);
		add(new MenuBackground());
		
		_keyHandler = new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				_delegate.onCreditsBack();
			}
		};
	}
	
	public void draw(Graphics g)
	{
		super.draw(g);
		_spt.draw(g, (int)getGlobalX() + 32, (int)getGlobalY() + 168, 0);
	}
	
	private void initImages()
	{
		ImageManager imgManager = ImageManager.getInstance();
		imgManager.load("back", "resource/background.png");
		imgManager.load("credits", "resource/credits.png");
	}
	
	private void initSprites()
	{
		ImageManager imgManager = ImageManager.getInstance();
		SpriteManager sManager = SpriteManager.getInstance();
		sManager.load
		(
			"back",
			imgManager.image("back"),
			480, 640,
			new int[] {0},
			false
		);
		sManager.load
		(
			"ui_credits",
			imgManager.image("credits"),
			247, 134,
			new int[] {0},
			false
		);
	}
	
	public void setDelegate(Delegate d)
	{
		_delegate = d;
		if (null == _delegate)
			_delegate = new NullDelegate();
	}
}
