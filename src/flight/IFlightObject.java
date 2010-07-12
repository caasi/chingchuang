package flight;

import java.awt.Graphics;
import java.awt.Rectangle;
import weapon.Weapon;
import command.FlightObjectCommand;
import core.IGObject;
import core.Sprite;

public interface IFlightObject extends IGObject
{	
	public void drawHitRect(Graphics g);
	
	// getters
	public FlightObject.Delegate getDelegate();
	public int getHp();
	public int getMaxHp();
	public IFlightObject getTarget();
	public String getFaction();
	public double getRotation();
	public double getRadians();
	public double getCenterX();
	public double getCenterY();
	public double getGlobalCenterX();
	public double getGlobalCenterY();
	public int getLiveTime();
	public int getDamage();
	public Rectangle getHitRect();
	
	// setters
	public void setDelegate(FlightObject.Delegate delegate);
	public void setHp(int hp);
	public void setSprite(Sprite s);
	public void setTarget(IFlightObject fo);
	public void setFaction(String f);
	public void setRotation(double degrees);
	public void setRadians(double radians);
	public void setCenterX(double x);
	public void setCenterY(double y);
	public void setLiveTime(int time);
	public void setCommand(FlightObjectCommand command);
	public void setWeapon(Weapon w);
	public void setAltWeapon(Weapon w);
	
	public void hit(IFlightObject target);
	public void clearHitList();
	public void addToHitList(IFlightObject fo);
	public void fire();
	public void fireAlt();
}
