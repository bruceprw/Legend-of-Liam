package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Teleporter extends Cell{
	
	private String path = "Images\\teleporter.jpg";
	private static String soundPath = "Sound\\teleport.wav";
	private Image image;
	private int pairX;
	private int pairY;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);

	public Teleporter(int pairX,int pairY) throws FileNotFoundException
	{
		super(false, true, true, "", Item.NONE);
		setImage();
		this.pairX=pairX;
		this.pairY=pairY;
	}
	
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
	
	public int getPairX()
	{
		return pairX;
	}
	
	public int getPairY()
	{
		return pairY;
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
    
    public String getString()
    {
    	return "@";
    }
}
