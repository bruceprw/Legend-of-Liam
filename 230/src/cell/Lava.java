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
	private String soundPath = "Sound\\Water Splash-SoundBible.com-800223477.mp3";
	private Image image;
	
    public Lava() throws FileNotFoundException {
        super(true, true, false, "", Item.FIRE_BOOTS);
        setImage();
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
    	Media music = new Media(new File(soundPath).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(music);
    	mediaPlayer.play();
    }
	
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
}
