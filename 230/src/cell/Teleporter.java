package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The teleporter tile allows for players to walk through one and pop out the
 * other tile.
 * 
 * @author user
 *
 */
public class Teleporter extends Cell {

	private String path = "Images\\teleporter.png";
	private static String soundPath = "Sound\\teleport.wav";
	private int pairX;
	private int pairY;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Creates a teleporter
	 * 
	 * @param pairX the x-coordinate of the pair.
	 * @param pairY the y-coordinate of the pair.
	 * @throws FileNotFoundException
	 */
	public Teleporter(int pairX, int pairY) throws FileNotFoundException {
		super(false, true, true, "", Item.NONE);
		setImage(path);
		this.name = "@";
		this.pairX = pairX;
		this.pairY = pairY;
	}

	/**
	 * Plays the sound.
	 */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}

	/**
	 * Get the pair's x-coordinate.
	 * 
	 * @return the x-coordinate
	 */
	public int getPairX() {
		return pairX;
	}

	/**
	 * Get the pair's y-coordinate.
	 * 
	 * @return the y-coordinate.
	 */
	public int getPairY() {
		return pairY;
	}

	/**
	 * Plays the sound.
	 */
	public void playSound() {
		mediaPlayer.play();
	}
}
