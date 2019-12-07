package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores image and sounds of bluekey.
 * @author Andy Kuo
 *
 */
public class BlueKey extends Collectible
{
	private String path = "Images\\BK.png";
    private static String soundPath = "Sound\\pickup_keys.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Create the instance of bluekey.
	 * @throws FileNotFoundException
	 */
	public BlueKey() throws FileNotFoundException
	{
		index=3;
		setImage();
	}
	
	/**
	 * Plays the sound of blue key.
	 */
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
    /**
     * Get the string for output purpose.
     */
	public String getString()
	{
		return "BKEY";
	}
	
	/**
	 * Set the image of blue key.
	 * @throws FileNotFoundException
	 */
	private void setImage() throws FileNotFoundException
	{
		this.image = new Image (new FileInputStream(path));
	}
}
