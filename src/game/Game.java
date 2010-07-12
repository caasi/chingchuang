package game;

import java.awt.event.KeyListener;

import core.GObject;

public class Game extends GObject
{
	protected KeyListener _keyHandler;
	protected int _timer;

	public Game()
	{
		super();
		_keyHandler = null;
		_timer = 0;
	}
	
	public void update()
	{
		super.update();
		++_timer;
	}
	
	public int getTime() {return _timer;}
	public KeyListener getKeyHandler() {return _keyHandler;}
	
	public void setKeyHandler(KeyListener handler) {_keyHandler = handler;}
}
