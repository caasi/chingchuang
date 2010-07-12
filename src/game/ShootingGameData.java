package game;

import command.FlightObjectCommand;

import util.GamePoint;
import core.ImageManager;
import core.SpriteManager;

public class ShootingGameData
{
	private Level _level;
	private String _music;
	private FlightObjectCommand _command;
	private int _life;
	private GamePoint _initPosition;
	
	public ShootingGameData()
	{
		_level = null;
		_music = "";
		_life = 0;
		_initPosition = new GamePoint();
		init();
	}
	
	public Level getLevel() {return _level;}
	public String getMusic() {return _music;}
	public FlightObjectCommand getCommand() {return _command;}
	public int getLife() {return _life;}
	public GamePoint getInitPosition() {return _initPosition;}
	
	public void setLevel(Level l) {_level = l;}
	public void setMusic(String music) {_music = music;}
	public void setCommand(FlightObjectCommand command) {_command = command;}
	public void setLife(int life) {_life = life;}
	public void setInitPosition(double x, double y)
	{
		_initPosition.setX(x);
		_initPosition.setY(y);
	}
	
	private void init()
	{
		initImages();
		initSprites();
	}
	
	private void initImages()
	{
		ImageManager imgManager = ImageManager.getInstance();
		imgManager.load("back", "resource/background.png");
		imgManager.load("fighters", "resource/fighters.png");
		imgManager.load("bullets", "resource/bullets.png");
		imgManager.load("misc", "resource/misc.png");
	}
	
	private void initSprites()
	{
		// Maybe SpriteFactory is better?
		ImageManager imgManager = ImageManager.getInstance();
		SpriteManager sManager = SpriteManager.getInstance();
		sManager.load
		(
			"back",
			imgManager.image("back"),
			480, 640,
			new int[] {0},
			false
		);
		sManager.load
		(
			"f_redfighter",
			imgManager.image("fighters"),
			32, 32,
			new int[] {4}
		);
		sManager.load
		(
			"f_blade",
			imgManager.image("fighters"),
			32, 32,
			new int[] {5}
		);
		sManager.load
		(
			"f_multi",
			imgManager.image("fighters"),
			32, 32,
			new int[] {6}
		);
		sManager.load
		(
			"f_demoball",
			imgManager.image("fighters"),
			32, 32,
			new int[] {8, 8, 9, 9, 10, 10, 11, 11}
		);
		sManager.load
		(
			"f_wagon",
			imgManager.image("fighters"),
			32, 32,
			new int[] {16}
		);
		// other
		sManager.load
		(
			"o_box1",
			imgManager.image("fighters"),
			32, 32,
			new int[] {12}
		);
		sManager.load
		(
			"o_box2",
			imgManager.image("fighters"),
			32, 32,
			new int[] {13}
		);
		sManager.load
		(
			"o_box3",
			imgManager.image("fighters"),
			32, 32,
			new int[] {14}
		);
		sManager.load
		(
			"o_box4",
			imgManager.image("fighters"),
			32, 32,
			new int[] {15}
		);
		// fx
		sManager.load
		(
			"b_energy",
			imgManager.image("bullets"),
			32, 32,
			new int[] {8, 8, 9, 9, 10, 10, 9, 9}
		);
		sManager.load
		(
			"b_lightgun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {1}
		);
		sManager.load
		(
			"b_gun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {16}
		);
		sManager.load
		(
			"b_heavygun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {17, 18, 19, 20, 21}
		);
		sManager.load
		(
			"be_lightgun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {7}
		);
		sManager.load
		(
			"be_gun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {22}
		);
		sManager.load
		(
			"be_heavygun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {23, 24, 25, 26, 27}
		);
		sManager.load
		(
			"fx_gun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {2, 3, 4, 5}
		);
		sManager.load
		(
			"fxe_gun",
			imgManager.image("bullets"),
			16, 16,
			new int[] {8, 9, 10, 11}
		);
		sManager.load
		(
			"fx_energy",
			imgManager.image("bullets"),
			32, 32,
			new int[] {11, 12, 13, 14, 15}
		);
		// misc
		sManager.load
		(
			"m_wall",
			imgManager.image("misc"),
			32, 32,
			new int[] {0}
		);
		sManager.load
		(
			"m_broken1",
			imgManager.image("misc"),
			32, 32,
			new int[] {1}
		);
		sManager.load
		(
			"m_broken2",
			imgManager.image("misc"),
			32, 32,
			new int[] {2}
		);
		sManager.load
		(
			"m_broken3",
			imgManager.image("misc"),
			32, 32,
			new int[] {3}
		);
		sManager.load
		(
			"m_broken4",
			imgManager.image("misc"),
			64, 64,
			new int[] {2}
		);
		sManager.load
		(
			"m_building",
			imgManager.image("misc"),
			64, 64,
			new int[] {3, 3, 3, 4, 4, 4, 5, 5, 5, 4, 4, 4},
			false
		);
		sManager.load
		(
			"m_astro",
			imgManager.image("misc"),
			16, 16,
			new int[] {16}
		);
		sManager.load
		(
			"m_console",
			imgManager.image("misc"),
			16, 16,
			new int[] {17}
		);
		sManager.load
		(
			"ui_hp",
			imgManager.image("misc"),
			128, 16,
			new int[] {3},
			false
		);
		sManager.load
		(
			"ui_ship",
			imgManager.image("misc"),
			16, 16,
			new int[] {18},
			false
		);
		sManager.load
		(
			"ui_0",
			imgManager.image("misc"),
			16, 16,
			new int[] {96},
			false
		);
		sManager.load
		(
			"ui_1",
			imgManager.image("misc"),
			16, 16,
			new int[] {97},
			false
		);
		sManager.load
		(
			"ui_2",
			imgManager.image("misc"),
			16, 16,
			new int[] {98},
			false
		);
		sManager.load
		(
			"ui_3",
			imgManager.image("misc"),
			16, 16,
			new int[] {99},
			false
		);
		sManager.load
		(
			"ui_4",
			imgManager.image("misc"),
			16, 16,
			new int[] {100},
			false
		);
		sManager.load
		(
			"ui_5",
			imgManager.image("misc"),
			16, 16,
			new int[] {101},
			false
		);
		sManager.load
		(
			"ui_6",
			imgManager.image("misc"),
			16, 16,
			new int[] {102},
			false
		);
		sManager.load
		(
			"ui_7",
			imgManager.image("misc"),
			16, 16,
			new int[] {103},
			false
		);
		sManager.load
		(
			"ui_8",
			imgManager.image("misc"),
			16, 16,
			new int[] {104},
			false
		);
		sManager.load
		(
			"ui_9",
			imgManager.image("misc"),
			16, 16,
			new int[] {105},
			false
		);
		sManager.load
		(
			"ui_x",
			imgManager.image("misc"),
			16, 16,
			new int[] {106},
			false
		);
	}
}