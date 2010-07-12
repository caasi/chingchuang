package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

import core.IGObject;
import core.ImageManager;
import core.SpriteManager;

public class ReplayMenu extends Game
{
	public interface Delegate
	{
		public void onReplaySelected(String path);
		public void onBack();
	}
	
	private class NullDelegate implements Delegate
	{
		public NullDelegate() {}
		public void onReplaySelected(String path) {}
		public void onBack() {}
	}
	
	private class ReplayButton extends Button
	{
		private String _path;
		
		public ReplayButton(String path)
		{
			super();
			_path = path;
			super.setText(replayPathToDate(path).toString());
		}
		
		public String getPath() {return _path;}
		
		public void setText(String text) {return;}
		
		// something useful but ugly
		private Date replayPathToDate(String path)
		{
			return new Date(new Long(path.substring(("replay/").length(), path.length() - 4)));
		}
	}
	
	private Delegate _delegate;
	private ButtonGroup _titleButtons;
	
	public ReplayMenu()
	{
		super();
		_delegate = new NullDelegate();
		_titleButtons = null;
		
		initImages();
		initSprites();
		
		setWidth(480);
		setHeight(640);
		add(new MenuBackground());
		
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
	
	public void loadReplays()
	{
		remove(_titleButtons);
		
		_titleButtons = new ButtonGroup();
		//_titleButtons.setX(264);
		_titleButtons.setX(32);
		_titleButtons.setY(168);
		
		Button btn;
		File file = new File("replay/");
		File[] files = file.listFiles();
		Arrays.sort(files);
		int i, j;
		int length = files.length;
		final int replayLimit = 10;
		j = 0;
		for (i = length - 1; i >= 0; --i)
		{
			// list 10 replays only
			if (replayLimit == j)
				break;
			
			File f = files[i];
			btn = new ReplayButton(f.toString())
			{
				public void onReleased()
				{
					super.onReleased();
					_delegate.onReplaySelected(getPath());
				}
			};
			btn.setY(j * 20.0);
			_titleButtons.add(btn);
			++j;
		}
		
		btn = new Button()
		{
			public void onReleased()
			{
				super.onReleased();
				_delegate.onBack();
			}
		};
		btn.setText("BACK");
		btn.setY(replayLimit * 20.0);
		_titleButtons.add(btn);
		
		add(_titleButtons);
	}
	
	private void initImages()
	{
		ImageManager imgManager = ImageManager.getInstance();
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
	}
	
	public void setDelegate(Delegate d)
	{
		_delegate = d;
		if (null == _delegate)
			_delegate = new NullDelegate();
	}
}
