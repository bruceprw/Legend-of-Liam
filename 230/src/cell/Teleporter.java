package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores image, position of the other door and the sound of
 * passing through the teleporter. 
 * 
 * @author Lee Hin Man (690479)
 * @version 1.1
 *
 */
public class Teleporter extends Cell{
	
	private String path = "Images\\teleporter.png";
	private static String soundPath = "Sound\\teleport.wav";
	private Image image;
	private int pairX;
	private int pairY;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Creates a teleporter instance.
	 * @param pairX The x-coordinate of the pair.
	 * @param pairY The y-coordinate of the pair.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
	public Teleporter(int pairX,int pairY) throws FileNotFoundException
	{
		super(false, true, true, "", Item.NONE);
		setImage();
		this.pairX=pairX;
		this.pairY=pairY;
	}
	
	/**
	 * Plays the sound.
	 */
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
	
    /**
     * Gets the pair's x-coordinate.
     * @return The x-coordinate.
     */
	public int getPairX()
	{
		return pairX;
	}
	
	/**
	 * Gets the pair's y-coordinate.
	 * @return The y-coordinate.
	 */
	public int getPairY()
	{
		return pairY;
	}
	
	/**
	 * Sets the image.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}

	/**
	 * Plays the sound.
	 */
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
    /**
     * Draw this elements image.
     */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    /**
     * Get the string for output purpose.
     */
    public String getString()
    {
    	return "@";
    }
}
