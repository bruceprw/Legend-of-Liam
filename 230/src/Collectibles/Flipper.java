package Collectibles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Store information of flippers.
 * 
 * @author Andy Kuo
 *
 */
public class Flipper extends Collectible {
	private String path = "230" + File.separatorChar + "Images" + File.separatorChar + "flipper.png";
	private static String soundPath = "230" + File.separatorChar + "Sound" + File.separatorChar + "putting_on_shoes.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Create instance of flipper.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such as not finding given file.
	 */
	public Flipper() throws FileNotFoundException {
		index = 6;
		setImage();
	}

	/**
	 * Plays sound of wearing the flipper.
	 */
	public void playSound() {
		mediaPlayer.play();
	}

	/**
	 * Get the String for output purpose.
	 */
	public String getString() {
		return "F";
	}

	/**
	 * Set the image of flipper.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error such as not finding given file.
	 */
	private void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}
}
