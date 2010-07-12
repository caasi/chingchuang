package game;

import util.LoggerManager;

import core.GObject;

public class ButtonGroup extends GObject
{
	private int _currentBtn;
	
	public ButtonGroup()
	{
		super();
		_currentBtn = 0;
	}
	
	public void add(GObject go)
	{
		LoggerManager.log(LoggerManager.WARN, "<ButtonGroup> Please don't add anything other than Button.");
		return;
	}
	
	public void add(Button btn)
	{
		if (0 == numberOfChildren())
			btn.setActived(true);
		super.add(btn);
	}
	
	public void remove(Button btn)
	{
		if (_children.indexOf(btn) == _currentBtn)
		super.remove(btn);
	}
	
	public void next()
	{
		((Button)_children.get(_currentBtn)).setActived(false);
		++_currentBtn;
		int length = numberOfChildren();
		if (_currentBtn >= length)
			_currentBtn = length - 1;
		((Button)_children.get(_currentBtn)).setActived(true);
	}
	
	public void prev()
	{
		((Button)_children.get(_currentBtn)).setActived(false);
		--_currentBtn;
		if (_currentBtn < 0)
			_currentBtn = 0;
		((Button)_children.get(_currentBtn)).setActived(true);
	}
	
	public Button currentButton()
	{
		return (Button)_children.get(_currentBtn);
	}
}
