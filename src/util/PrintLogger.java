package util;

public class PrintLogger implements ILogger
{
	private int _level;

	public PrintLogger()
	{
		this(LoggerManager.ERROR);
	}
	
	public PrintLogger(int level)
	{
		setLevel(level);
	}

	public int getLevel() {return _level;}

	public void setLevel(int l) {	_level = l;}

	public void log(int level, String msg)
	{
		System.out.println
		(
			LoggerManager.NSLogLikeDate() + " " +
			LoggerManager.LEVEL_TEXT[level] + " " +
			msg
		);
	}
}
