package cell;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

/**
 * Class is used to create a player on the screen. 
 * 
 * @author Lee Hin Man (690479)
 * @version 1.1
 */
public class Cell extends Element
{
	private MediaPlayer mediaPlayer;
	protected Image image;

	public Cell(boolean harmable, boolean playerAllowed, boolean enemyAllowed, String texturePath, Item itemRequiredForCell)
	{
		
	}
	
	/**
	 * Get the image of the collectible.
	 * @return
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Draw the image.
	 */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
	
    /**
     * Gets the media player of the door.
     * @return media player that stores the sound.
     * 
     */
	public MediaPlayer getSound()
	{
		return mediaPlayer;
	}
	
	/**
	 * Plays the sound.
	 */
    public void playSound()
    {

    }
    
	/**
	 * returns the class as a string
	 * 
	 * @return class name.
	 */
	public String getString() 
	{
		return "";
	}
}