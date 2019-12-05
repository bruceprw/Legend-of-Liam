package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class for the token door, requires a certin amount of tokens for the player
 * to open it.
 * 
 * @author user
 *
 */
public class TokenDoor extends Cell {
	/* The token number needed to unlock the door. */
	private int tokenNumToUnlockDoor;
	private String path = "Images\\token_door.png";
	private static String knockSoundPath = "Sound\\door_knock.mp3";
	private static String openSoundPath = "Sound\\creaking_door.mp3";
	private static Media knockMusic = new Media(new File(knockSoundPath).toURI().toString());
	private static Media openMusic = new Media(new File(openSoundPath).toURI().toString());
	private static MediaPlayer mediaPlayer;
	private boolean opened;
	private static String openedDoorPath = "Images\\open_token_door.png";

	/**
	 * Class constructor for the token door.
	 * 
	 * @param tokenNumToUnlockDoor number of tokens need to open the door.
	 * @throws FileNotFoundException
	 */
	public TokenDoor(int tokenNumToUnlockDoor) throws FileNotFoundException {
		super(false, true, false, "", Item.NONE);
		this.tokenNumToUnlockDoor = tokenNumToUnlockDoor;
		this.opened = false;
		setImage(path);
		this.name = "D";
	}

	/**
	 * Gets the media player of the door.
	 * 
	 * @return media player that stores the sound.
	 * 
	 */
	public MediaPlayer getSound() {
		if (opened) {
			playOpenSound();
		} else {
			playKnockSound();
		}
		return mediaPlayer;
	}

	/**
	 * Get the token number needed to open door.
	 * 
	 * @return the number
	 */
	public int getTokenNum() {
		return tokenNumToUnlockDoor;
	}

	/**
	 * sets the door to open.
	 */
	public void openDoor() {
		this.opened = true;
		try {
			image = new Image(new FileInputStream(openedDoorPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * return the state of the door.
	 * 
	 * @return true if the door is opened.
	 */
	public boolean getOpened() {
		return opened;
	}

	/**
	 * Plays the sound that the door isn't opened.
	 */
	public void playKnockSound() {
		mediaPlayer = new MediaPlayer(knockMusic);
		mediaPlayer.play();
	}

	/**
	 * Plays the sound that the door is opening.
	 */
	public void playOpenSound() {
		mediaPlayer = new MediaPlayer(openMusic);
		mediaPlayer.play();
	}

}
