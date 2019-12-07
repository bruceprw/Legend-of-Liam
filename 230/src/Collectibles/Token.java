package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores the image and the sound of a token.
 * @author Andy Kuo
 *
 */
public class Token extends Collectible
{
	private String path = "Images\\token.png";
    private static String soundPath = "Sound\\coin_sound.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Create a instance.
	 * @throws FileNotFoundException
	 */
	public Token() throws FileNotFoundException
	{
		index=0;
		setImage();
	}
	
	/**
	 * Plays the sound of the token.
	 */
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
    /**
     * Get the image of the token
     * @return the image.
     */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Set the image of the token
	 * @throws FileNotFoundException
	 */
	private void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	
	/**
	 * Get the string of file output purpose.
	 */
	public String getString()
	{
		return "T";
	}

}
