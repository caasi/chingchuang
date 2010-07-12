package game;

import util.GameMath;
import util.GamePoint;
import command.*;
import flight.*;

public class Level1 extends Level
{
	public Level1()
	{
		super();
		// background
		for (int i = 0; i < 20; i++)
		{
			createWall(0, 32, -608 + i * 64);
			createWall(0, 448, -608 + i * 64);
		}
		createWall(0, 96, 608);
		createWall(0, 384, 608);
		
		for (int i = 0; i < 10; i++)
		{
			createWall(2020, 32, -608 + i * 64);
			createWall(2020, 448, -608 + i * 64);
		}
		createWall(2020, 96, -96);
		createWall(2020, 384, -32);
		
		for (int i = 0; i < 10; i++)
		{
			createWall(3080, 208, -608 + i * 64);
			createWall(3080, 272, -608 + i * 64);
		}
		
		for (int i = 0; i < 12; i++)
		{
			createWall(4150, 208, -736 + i * 64);
			createWall(4150, 272, -736 + i * 64);
			if (i >= 6 && i < 12)
			{
				createWall(4150, 144, -736 + i * 64);
				createWall(4150, 336, -736 + i * 64);
			}
		}
		
		createWall(4450, 32, -32);
		createWall(4450, 96, -32);
		createWall(4450, 160, -32);
		createWall(4450, 32, -96);
		createWall(4450, 96, -96);
		
		createWall(4750, 160, -32);
		createWall(4750, 224, -32);
		createWall(4750, 288, -32);
		createWall(4750, 352, -32);
		createWall(4750, 160, -96);
		createWall(4750, 224, -96);
		createWall(4750, 288, -96);
		createWall(4750, 352, -96);
		
		for (int i = 0; i < 20; i++)
		{
			createWall(6000, 32, -1248 + i * 64);
			createWall(6000, 448, -1248 + i * 64);
		}
		
		for (int i = 0; i < 16; i++)
		{
			createWall(7300, 32, -1248 + i * 64);
			createWall(7300, 448, -1248 + i * 64);
		}
		
		for (int i = 0; i < 5; ++i)
			createBroken((int)(GameMath.rand() * 7400), GameMath.rand() * 480, GameMath.rand() * -64, "wall");
		for (int i = 0; i < 5; ++i)
			createBroken((int)(GameMath.rand() * 7400), GameMath.rand() * 480, GameMath.rand() * -64, "broken1");
		for (int i = 0; i < 5; ++i)
			createBroken((int)(GameMath.rand() * 7400), GameMath.rand() * 480, GameMath.rand() * -64, "broken2");
		for (int i = 0; i < 5; ++i)
			createBroken((int)(GameMath.rand() * 7400), GameMath.rand() * 480, GameMath.rand() * -64, "broken3");
		for (int i = 0; i < 3; ++i)
			createBroken((int)(GameMath.rand() * 7400), GameMath.rand() * 480, GameMath.rand() * -64, "broken4");
		
		for (int i = 0; i < 5; ++i)
			createBroken(1800, 120 + GameMath.rand() * 260, GameMath.rand() * 64 -32, "broken1");
		for (int i = 0; i < 5; ++i)
			createBroken(1800, 120 + GameMath.rand() * 260, GameMath.rand() * 64 -32, "broken2");
		for (int i = 0; i < 5; ++i)
			createBroken(1800, 120 + GameMath.rand() * 260, GameMath.rand() * 64 -32, "broken3");
		createBroken(1800, 120 + GameMath.rand() * 260, GameMath.rand() * 64 -32, "broken4");
		
		// first fighter
		createLinearBladePatrol3
		(
			450,
			"enemy",
			TOP1.getX(),
			TOP1.getY(),
			GameMath.degreesToRadians(60),
			GameMath.degreesToRadians(60),
			8,
			GameMath.degreesToRadians(-90),
			0.05
		);
		
		// more patrol fighters
		createLinearBladePatrol3
		(
			735,
			"enemy",
			TOP1.getX(),
			TOP1.getY(),
			GameMath.degreesToRadians(60),
			GameMath.degreesToRadians(60),
			8,
			GameMath.degreesToRadians(-90),
			0.05
		);
		createLinearBladePatrol3
		(
			735,
			"enemy",
			TOP5.getX(),
			TOP5.getY(),
			GameMath.degreesToRadians(120),
			GameMath.degreesToRadians(120),
			8,
			GameMath.degreesToRadians(-90),
			0.05
		);
		
		// attackers
		createLinearBladePatrol3
		(
			1130,
			"enemy",
			RIGHT2.getX(),
			RIGHT2.getY(),
			GameMath.degreesToRadians(180),
			GameMath.degreesToRadians(180),
			8,
			GameMath.degreesToRadians(90),
			0.2
		);
		createLinearBladePatrol3
		(
			1160,
			"enemy",
			RIGHT2.getX(),
			RIGHT2.getY(),
			GameMath.degreesToRadians(180),
			GameMath.degreesToRadians(180),
			8,
			GameMath.degreesToRadians(90),
			0.2
		);
		createLinearBladePatrol3
		(
			1200,
			"enemy",
			RIGHT2.getX(),
			RIGHT2.getY(),
			GameMath.degreesToRadians(180),
			GameMath.degreesToRadians(180),
			8,
			GameMath.degreesToRadians(90),
			0.2
		);
		createLinearBladePatrol3
		(
			1230,
			"enemy",
			RIGHT2.getX(),
			RIGHT2.getY(),
			GameMath.degreesToRadians(180),
			GameMath.degreesToRadians(180),
			8,
			GameMath.degreesToRadians(90),
			0.2
		);
		createLinearBladePatrol3
		(
			1270,
			"enemy",
			RIGHT2.getX(),
			RIGHT2.getY(),
			GameMath.degreesToRadians(180),
			GameMath.degreesToRadians(180),
			8,
			GameMath.degreesToRadians(90),
			0.2
		);
		createLinearBladePatrol3
		(
			1310,
			"enemy",
			RIGHT2.getX(),
			RIGHT2.getY(),
			GameMath.degreesToRadians(180),
			GameMath.degreesToRadians(180),
			8,
			GameMath.degreesToRadians(90),
			0.2
		);
		createTmp(1340, TOP2.getX());
		createTmp(1360, TOP4.getX());
		createTmp(1380, TOP2.getX());
		createTmp(1400, TOP4.getX());
		createTmp(1420, TOP2.getX());
		createTmp(1440, TOP4.getX());
		createTmp(1460, TOP2.getX());
		createTmp(1480, TOP4.getX());
		createTmp(1500, TOP2.getX());
		createTmp(1520, TOP4.getX());
		createTmp(1540, TOP2.getX());
		createTmp(1560, TOP4.getX());
		createTmp(1580, TOP2.getX());
		createTmp(1600, TOP4.getX());
		createTmp(1620, TOP2.getX());
		createTmp(1640, TOP4.getX());
		
		//
		createRotater(2020, LEFT2.getY());
		createRotater(2300, LEFT3.getY());
		createRotater(2600, LEFT4.getY());
		
		createRotater(3150, LEFT4.getY());
		createRotater(3220, LEFT3.getY());
		createRotater(3290, LEFT2.getY());
		
		createTmp(3690, TOP2.getX());
		createTmp(3695, TOP5.getX());
		
		createTmp(3710, TOP4.getX());
		createTmp(3715, TOP1.getX());
		
		createTmp(3730, TOP2.getX());
		createTmp(3735, TOP5.getX());
		
		createTmp(3750, TOP4.getX());
		createTmp(3755, TOP1.getX());
		
		createTmp(3770, TOP2.getX());
		createTmp(3775, TOP5.getX());
		
		createTmp(3790, TOP4.getX());
		createTmp(3795, TOP1.getX());
		
		createTmp(3810, TOP2.getX());
		createTmp(3815, TOP5.getX());
		
		createBlocker
		(
			4010,
			LEFT2.getX(),
			LEFT2.getY(),
			GameMath.degreesToRadians(0),
			2.0
		);
		createBlocker
		(
			4010,
			RIGHT3.getX(),
			RIGHT3.getY(),
			GameMath.degreesToRadians(180),
			2.0
		);
		createBlocker
		(
			4080,
			LEFT2.getX(),
			LEFT2.getY(),
			GameMath.degreesToRadians(0),
			2.0
		);
		createBlocker
		(
			4080,
			RIGHT3.getX(),
			RIGHT3.getY(),
			GameMath.degreesToRadians(180),
			2.0
		);
		createBlocker
		(
			4150,
			LEFT2.getX(),
			LEFT2.getY(),
			GameMath.degreesToRadians(0),
			2.0
		);
		createBlocker
		(
			4150,
			RIGHT3.getX(),
			RIGHT3.getY(),
			GameMath.degreesToRadians(180),
			2.0
		);
		createBlocker
		(
			4220,
			LEFT2.getX(),
			LEFT2.getY(),
			GameMath.degreesToRadians(0),
			2.0
		);
		createBlocker
		(
			4220,
			RIGHT3.getX(),
			RIGHT3.getY(),
			GameMath.degreesToRadians(180),
			2.0
		);
		
		createTmp(4780, TOP2.getX());
		createTmp(4785, TOP5.getX());
		
		createTmp(4800, TOP1.getX());
		createTmp(4805, TOP4.getX());
		
		createTmp(4820, TOP2.getX());
		createTmp(4825, TOP5.getX());
		
		createTmp(4840, TOP1.getX());
		createTmp(4845, TOP4.getX());
		
		createTmp(4860, TOP2.getX());
		createTmp(4865, TOP5.getX());
		
		createTmp(4880, TOP1.getX());
		createTmp(4885, TOP4.getX());
		
		createTmp(4900, TOP2.getX());
		createTmp(4905, TOP5.getX());
		
		createTmp(4920, TOP1.getX());
		createTmp(4925, TOP4.getX());
		
		createRotater(5100, LEFT2.getY());
		createRotater(5180, LEFT3.getY());
		createRotater(5260, LEFT4.getY());
		
		createRotater(5340, LEFT2.getY());
		createRotater(5420, LEFT3.getY());
		createRotater(5500, LEFT4.getY());
		
		createTmp(5820, TOP2.getX());
		createTmp(5825, TOP2.getX());
		createTmp(5830, TOP2.getX());
		createTmp(5835, TOP2.getX());
		createTmp(5840, TOP2.getX());
		
		createTmp(5830, TOP4.getX());
		createTmp(5835, TOP4.getX());
		createTmp(5840, TOP4.getX());
		createTmp(5845, TOP4.getX());
		createTmp(5850, TOP4.getX());
		
		createTmp(5860, TOP2.getX());
		createTmp(5865, TOP2.getX());
		createTmp(5870, TOP2.getX());
		createTmp(5875, TOP2.getX());
		createTmp(5880, TOP2.getX());
		
		createTmp(5870, TOP4.getX());
		createTmp(5875, TOP4.getX());
		createTmp(5880, TOP4.getX());
		createTmp(5885, TOP4.getX());
		createTmp(5890, TOP4.getX());
		
		LevelData ld = new LevelData();
		ld.setTime(7340);
		ld.setAction(LevelData.END);
		_data.add(ld);
	}
	
	private void createWall(int time, double x, double y)
	{
		IFlightObject fo;
		FlightObjectCommand command;
		LevelData ld;
		
		fo = new Building();
		fo.setFaction("enemy");
		fo.setCenterX(x);
		fo.setCenterY(y);
		fo.setLiveTime(600);
		command = new MoveCommand(0, 5.0);
		command.setLiveTime(fo.getLiveTime());
		fo.setCommand(command);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
	}
	
	private void createBroken(int time, double x, double y, String id)
	{
		IFlightObject fo = null;
		LevelData ld;
		
		if (id.equals("wall"))
			fo = new Wall();
		if (id.equals("broken1"))
			fo = new Broken1();
		if (id.equals("broken2"))
			fo = new Broken2();
		if (id.equals("broken3"))
			fo = new Broken3();
		if (id.equals("broken4"))
			fo = new Broken4();
		fo.setFaction("back");
		fo.setCenterX(x);
		fo.setCenterY(y);
		fo.setRotation((int)(GameMath.rand() * 360));
		fo.setCommand(new RotateCommand(GameMath.rand() * 0.4 - 0.2));
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.BACK);
		ld.setTarget(fo);
		_data.add(ld);
	}
	
	private void createTmp(int time, double x)
	{
		createLinearBladeAttacker
		(
			time,
			"enemy",
			x,
			TOP1.getY(),
			GameMath.degreesToRadians(90),
			GameMath.degreesToRadians(90),
			4,
			x > 240 ? GameMath.degreesToRadians(135) : GameMath.degreesToRadians(45),
			0.05
		);
	}
	
	private void createLinearBladePatrol3
	(
		int time,
		String faction,
		double x,
		double y,
		double selfRadians,
		double vRadians,
		double vLength,
		double aRadians,
		double aLength
	)
	{
		GamePoint member1 = new GamePoint(-48, 0);
		GamePoint member2 = new GamePoint(-96, 0);
		
		double tmpX, tmpY;
		IFlightObject fo;
		FlightObjectCommand command;
		LevelData ld;
		
		fo = new Blade();
		fo.setFaction(faction);
		fo.setX(x);
		fo.setY(y);
		command = new PolarMoveTowardCommand
		(
			vRadians, vLength,
			aRadians, aLength
		);
		fo.setCommand(command);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
		
		member1.rotate(selfRadians);
		tmpX = member1.getX();
		tmpY = member1.getY();
		fo = new Blade();
		fo.setFaction(faction);
		fo.setX(x + tmpX);
		fo.setY(y + tmpY);
		command = new PolarMoveTowardCommand
		(
			vRadians, vLength,
			aRadians, aLength
		);
		fo.setCommand(command);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
		
		member2.rotate(selfRadians);
		tmpX = member2.getX();
		tmpY = member2.getY();
		fo = new Blade();
		fo.setFaction(faction);
		fo.setX(x + tmpX);
		fo.setY(y + tmpY);
		command = new PolarMoveTowardCommand
		(
			vRadians, vLength,
			aRadians, aLength
		);
		fo.setCommand(command);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
	}
	
	private void createLinearBladeAttacker
	(
		int time,
		String faction,
		double x,
		double y,
		double selfRadians,
		double vRadians,
		double vLength,
		double aRadians,
		double aLength
	)
	{	
		double tmpX, tmpY;
		IFlightObject fo;
		FlightObjectCommandContainer series;
		FlightObjectCommandContainer parallel;
		FlightObjectCommand command;
		LevelData ld;
		
		fo = new Blade();
		fo.setFaction(faction);
		fo.setX(x);
		fo.setY(y);
		fo.setRotation(90);
		fo.setLiveTime(180);
		parallel = new ParallelCommands();
		command = new PolarMoveCommand
		(
			fo,
			vRadians, vLength,
			aRadians, aLength
		);
		parallel.add(command);
		series = new SeriesCommands();
		command = new IdleCommand(fo);
		command.setLiveTime(10);
		series.add(command);
		command = new FireToTargetCommand(fo, FireCommand.MAIN);
		command.setLiveTime(30);
		series.add(command);
		parallel.add(series);
		fo.setCommand(parallel);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
	}
	
	private void createRotater(int time, double y)
	{
		IFlightObject fo;
		FlightObjectCommandContainer series;
		FlightObjectCommandContainer parallel;
		FlightObjectCommand command;
		LevelData ld;
		
		fo = new Enemy();
		fo.setFaction("enemy");
		fo.setCenterX(LEFT2.getX());
		fo.setCenterY(y);
		fo.setLiveTime(180);
		series = new SeriesCommands();
		command = new MoveCommand(fo, 4, 0, -4 / 30, 0);
		command.setLiveTime(30);
		series.add(command);
		parallel = new ParallelCommands();
		command = new RotateCommand(fo, 2);
		command.setLiveTime(120);
		parallel.add(command);
		command = new FireCommand(fo, FireCommand.MAIN);
		command.setLiveTime(120);
		parallel.add(command);
		series.add(parallel);
		command = new MoveCommand(fo, -4, 0, 4 / 30, 0);
		command.setLiveTime(30);
		series.add(command);
		fo.setCommand(series);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
		
		fo = new Enemy();
		fo.setFaction("enemy");
		fo.setCenterX(RIGHT2.getX());
		fo.setCenterY(y);
		fo.setLiveTime(180);
		series = new SeriesCommands();
		command = new MoveCommand(fo, -4, 0, 4 / 30, 0);
		command.setLiveTime(30);
		series.add(command);
		parallel = new ParallelCommands();
		command = new RotateCommand(fo, -2);
		command.setLiveTime(120);
		parallel.add(command);
		command = new FireCommand(fo, FireCommand.MAIN);
		command.setLiveTime(120);
		parallel.add(command);
		series.add(parallel);
		command = new MoveCommand(fo, 4, 0, -4 / 30, 0);
		command.setLiveTime(30);
		series.add(command);
		fo.setCommand(series);
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
	}
	
	public void createBlocker
	(
		int time,
		double x, double y,
		double vRadians,
		double vLength
	)
	{
		IFlightObject fo;
		FlightObjectCommandContainer series;
		FlightObjectCommandContainer parallel;
		FlightObjectCommand command;
		LevelData ld;
		
		fo = new Blade();
		fo.setFaction("enemy");
		fo.setX(x);
		fo.setY(y);
		fo.setRotation(90);
		
		parallel = new ParallelCommands();
		command = new PolarMoveCommand(fo, vRadians, vLength);
		parallel.add(command);
		parallel.add(new FireCommand(fo, FireCommand.MAIN));
		fo.setCommand(parallel);
		
		ld = new LevelData();
		ld.setTime(time);
		ld.setAction(LevelData.ADD);
		ld.setTarget(fo);
		_data.add(ld);
	}
}