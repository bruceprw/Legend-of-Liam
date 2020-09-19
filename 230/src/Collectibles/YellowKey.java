package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores the image and sounds of a yellow key.
 * 
 * @author Andy Kuo
 *
 */
public class YellowKey extends Collectible {
	private String path = "230" + File.separatorChar + "Images" + File.separatorChar + "YK.png";
	private static String soundPath = "230" + File.separatorChar + "Sound" + File.separatorChar + "pickup_keys.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Set the image of the key.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such not finding given file.
	 */
	public YellowKey() throws FileNotFoundException {
		index = 4;
		setImage();
	}

	/**
	 * Plays the sound of the key.
	 */
	public void playSound() {
		mediaPlayer.play();
	}

	/**
	 * Get the string for output purpose.
	 * 
	 * @return The String representation of the key.
	 */
	public String getString() {
		return "YKEY";
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
