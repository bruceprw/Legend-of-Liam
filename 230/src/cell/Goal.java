package cell;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class for the end goal of each level.
 * @author user
 *
 */
public class Goal extends Cell
{

	private String path = "Images\\goal.png";
	private static String soundPath = "Sound\\BGM\\Mournful_DepartureGOAL.mp3";
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Creates instance of goal.
	 * @throws FileNotFoundException
	 */
	public Goal() throws FileNotFoundException
	{
		super(false, true, false, "", Item.NONE);
		setImage(path);
	}

	/**
	 * @return the media player of goal.
	 */
	public MediaPlayer getSound()
	{
		return mediaPlayer;
	}
	
	/**
	 * @return string for output purpose.
	 */
	public String getString()
	{
		return "G";
	}

	/**
	 * Plays sound.
	 */
	public void playSound()
	{
		mediaPlayer.play();
	}
}