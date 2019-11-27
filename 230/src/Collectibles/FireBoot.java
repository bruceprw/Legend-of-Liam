package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FireBoot extends Collectible
{
	private String path = "Images\\Fireboot.jpg";
    private String soundPath = "Sound\\creaking_door.mp3";
	
	public FireBoot() throws FileNotFoundException
	{
		index=5;
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
		return "O";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
	
}
