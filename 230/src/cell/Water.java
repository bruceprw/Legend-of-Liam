package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores information about a water.
 * @author user
 *
 */
public class Water extends Cell {
	
	private String path = "Images\\water.jpg";
	private String soundPath = "Sound\\water_splash.mp3";
	private Image image;
	private Media music = new Media(new File(soundPath).toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Create instance of water.
	 * @throws FileNotFoundException
	 */
    public Water() throws FileNotFoundException {
        super(true, true, false, "", Item.FLIPPER);
        setImage();
    }
	
    /**
     * Set image.
     * @throws FileNotFoundException
     */
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
    /**
     * Draw water.
     */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    /**
     * Plays sound of stepping on water.
     */
    public void playSound()
    {
    	mediaPlayer.play();
    }
    
    /**
     * String for output purpose.
     */
    public String getString()
    {
    	return "W";
    }
    
    /**
     * Get the sound player of water.
     * @return the mediaplayer.
     */
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
}
