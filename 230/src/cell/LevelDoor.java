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
 * @author Lee Hin Man (690479)
 * @version 1.1
 *
 */
public class LevelDoor extends Cell {

	private String path = "Images\\updateimage\\closed_blue_door.png";
	private static String soundPath = "Sound\\creaking_door.mp3";
	private Image image;
	private int levelNo;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	private static final int NUM_Y_OFFSET = 90;
	private static final int ONE_DIGIT_X_OFFSET = 15;
	private static final int TWO_DIGIT_X_OFFSET = 8;
	
	/**
	 * Create the instance of the level door.
	 * 
	 * @param levelNo The level number the door corresponds to.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
	public LevelDoor(int levelNo) throws FileNotFoundException {
		super(false, true, false, "", Item.NONE);
		this.levelNo = levelNo;
		setImage();
	}

    /**
     * Checks whether able to move to this cell.
     * @return Returns true to allow the 
     * player to move.
     */
	public boolean moveToCell() {
		return true;
	}

    /**
     * Get the level number.
     * @return levelNo. The given doors corresponding
     * level.
     */
	public int getLevelNo() {
		return levelNo;
	}

    /**
     * Get the sound player of LevelDoor.
     * @return The mediaplayer.
     */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}

    /**
     * Set image.
     * @throws FileNotFoundException Tells the system that this
	 * method could cause an error such as unable to access a file.
     */
	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

    /**
     * Draw LevelDoor.
     */
	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y, 100, 100);

		gc.setFont(new Font("Arial", 30));
		if (levelNo > 0 && levelNo < 10) {
			gc.fillText(levelNo + "", x + ONE_DIGIT_X_OFFSET, y + NUM_Y_OFFSET);
		} else if (levelNo >= 10) {
			gc.fillText(levelNo + "", x + TWO_DIGIT_X_OFFSET, y + NUM_Y_OFFSET);
		}
	}

    /**
     * Plays sound of open door.
     */
	public void playSound() {
		mediaPlayer.play();
	}

    /**
     * String for output purpose.
     */
	public String getString() {
		return " ";
	}
}