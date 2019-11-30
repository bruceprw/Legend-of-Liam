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
    private static String soundPath = "Sound\\putting_on_shoes.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	public FireBoot() throws FileNotFoundException
	{
		index=5;
		setImage();
	}
	
    public void playSound()
    {
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
