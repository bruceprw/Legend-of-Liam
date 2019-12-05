package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cell.Cell;
import cell.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

/**
 * The door that the player pass to get to different level.
 * 
 * @author user
 *
 */
public class LevelDoor extends Cell {

	private String path = "Images\\blue_door.jpg";
	private static String soundPath = "Sound\\creaking_door.mp3";
	private Image image;
	private int levelNo;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Create the instance of the level door.
	 * 
	 * @param levelNo
	 * @throws FileNotFoundException
	 */
	public LevelDoor(int levelNo) throws FileNotFoundException {
		super(false, true, false, "", Item.NONE);
		this.levelNo = levelNo;
		setImage(path);
		this.name = " ";
	}

	/**
	 * Gets the level number 
	 * @return level number
	 */
	public int getLevelNo() {
		return levelNo;
	}
	/**
	 * Gets the sound to play for the cell
	 * @return the mediaPlayer object to do so
	 */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}

	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

	/**
	 * Draws the door to the screen 
	 */
	@Override
	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y, 100, 100);

		gc.setFont(new Font("Arial", 30));
		if (levelNo == 0) {
			gc.fillText("Back", x, y + 75);
		} else {
			gc.fillText(levelNo + "", x, y + 75);
		}
	}

	/**
	 * Plays the sound of the tile
	 */
	public void playSound() {
		mediaPlayer.play();
	}

}