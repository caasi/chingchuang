package core;

import java.awt.Graphics;
import java.util.ArrayList;
import util.LoggerManager;

/**
 * <p>Class GObject will handle basic update and draw for thing you want to show on the screen.<br/>
 * Currently it can't handle coordinate transformation, so you have to calculate global
 * rotation by yourself.</p>
 * <p>I may need good transformation system to solve local/global x, y, rotation problem.</p>
 * <p>BTW, setParent() only uses in add and remove, I need remove interface IGObject and clean
 * up this mess.</p>
 * <p>Another todo is about counting children, ask children number recursively is not a good idea,
 * but it works fine now.</p>
 * @author KLH
 */
public class GObject implements IGObject
{
	protected IGObject _parent;
	protected ArrayList<IGObject> _children;
	private ArrayList<IGObject> _willAdd;
	private ArrayList<IGObject> _willRemove;
	private double _x;
	private double _y;
	private int _width;
	private int _height;

	/**
	 * Default constructor.
	 */
	public GObject()
	{
		this(0.0, 0.0);
	}
	
	/**
	 * Create a GObject in (x, y).
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public GObject(double x, double y)
	{
		setParent(null);
		setX(x);
		setY(y);
		setWidth(0);
		setHeight(0);
		_children = new ArrayList<IGObject>();
		_willAdd = new ArrayList<IGObject>();
		_willRemove = new ArrayList<IGObject>();
	}

	// GETTERS
	/**
	 * Get the x coordinate of this instance.
	 */
	public double getX() {return _x;}
	
	/**
	 * Get the y coordinate of this instance.
	 */
	public double getY() {return _y;}
	
	/**
	 * Get the global x coordinate of this instance.
	 */
	public double getGlobalX()
	{
		double gx = getX();
		if (_parent != null)
			gx += _parent.getGlobalX();
		return gx;
	}
	
	/**
	 * Get the global y coordinate of this instance.
	 */
	public double getGlobalY()
	{
		double gy = getY();
		if (_parent != null)
			gy += _parent.getGlobalY();
		return gy;
	}
	
	/**
	 * Get the width of this instance.
	 */
	public int getWidth() {return _width;}
	
	/**
	 * Get the height of this instance.
	 */
	public int getHeight() {return _height;}
	
	/**
	 * Get the parent of this instance.
	 */
	public IGObject getParent() {return _parent;}

	// SETTERS
	/**
	 * Set x coordinate.
	 * @param x X coordinate
	 */
	public void setX(double x) {_x = x;}
	
	/**
	 * Set y coordinate.
	 * @param y Y coordinate
	 */
	public void setY(double y) {_y = y;}
	
	/**
	 * Set width.
	 * @param width Width
	 */
	public void setWidth(int width) {	_width = width;}
	
	/**
	 * Set height.
	 * @param height Height
	 */
	public void setHeight(int height) {_height = height;}
	
	/**
	 * Set parent.
	 * @param p Parent GObject
	 */
	public void setParent(IGObject p) {_parent = p;}
	
	/**
	 * <p>Add another GObject as a child.</p>
	 * <p>Because I want to add GObject when I updating it, I have to save new objects in another
	 * collection, and add it after updating complete.</p>
	 * <p>Also note you can add same GObject again to bring it to the top of all children. It simulates
	 * same feature in ActionScript 3.</p>
	 * @param go Child GObject
	 */
	public void add(IGObject go)
	{
		LoggerManager.log(LoggerManager.INFO, "<GObject> Add a child.");
		if (_willAdd.contains(go))
		{
			LoggerManager.log(LoggerManager.WARN, "<GObject> Duplicate add call.");
			return;
		}
		if (!_children.contains(go))
		{
			_willAdd.add(go);
			go.setParent(this);
		}
		else if (go.getParent() == this)
		{
			LoggerManager.log(LoggerManager.INFO, "<GObject> Move the child to the top.");
			_willRemove.add(go);
			_willAdd.add(go);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<GObject> Target has already be a child of other GObject.");
		}
	}

	/**
	 * <p>Remove a child GObject from current instance.</p>
	 * <p>Like add(), it add the target to another collection, and remove it after updating.</p>
	 * @param go Child GObject to be removed
	 */
	public void remove(IGObject go)
	{
		LoggerManager.log(LoggerManager.INFO, "<GObject> Remove a child.");
		if (_children.contains(go))
		{
			_willRemove.add(go);
			go.setParent(null);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<GObject> Target is not my child.");
		}
	}
	
	/**
	 * Test if contains another GObject recursively.
	 * @param go Target GObject
	 * @return true if it does contains target GObject
	 */
	public boolean contains(IGObject go)
	{
		if (null == go)
			return false;
		boolean isContained = false;
		isContained |= _children.contains(go);
		int length = _children.size();
		for (int i = 0; i < length; ++i)
		{
			isContained |= _children.get(i).contains(go);	
		}
		return isContained;
	}
	
	/**
	 * Get the number of children.
	 * @return Total number of children
	 */
	public int numberOfChildren()
	{
		return _children.size() + _willAdd.size();
	}
	
	/**
	 * Get the number of GObjects under this one recursively.
	 * @return Total number in this GObject tree
	 */
	public int numberOfGObjects()
	{
		int count = numberOfChildren();
		int length = _children.size();
		for (int i = 0; i < length; ++i)
		{
			count += _children.get(i).numberOfGObjects();	
		}
		return count;
	}

	/**
	 * <p>Update all children.</p>
	 * <p>If any class override this method, it should call super.update() last.</p>
	 */
	public void update()
	{
		int i, length;
		length = _children.size();
		for (i = 0; i < length; ++i)
		{
			_children.get(i).update();
		}
		length = _willRemove.size();
		for (i = 0; i < length; ++i)
		{
			_children.remove(_willRemove.get(i));
		}
		_willRemove.removeAll(_willRemove);
		length = _willAdd.size();
		for (i = 0; i < length; ++i)
		{
			_children.add(_willAdd.get(i));
		}
		_willAdd.removeAll(_willAdd);
	}

	/**
	 * <p>Draw all children.</p>
	 * <p>I don't use clip of Graphics for efficiency problem. Making too much clips will lower
	 * the performance of my game.</p>
	 * <p>Like update(), you should call super.draw(g) last in your own class.</p>
	 * @param g Graphics to be draw
	 */
	public void draw(Graphics g)
	{
		int i;
		int length = _children.size();
		for (i = 0; i < length; ++i)
		{
			_children.get(i).draw(g);
		}
	}
}
