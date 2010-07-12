package core;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import util.LoggerManager;

/**
 * class WavThread
 * @author http://www.anyexample.com/programming/java/java_play_wav_sound_file.xml
 */
public class WavThread extends Thread
{
	private static final int EXTERNAL_BUFFER_SIZE = 128 * 1024;;
	
	public enum Position
	{
		LEFT, RIGHT, NORMAL
	}
	
	private String _path;
	private Position _position;
	
	public WavThread(String path)
	{
		this(path, Position.NORMAL);
	}
	
	public WavThread(String path, Position p)
	{
		_path = path;
		_position = p;
	}
	
	public void run()
	{
		File soundFile = new File(_path);
		if (!soundFile.exists())
		{
			LoggerManager.log(LoggerManager.ERROR, "<WavThread> Wav file not found.");
			return;
		}
		
		AudioInputStream audioInputStream = null;
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		
		try
		{
			auline = (SourceDataLine)AudioSystem.getSourceDataLine(format);
			auline.open();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		if (auline.isControlSupported(FloatControl.Type.MASTER_GAIN))
		{
			FloatControl gain = (FloatControl)auline.getControl(FloatControl.Type.MASTER_GAIN);
			gain.setValue(-5.0f);
		}
		
		if (auline.isControlSupported(FloatControl.Type.PAN))
		{
			FloatControl pan = (FloatControl)auline.getControl(FloatControl.Type.PAN);
			if (_position == Position.LEFT)
				pan.setValue(-0.5f);
			else if (_position == Position.RIGHT)
				pan.setValue(0.5f);
		}
		
		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
		
		try
		{
			while (nBytesRead != -1)
			{
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
		finally
		{
			auline.drain();
			auline.close();
		}
	}
}
