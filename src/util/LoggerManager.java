package util;

import java.util.Vector;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class LoggerManager
{
	public static final int ERROR = 0;
	public static final int WARN = 1;
	public static final int DEBUG = 2;
	public static final int INFO = 3;
	public static final String[] LEVEL_TEXT =
	{
		"ERROR",
		"WARNING",
		"DEBUG",
		"INFO"
	};
	private static SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static LoggerManager _instance = null;

	public static LoggerManager getInstance()
	{
		if (null == _instance)
		{
			_instance = new LoggerManager();
		}
		return _instance;
	}

	public static void addLogger(ILogger logger)
	{
		getInstance()._addLogger(logger);
	}

	public static void removeLogger(ILogger logger)
	{
		getInstance()._removeLogger(logger);
	}

	public static void log(int level, String msg)
	{
		getInstance()._log(level, msg);
	}

	// tool
	public static String NSLogLikeDate()
	{
		Calendar cal = Calendar.getInstance();
		return _dateFormat.format(cal.getTime());
	}

	private Vector<ILogger> _loggers;

	private LoggerManager()
	{
		_loggers = new Vector<ILogger>();
		_addLogger(new PrintLogger(LoggerManager.DEBUG));
	}

	private void _addLogger(ILogger logger)
	{
		if (!_loggers.contains(logger))
		{
			_loggers.add(logger);
		}
	}

	private void _removeLogger(ILogger logger)
	{
		if (_loggers.contains(logger))
		{
			_loggers.remove(logger);
		}
	}

	private void _log(final int level, String msg)
	{
		int length = _loggers.size();
		for (int i = length - 1; i >= 0; --i)
		{
			ILogger logger = _loggers.elementAt(i);
			if (level <=  logger.getLevel())
			{
				logger.log(level, msg);
			}
		}
	}
}
