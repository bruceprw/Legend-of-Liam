package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Ground extends Cell {

	private String path = "Images\\ground.jpg";
	private String soundPath = "Sound\\Water Splash-SoundBible.com-800223477.mp3";
	private Image image;
	
    public Ground() throws FileNotFoundException{
        super(false, true, true, "", Item.NONE);
        setImage();
    }

    
    public boolean moveToCell() {
        return true;
    }
    
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
    public void draw(GraphicsContext gc,int x,int y) 
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    public void playSound()
    {
    	Media music = new Media(new File(soundPath).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(music);
    	mediaPlayer.play();
    }
    
    public String getString()
    {
    	return " ";
    }
}
