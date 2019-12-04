package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores the image and sound of goal.
 * @author user
 *
 */
public class Goal extends Cell
{

	private String path = "Images\\goal.png";
	private static String soundPath = "Sound\\BGM\\Mournful_DepartureGOAL.mp3";
	private Image image;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Creates instance of goal.
	 * @throws FileNotFoundException
	 */
	public Goal() throws FileNotFoundException
	{
		super(false, true, false, "", Item.NONE);
		setImage();
	}

	/**
	 * @return the media player of goal.
	 */
	public MediaPlayer getSound()
	{
		return mediaPlayer;
	}

	/**
	 * Sets image.
	 * @throws FileNotFoundException
	 */
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

	/**
	 * Draw image.
	 */
	public void draw(GraphicsContext gc, int x, int y)
	{
		gc.drawImage(image, x, y, 100, 100);
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