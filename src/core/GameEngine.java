package core;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.*;

import javax.swing.*;
import game.Game;
import util.FixedCanvas;
import util.LoggerManager;

public class GameEngine extends JFrame implements Runnable
{
	private DisplayMode _mode;
	private GraphicsDevice _device;
	
	private FixedCanvas _canvas;
	private BufferStrategy _bufferStrategy;
	private int _fps;
	private int _nanoFrameLength;
	private Game _main;
	private Thread _t;
	
	public GameEngine(int fps, int width, int height)
	{
		super();
		_fps = fps;
		_nanoFrameLength = 1000000000/_fps;
		setUndecorated(true);
		setIgnoreRepaint(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 225 * 400(9:16) is too narrow
		_canvas = new FixedCanvas(width, height);
		_canvas.setBackground(new Color(0x0C0C0C));
		_canvas.setIgnoreRepaint(true);
		getContentPane().add(_canvas);
		pack();
		setVisible(true);

		_mode = new DisplayMode(width, height, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		_device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		_device.setFullScreenWindow(this);
		_device.setDisplayMode(_mode);
		//setSize(800, 600);
		
		_t = new Thread(this);
	}
	
	public void start()
	{
		_t.start();
	}
	
	public void update()
	{
		_main.update();
	}
	
	public void draw(Graphics g)
	{
		_main.draw(g);
		
	}
	
	// implement Runnable
	public void run()
	{
		long startTime = System.nanoTime();
		long prevTime, currentTime;
		prevTime = currentTime = startTime;
		int frameCount = 0;

		LoggerManager.log(LoggerManager.DEBUG, "<GameEngine> Initialization.");
		_canvas.createBufferStrategy(2);
		_bufferStrategy = _canvas.getBufferStrategy();

		LoggerManager.log(LoggerManager.DEBUG, "<GameEngine> Start game loop.");
		while (isVisible()) {
			if (null != _main)
			{
				// logic update
				update();
				// end of logic update
	
				// rendering
				// clear background
				Graphics g = _bufferStrategy.getDrawGraphics();
				g.clearRect(0, 0, _mode.getWidth(), _mode.getHeight());
				// draw game
				draw(g);
				//draw instance count and fpx
				Graphics2D g2d = (Graphics2D)g;
				g2d.setColor(Color.GREEN);
				g2d.setFont(new Font("Arial", Font.BOLD, 14));
				g2d.drawString("instances: " + _main.numberOfGObjects(), 10, 20);
				currentTime = System.nanoTime();
				double fps = 1000000000.0 / (currentTime - prevTime);
				prevTime = currentTime;
				g2d.drawString("fps: " + Math.round(10 * fps) / 10.0, 10, 35);
				// end of rendering
	
				g.dispose();
				_bufferStrategy.show();
			}
			++frameCount;
			// end of draw

			while ((System.nanoTime() - startTime)/_nanoFrameLength < frameCount)
			{
				Thread.yield();
			}
		}
	}
	
	public void removeKeyHandler(KeyListener keyHandler)
	{
		_canvas.removeKeyListener(keyHandler);
		removeKeyListener(keyHandler);
	}
	
	public void setKeyHandler(KeyListener keyHandler)
	{
		_canvas.addKeyListener(keyHandler);
		addKeyListener(keyHandler);
	}
	
	public void setGame(Game mainGame)
	{
		_main = mainGame;
		_main.setX((_mode.getWidth() - _main.getWidth()) / 2);
		_main.setY((_mode.getHeight() - _main.getHeight()) / 2);
	}
}
