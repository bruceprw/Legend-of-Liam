package cell;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class for the wall class, eneimies or players can pass through it
 * 
 * @author user
 *
 */
public class Wall extends Cell {

	private String path = "Images\\wall.png";
	private static String soundPath = "Sound\\wall_hit.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Sets the instance of the wall.
	 * 
	 * @throws FileNotFoundException
	 */
	public Wall() throws FileNotFoundException {
		super(false, false, false, "", Item.NONE);
		setImage(path);
		this.name = "#";
	}

	/**
	 * Plays the sound when the player intended to go to the door.
	 */
	@Override
	public void playSound() {
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
	}

	/**
	 * Gets the sound to play for the cell
	 * 
	 * @return the mediaPlayer object to do so
	 */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}

}
