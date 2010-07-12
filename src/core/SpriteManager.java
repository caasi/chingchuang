package core;

import java.util.HashMap;
import java.awt.image.BufferedImage;
import util.LoggerManager;

/**
 * <p>SpriteManager creates Sprite and bind it to unique id.</p>
 * <p>When you ask a Sprite from it, it gives you a copy of that Sprite, so the system
 * just create rotated images once.</p>
 * <p>This is my best solution for slow affine transformation in Java right now.</p>
 * @author KLH
 * @see Sprite
 */
public class SpriteManager
{
	private static SpriteManager _instance = null;

	/**
	 * Get the only instance of SpriteManager.
	 * @return Singleton of SpriteManager
	 */
	public static SpriteManager getInstance()
	{
		if (null == _instance)
		{
			_instance = new SpriteManager();
		}
		return _instance;
	}

	private HashMap<String, Sprite> _map;

	/**
	 * Default constructor.
	 */
	private SpriteManager()
	{
		_map = new HashMap<String, Sprite>();
	}

	/**
	 *<p>Create a Sprite from sprites sheet image, width and height is for the Sprite not the image.</p>
	 *<p>This is only a shortcut for sprite that rotates.</p>
	 * @param id
	 * @param img
	 * @param width
	 * @param height
	 * @param animation
	 */
	public void load
	(
		String id,
		BufferedImage img,
		int width, int height,
		int[] animation
	)
	{
		this.load(id, img, width, height, animation, true);
	}
	
	/**
	 * <p>Create Sprite from sprites sheet.</p>
	 * @param id
	 * @param img
	 * @param width
	 * @param height
	 * @param animation
	 * @param rotatable
	 */
	public void load
	(
		String id,
		BufferedImage img,
		int width, int height,
		int[] animation,
		boolean rotatable
	)
	{
		LoggerManager.log(LoggerManager.DEBUG, "<SpriteManager> Create sprite \"" + id + "\".");
		Sprite s = new Sprite(img, width, height, animation, rotatable);
		if (img != null && !_map.containsKey(id))
		{
			_map.put(id, s);
		}
		else
		{
			LoggerManager.log(LoggerManager.DEBUG, "<SpriteManager> Can't create sprite \"" + id + "\".");
		}
	}

	/**
	 * Get a copy of some Sprite by id.
	 * @param id
	 * @return
	 */
	public Sprite aCopyOf(String id)
	{
		if (_map.containsKey(id))
		{
			LoggerManager.log(LoggerManager.INFO, "<SpriteManager> Create new sprite from: \"" + id + "\".");
			return new Sprite(_map.get(id));
		}
		else
		{
			LoggerManager.log(LoggerManager.WARN, "<SpriteManager> \"" + id + "\" not found.");
			return null;
		}
	}
}
