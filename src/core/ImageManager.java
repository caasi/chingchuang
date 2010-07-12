package core;

import java.util.HashMap;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.Transparency;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import util.LoggerManager;

/**
 * <p>ImageManager will load image and bind it to an unique id.</p>
 * <p>All of the Sprites using BufferedImage can access same image without creating more
 * instance of the image.</p>
 * @author KLH
 */
public class ImageManager
{
	private static ImageManager _instance = null;

	/**
	 * Return the only instance of ImageManager.
	 * @return Singleton of ImageManager
	 */
	public static ImageManager getInstance()
	{
		if (null == _instance)
		{
			_instance = new ImageManager();
		}
		return _instance;
	}

	private HashMap<String, BufferedImage> _map;

	/**
	 * Default constructor.
	 */
	private ImageManager()
	{
		_map = new HashMap<String, BufferedImage>();
	}

	/**
	 * <p>Create optimized image for current screen device.</p>
	 * <p>Works fine with png transparency, but this is not the biggest bottleneck of my draw system.</p>
	 * @param img
	 * @return
	 */
	private static BufferedImage optimizeImage(BufferedImage img)
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();				
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		
		boolean istransparent = img.getColorModel().hasAlpha();
		
		BufferedImage img2 = gc.createCompatibleImage(img.getWidth(), img.getHeight(), istransparent ? Transparency.BITMASK : Transparency.OPAQUE);
		Graphics2D g = img2.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return img2;
	}

	/**
	 * Load image and bind it to an id.
	 * @param id
	 * @param path
	 */
	public void load(String id, String path)
	{
		try
		{
			LoggerManager.log(LoggerManager.DEBUG, "<ImageManager> Load " + path + " as \"" + id + "\".");
			BufferedImage img = optimizeImage(ImageIO.read(new File(path)));
			//BufferedImage img = ImageIO.read(new File(path));
			if (img != null && !_map.containsKey(id))
			{
				_map.put(id, img);
			}
		}
		catch (IOException e)
		{
			LoggerManager.log(LoggerManager.ERROR, "<ImageManager> Can't load " + path + ": " + e.getMessage() + ".");
		}
	}

	/**
	 * Get image by id.
	 * @param id
	 * @return
	 */
	public BufferedImage image(String id)
	{
		LoggerManager.log(LoggerManager.DEBUG, "<ImageManager> Get image \"" + id + "\".");
		if (_map.containsKey(id))
		{
			return _map.get(id);
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<ImageManager> \"" + id + "\" not found.");
			return null;
		}
	}
}
