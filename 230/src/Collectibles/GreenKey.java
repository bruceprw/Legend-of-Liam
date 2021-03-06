package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores information for green key.
 * 
 * @author Andy Kuo
 *
 */
public class GreenKey extends Collectible {
	private String path = "230" + File.separatorChar + "Images" + File.separatorChar + "GK.png";
	private static String soundPath = "230" + File.separatorChar + "Sound" + File.separatorChar + "pickup_keys.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Create instance of the key.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such as not finding given file.
	 */
	public GreenKey() throws FileNotFoundException {
		index = 2;
		setImage();
	}

	/**
	 * Plays the sound of green key.
	 */
	public void playSound() {
		mediaPlayer.play();
	}

	/**
	 * Get the String for output purpose.
	 * 
	 * @return the output format of the key.
	 */
	public String getString() {
		return "GKEY";
	}

	/**
	 * Set the image of green key.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such as not finding given file.
	 */
	private void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

}
