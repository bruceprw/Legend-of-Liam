package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.canvas.GraphicsContext;

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
    	Media music = new Media(new File(filepath).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(music);
    	mediaPlayer.play();
    
    }
}
