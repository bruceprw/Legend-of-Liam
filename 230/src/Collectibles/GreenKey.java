package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GreenKey extends Collectible
{
	private String path = "Images\\GK.png";
    private String soundPath = "Sound\\pickup_keys.ogg";
    
	public GreenKey() throws FileNotFoundException
	{
		index=2;
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
		return "GKEY";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	

}
