package cell;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Ground class used as just default ground cell, a majority of maps are made up
 * with this cell.
 * 
 * @author user
 *
 */
public class Ground extends Cell {

	private String path = "Images\\updateimage\\ground.png";
	private static String soundPath = "Sound\\foot_step.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Create instance of ground.
	 * 
	 * @throws FileNotFoundException
	 */
	public Ground() throws FileNotFoundException {
		super(false, true, true, "", Item.NONE);
		setImage(path);
	}

	/**
	 * Checks whether player of enemy is able to move to this cell.
	 * 
	 * @return always true
	 */
	public boolean moveToCell() {
		return true;
	}

	/**
	 * Plays cell sound effect.
	 */
	public void playSound() {
		mediaPlayer.play();
	}

	/**
	 * Get the media player of sound stored.
	 * 
	 * @return media player.
	 */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}

	/**
	 * Return a string that indicates the type to the fileOutputer class.
	 * 
	 * @return string in output format.
	 */
	public String getString() {
		return " ";
	}
}
