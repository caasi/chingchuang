package util;

public interface ILogger
{
	public int getLevel();
	public void setLevel(int l);
	public void log(int level, String msg);
}
