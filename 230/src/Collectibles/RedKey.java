package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RedKey extends Collectible
{
	private String path = "Images\\RK.png";
    private String soundPath = "Sound\\pickup_keys.ogg";
	
	public RedKey() throws FileNotFoundException
	{
		index=1;
		setImage();
	}
	
    public void playSound()
    {
    	Media music = new Media(new File(soundPath).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(music);
    	mediaPlayer.play();
    }
	
	public String getString()
	{
		return "RKEY";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

}
