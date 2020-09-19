package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores the info. of red key.
 * 
 * @author Andy Kuo
 *
 */
public class RedKey extends Collectible {
	private String path = "230" + File.separatorChar + "Images" + File.separatorChar + "RK.png";
	private static String soundPath = "230" + File.separatorChar + "Sound" + File.separatorChar + "pickup_keys.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Create the instance of red key.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such as not finding given file.
	 */
	public RedKey() throws FileNotFoundException {
		index = 1;
		setImage();
	}

	/**
	 * Plays the sound of red key.
	 */
	public void playSound() {
		mediaPlayer.play();
	}

	/**
	 * Get the string of output purpose.
	 * 
	 * @return The key string format.
	 */
	public String getString() {
		return "RKEY";
	}

	/**
	 * Set the image.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such as not finding given file.
	 */
	private void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

}
