package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BlueKey extends Collectible
{
	private String path = "Images\\BK.png";
    private static String soundPath = "Sound\\pickup_keys.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	public BlueKey() throws FileNotFoundException
	{
		index=3;
		setImage();
	}
	
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
	public String getString()
	{
		return "BKEY";
	}
	
	public void setImage() throws FileNotFoundException
	{
		this.image = new Image (new FileInputStream(path));
	}
	

    
}
