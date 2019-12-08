package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores images and sound of lava.
 * 
 * @author Lee Hin Man (690479)
 * @version 1.1
 *
 */
public class Lava extends Cell {

	private String path = "Images\\updateimage\\lava.png";
	private static String soundPath = "Sound\\catch_fire.wav";
	private Image image;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
	/**
	 * Creates instance of lava.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
    public Lava() throws FileNotFoundException {
        super(true, true, false, "", Item.FIRE_BOOTS);
        setImage();
    }

    /**
     * Get the media player of the stored sound.
     */
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
    
    /**
     * Returns the lava map character.
     * @return the string for output purpose.
     */
    public String getString()
    {
    	return"L";
    }
    
    /**
     * Set to the lava image.
     * @throws FileNotFoundException Tells the system that this
	 * method could cause an error such as unable to access a file.
     */
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
	/**
	 * Plays sound stored.
	 */
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
    /**
     * Draw the lava image.
     */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
}
