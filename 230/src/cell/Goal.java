package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Goal extends Cell {

	private String path = "Images\\goal.png";
	private String soundPath = "Sound\\goal_applause.wav";
	private Image image;
	
    public Goal() throws FileNotFoundException {
        super(false, true, false, "", Item.NONE);
        setImage();
    }

    public void setImage() throws FileNotFoundException
    {
    	Image image = new Image(new FileInputStream(path));
    }
    
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    public String getString()
    {
    	return "G";
    }
    
    public void playSound()
    {
    	Media music = new Media(new File(soundPath).toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(music);
    	mediaPlayer.play();
    }
}