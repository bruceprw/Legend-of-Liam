package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Lava extends Cell {

	private String path = "Images\\fire.jpg";
	private static String soundPath = "Sound\\catch_fire.wav";
	private Image image;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
    public Lava() throws FileNotFoundException {
        super(true, true, false, "", Item.FIRE_BOOTS);
        setImage();
    }

    
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
    public String getString()
    {
    	return"L";
    }
    
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
    public void playSound()
    {
    	mediaPlayer.play();
    }
	
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
}
