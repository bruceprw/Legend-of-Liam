package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The player and enemy cannot pass through the wall.
 * @author user
 *
 */
public class Wall extends Cell {
	
	private String path = "Images\\wall.png";
	private static String soundPath = "Sound\\wall_hit.mp3";
	private Image image;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Sets the instance of the wall.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
    public Wall() throws FileNotFoundException {
        super(false, false, false, "", Item.NONE);
        setImage();
    } 
    
    /**
     * Get the image from the path.
     * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
     */
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
	/**
	 * Plays the sound when the player intended to go to the door.
	 */
    public void playSound()
    {
    	mediaPlayer.setAutoPlay(true);
    	mediaPlayer.play();
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
     * Draw the door image.
     */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    /**
     * Get the string for file outputer.
     */
    public String getString()
    {
    	return "#";
    }
}
