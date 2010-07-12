package core;

import java.awt.Graphics;

/**
 * <p>Interface IGObject is the interface of GObject.</p>
 * @author KLH
 * @see GObject
 */
public interface IGObject
{
	// getters
	public double getX();
	public double getY();
	public double getGlobalX();
	public double getGlobalY();
	public int getWidth();
	public int getHeight();
	public IGObject getParent();
	
	// setters
	public void setX(double x);
	public void setY(double y);
	public void setWidth(int width);
	public void setHeight(int height);
	public void setParent(IGObject p);
	
	public void add(IGObject go);
	public void remove(IGObject go);
	public boolean contains(IGObject go);
	public int numberOfChildren();
	public int numberOfGObjects();
	public void update();
	public void draw(Graphics g);
}
