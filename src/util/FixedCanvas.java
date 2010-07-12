package util;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Canvas;

public class FixedCanvas extends Canvas
{
	private int _width;
	private int _height;

	public FixedCanvas(int w, int h)
	{
		_width = w;
		_height = h;
		addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				setSize(_width, _height);
			}
		});
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(_width, _height);
	}
}
