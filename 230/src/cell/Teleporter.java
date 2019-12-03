package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores image, position of the other door and the sound of passing through the teleporter. 
 * @author user
 *
 */
public class Teleporter extends Cell{
	
	private String path = "Images\\teleporter.jpg";
	private static String soundPath = "Sound\\teleport.wav";
	private Image image;
	private int pairX;
	private int pairY;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	/**
	 * Creates a teleporter instance
	 * @param pairX the x-coordinate of the pair.
	 * @param pairY the y-coordinate of the pair.
	 * @throws FileNotFoundException
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
     * Get the pair's x-coordinate.
     * @return the x-coordinate
     */
	public int getPairX()
	{
		return pairX;
	}
	
	/**
	 * Get the pair's y-coordinate.
	 * @return the y-coordinate.
	 */
	public int getPairY()
	{
		return pairY;
	}
	
	/**
	 * Set the image.
	 * @throws FileNotFoundException
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
     * draw the image.
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
