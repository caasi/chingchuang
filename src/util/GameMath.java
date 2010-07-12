package util;

import java.util.Random;

public class GameMath
{
	public static final int FOREVER = Integer.MAX_VALUE;
	public static final int LONG_ENOUGH = 360;
	
	private static final double _toDegreesFactor = 180 / Math.PI;
	private static final double _toRadiansFactor = Math.PI / 180;
	
	private static Random _rand = new Random();
	
	// Some simple math function for this game,
	public static double radiansToDegrees(double radians)
	{
		return radians * _toDegreesFactor;
	}
	
	public static double degreesToRadians(double degrees)
	{
		return degrees * _toRadiansFactor;
	}
	
	public static void setRandSeed(long seed)
	{
		_rand.setSeed(seed);
	}
	
	public static double rand()
	{
		return _rand.nextDouble();
	}
}
