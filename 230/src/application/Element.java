package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javafx.scene.canvas.GraphicsContext;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Element
{
	public String getString()
	{
		return"";
	}
	
	public void draw(GraphicsContext gc, int x,int y)
	{
		
	}

    public void playSound(String filepath) throws FileNotFoundException, IOException
    {
    	InputStream music = new FileInputStream(new File(filepath));
    		AudioStream audio = new AudioStream(music);
    		AudioPlayer.player.start(audio);
    
    }
}
