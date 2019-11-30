package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Wall extends Cell {
	
	private String path = "Images\\wall.jpg";
	private static String soundPath = "Sound\\wall_hit.mp3";
	private Image image;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
    public Wall() throws FileNotFoundException {
        super(false, false, false, "", Item.NONE);
        setImage();
    } 
    
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
    public void playSound()
    {
    	mediaPlayer.setAutoPlay(true);
    	mediaPlayer.play();
    }
	
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
    
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    public String getString()
    {
    	return "#";
    }
}
