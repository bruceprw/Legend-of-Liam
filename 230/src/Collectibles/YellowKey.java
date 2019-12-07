package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores the image and sounds of a yellow key.
 * @author Andy Kuo
 *
 */
public class YellowKey extends Collectible
{
	private String path = "Images\\YK.png";
    private static String soundPath = "Sound\\pickup_keys.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Set the image of the key.
	 * @throws FileNotFoundException
	 */
	public YellowKey() throws FileNotFoundException
	{
		index=4;
		setImage();
	}
	
	/**
	 * Plays the sound of the key.
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
		return "YKEY";
	}
	
	/**
	 * Set the image.
	 * @throws FileNotFoundException
	 */
	private void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

    
}
