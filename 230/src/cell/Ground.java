package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores the image and the sound of ground.
 * @author user
 *
 */
public class Ground extends Cell {

	private String path = "Images\\updateimage\\ground.png";
	private static String soundPath = "Sound\\foot_step.mp3";
	private Image image;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Create instance of ground.
	 * @throws FileNotFoundException
	 */
    public Ground() throws FileNotFoundException{
        super(false, true, true, "", Item.NONE);
        setImage();
    }

    /**
     * Get the media player of sound stored.
     * @return media player.
     */
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
    
    /**
     * Checks whether able to move to this cell.
     * @return
     */
    public boolean moveToCell() {
        return true;
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
	 * Draw image.
	 */
    public void draw(GraphicsContext gc,int x,int y) 
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    /**
     * Plays sound.
     */
    public void playSound()
    {
    	mediaPlayer.play();
    }
    
    /**
     * Get String for output purpose.
     * @return string in output format.
     */
    public String getString()
    {
    	return " ";
    }
}
