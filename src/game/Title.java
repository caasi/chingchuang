package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import core.IGObject;
import core.ImageManager;
import core.SpriteManager;

public class Title extends Game
{
	public interface Delegate
	{
		public void onStart();
		public void onReplay();
		public void onCredits();
		public void onExit();
	}
	
	private class NullDelegate implements Delegate
	{
		public NullDelegate() {};
		public void onStart() {};
		public void onReplay() {};
		public void onCredits() {};
		public void onExit() {};
	}
	
	private Delegate _delegate;
	private ButtonGroup _titleButtons;
	
	public Title()
	{
		super();
		_delegate = new NullDelegate();
		
		initImages();
		initSprites();
		
		setWidth(480);
		setHeight(640);
		add(new FarBackground());
		
		IGObject logo = new LogoView();
		logo.setX((480 - logo.getWidth()) / 2);
		logo.setY(64);
		add(logo);
		
		_titleButtons = new ButtonGroup();
		_titleButtons.setX(256);
		_titleButtons.setY(480);
		Button btn;
		btn = new Button()
		{
			public void onReleased()
			{
				super.onReleased();
				_delegate.onStart();
			}
		};
		btn.setText("START");
		_titleButtons.add(btn);

		btn = new Button()
		{
			public void onReleased()
			{
				super.onReleased();
				_delegate.onReplay();
			}
		};
		btn.setText("REPLAY");
		btn.setY(20);
		_titleButtons.add(btn);
		
		btn = new Button()
		{
			public void onReleased()
			{
				super.onReleased();
				_delegate.onCredits();
			}
		};
		btn.setText("CREDITS");
		btn.setY(40);
		_titleButtons.add(btn);
		
		btn = new Button(){
			public void onReleased()
			{
				super.onReleased();
				_delegate.onExit();
			}
		};
		btn.setText("EXIT");
		btn.setY(60);
		_titleButtons.add(btn);
		
		add(_titleButtons);
		
		_keyHandler = new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_Z:
					case KeyEvent.VK_ENTER:
						_titleButtons.currentButton().onPressed();
				}
			}
			
			public void keyReleased(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_UP:
					case KeyEvent.VK_LEFT:
						_titleButtons.prev();
						break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_RIGHT:
						_titleButtons.next();
						break;
					case KeyEvent.VK_Z:
					case KeyEvent.VK_ENTER:
						_titleButtons.currentButton().onReleased();
				}
			}
		};
	}
	
	private void initImages()
	{
		ImageManager imgManager = ImageManager.getInstance();
		imgManager.load("logo", "resource/logo.png");
		imgManager.load("back", "resource/background.png");
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
			"ui_logo",
			imgManager.image("logo"),
			192, 128,
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
