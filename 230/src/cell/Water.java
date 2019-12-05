package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The class for the water tile, can only be accessed by the player when they acquire the flipper item
 * @author user
 *
 */
public class Water extends Cell {
	
	private String path = "Images\\\\updateimage\\water.png";
	private String soundPath = "Sound\\water_splash.mp3";
	private Media music = new Media(new File(soundPath).toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	
	/**
	 * Constructor for water class, 
	 * @throws FileNotFoundException
	 */
    public Water() throws FileNotFoundException {
        super(true, true, false, "", Item.FLIPPER);
        setImage(path);
        this.name = "W";
    }
    
    /**
     * Plays sound of stepping on water.
     */
    public void playSound()
    {
    	mediaPlayer.play();
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
