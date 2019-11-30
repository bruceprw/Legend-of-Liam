package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Token extends Collectible
{
	private String path = "Images\\token.png";
    private static String soundPath = "Sound\\coin_sound.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	public Token() throws FileNotFoundException
	{
		index=0;
		setImage();
	}
	
    public void playSound()
    {

    	mediaPlayer.play();
    }
	
	public Image getImage()
	{
		return image;
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	
	public String getString()
	{
		return "T";
	}

}
