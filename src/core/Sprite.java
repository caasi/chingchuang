package core;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import util.GameMath;
import util.LoggerManager;

/**
 * <p>Magical Sprite!</p>
 * <p>Pre-calculated images solve my problem about affine transformation.</p>
 * <p>Don't try to rotate big image, it will eat all of your heap!</p>
 * @author KLH
 * @see SpriteManager
 * @see FlightObject
 */
public class Sprite
{
	private BufferedImage _image;
	// quick hack
	private boolean _rotatable;
	private int[] _animation;
	private int _width;
	private int _height;
	private int _offsetX;
	private int _offsetY;
	private double _hypotenuse;
	private int _frameCounter;
	private int _rowLength;
	private ArrayList<ArrayList<BufferedImage>> _rotatedImages;

	/**
	 * Create Sprite, this is the shortcut for image that rotated.
	 * @param img Sprites sheet image
	 * @param width Width of sprite cell
	 * @param height Height of sprite cell
	 * @param animation Animation frame keys
	 */
	public Sprite
	(
		BufferedImage img,
		int width, int height,
		int[] animation
	)
	{
		this(img, width, height, animation, true);
	}
	
	/**
	 * Create Sprite.
	 * @param img Sprites sheet image
	 * @param width Width of sprite cell
	 * @param height Height of sprite cell
	 * @param animation Animation frame keys
	 * @param rotatable Is this Sprite rotatable
	 */
	public Sprite
	(
		BufferedImage img,
		int width, int height,
		int[] animation,
		boolean rotatable
	)
	{
		LoggerManager.log(LoggerManager.DEBUG, "<Sprite> Create a sprite.");
		_image = img;
		_rotatable = rotatable;
		_animation = animation;
		_frameCounter = 0;
		_width = width;
		_height = height;
		_hypotenuse = _width * _width + _height * _height;
		_hypotenuse = Math.sqrt(_hypotenuse);
		_rowLength = _image.getWidth() / _width;

		_offsetX = 0;
		_offsetY = 0;
		_rotatedImages = null;
		if (_rotatable)
		{
			int ceilHypo = (int)Math.ceil(_hypotenuse);
			_offsetX = (_width - ceilHypo) / 2;
			_offsetY = (_height - ceilHypo) / 2;
			
			_rotatedImages = new ArrayList<ArrayList<BufferedImage>>();
			LoggerManager.log(LoggerManager.DEBUG, "<Sprite> Creating rotated images.");
			for (int i = 0; i < _animation.length; ++i)
			{ 
				ArrayList<BufferedImage> rotatedList = new ArrayList<BufferedImage>();
				for (int j = 0; j < 360; ++j)
				{
					BufferedImage oldImage = getImageByIndex(_animation[i]);
					BufferedImage newImage = new BufferedImage
					(
						ceilHypo,
						ceilHypo,
						oldImage.getColorModel().hasAlpha() ? Transparency.BITMASK : Transparency.OPAQUE
					);
					Graphics2D g = newImage.createGraphics();
					g.rotate(GameMath.degreesToRadians(j + 90), _hypotenuse / 2.0, _hypotenuse / 2.0);
					g.drawImage(oldImage, (ceilHypo - _width) / 2, (ceilHypo - _height) / 2, null);
					g.dispose();
					rotatedList.add(newImage);
				}
				_rotatedImages.add(rotatedList);
			}
			LoggerManager.log(LoggerManager.DEBUG, "<Sprite> Rotated images created.");
		}
	}

	/**
	 * <p>Copy constructor.</p>
	 * <p>Strange bug happened when you miss any variable.</p>
	 * @param src Source Sprite
	 */
	public Sprite(Sprite src)
	{
		LoggerManager.log(LoggerManager.INFO, "<Sprite> Copy a sprite.");
		_image = src._image;
		_animation = src._animation;
		_width = src._width;
		_height = src._height;
		_hypotenuse = src._hypotenuse;
		_frameCounter = src._frameCounter;
		_rowLength = src._rowLength;
		_rotatedImages = src._rotatedImages;
		_offsetX = src._offsetX;
		_offsetY = src._offsetY;
		_rotatable = src._rotatable;
	}
	
	/**
	 * Get width.
	 * @return Width of the Sprite
	 */
	public int getWidth()
	{
		return _width;
	}
	
	/**
	 * Get height.
	 * @return Height of the Sprite
	 */
	public int getHeight()
	{
		return _height;
	}
	
	/**
	 * Get the length of animation.
	 * @return Length of animation.
	 */
	public int getLength()
	{
		return _animation.length;
	}

	/**
	 * Cut clip from sprites sheet, treat sprites sheet as an one dimension array.
	 * @param index One dimension index of sprite
	 * @return Cut image from sprites sheet
	 */
	private BufferedImage getImageByIndex(int index)
	{
		return _image.getSubimage
		(
			index % _rowLength * _width,
			index / _rowLength * _height,
			_width,
			_height
		);
	}

	/**
	 * <p>Draw Sprite.</p>
	 * <p>You should always use this method for rotated image. It hides the truth that rotated image has
	 * different width and height with original image.</p>
	 * @param g
	 * @param x
	 * @param y
	 * @param degrees
	 */
	public void draw(Graphics g, int x, int y, int degrees)
	{
		BufferedImage image;
		if (_rotatable)
			image = _rotatedImages.get(_frameCounter).get((degrees + 360) % 360);
		else
			image = getImageByIndex(_animation[_frameCounter]);
		g.drawImage
		(
			image,
			x + _offsetX,
			y + _offsetY,
			null
		);
	}

	/**
	 * Update animation to next frame.
	 */
	public void nextFrame()
	{
		_frameCounter = _animation.length == (_frameCounter + 1) ? 0 : _frameCounter + 1;
	}
}
