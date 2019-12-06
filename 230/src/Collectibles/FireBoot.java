package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores image and sound of fire boot.
 * @author Andy Kuo
 *
 */
public class FireBoot extends Collectible
{
	private String path = "Images\\fireboots.png";
    private static String soundPath = "Sound\\putting_on_shoes.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Creates instance of a fireboot.
	 * @throws FileNotFoundException
	 */
	public FireBoot() throws FileNotFoundException
	{
		index=5;
		setImage();
	}
	
	/**
	 * Plays the sound of the fireboot.
	 */
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
    /**
     * Get the string of the fireboot for output purpose.
     */
	public String getString()
	{
		return "O";
	}
	
	/**
	 * Set the image.
	 * @throws FileNotFoundException
	 */
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
	
}
