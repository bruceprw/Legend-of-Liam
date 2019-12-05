package cell;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The lava tile is deadly to players and enemies however if the player has the
 * lava boots they can freely walk across.
 * 
 * @author user
 *
 */
public class Lava extends Cell {

	private String path = "Images\\updateimage\\lava.png";
	private static String soundPath = "Sound\\catch_fire.wav";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Creates instance of lava.
	 * 
	 * @throws FileNotFoundException
	 */
	public Lava() throws FileNotFoundException {
		super(true, true, false, "", Item.FIRE_BOOTS);
		setImage(path);
		this.name = "L";
	}

	/**
	 * Get the media player of the stored sound.
	 */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}

	/**
	 * Plays sound stored.
	 */
	public void playSound() {
		mediaPlayer.play();
	}

}
