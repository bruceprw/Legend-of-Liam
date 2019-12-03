package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ColouredDoor extends Cell {

	private String colour;
	private String greenPath = "Images\\green_door.jpg";
	private String redPath = "Images\\red_door.jpg";
	private String yellowPath = "Images\\yellow_door.jpg";
	private String bluePath = "Images\\blue_door.jpg";
	private static String knockSoundPath = "Sound\\door_knock.mp3";
	private static String openSoundPath = "Sound\\creaking_door.mp3";
	private Image image;
	private boolean opened;
	private static Media knockMusic = new Media(new File(knockSoundPath).toURI().toString());
	private static Media openMusic = new Media(new File(openSoundPath).toURI().toString());
	private static MediaPlayer mediaPlayer;
	
	
	
    public ColouredDoor(String colour) throws FileNotFoundException {
        super(false, true, false, "", Item.GREEN_KEY);
        this.colour = colour;
        this.opened=false;
        setImage(colour);
    }
    
    public MediaPlayer getSound()
    {
    	if(opened)
    	{
    		playOpenSound();
    	}
    	else
    	{
    		playKnockSound();
    	}
    	
    	return mediaPlayer;
    }
    
    public String getColour()
    {
    	return colour;
    }
    
    public void openDoor()
    {
    	opened=true;
    }
    
	public boolean getOpened()
	{
		return opened;
	}
    
    public void draw(GraphicsContext gc,int x,int y)
    {
    	switch(colour)
    	{
    	case "GREENDOOR":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	case "REDDOOR":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	case "YELLOWDOOR":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	case "BLUEDOOR":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	}
    }
    
    public void playKnockSound()
    {
    	mediaPlayer = new MediaPlayer(knockMusic);
    	mediaPlayer.play();
    }
    
    public void playOpenSound()
    {
    	mediaPlayer = new MediaPlayer(openMusic);
    	mediaPlayer.play();
    }
    
    public String getString()
    {
    	return colour;
    }
    
    public void setImage (String colour) throws FileNotFoundException
    {
    	switch(colour)
    	{
    	case "GREENDOOR":
    		image = new Image(new FileInputStream(greenPath));
    		break;
    	case "REDDOOR":
    		image = new Image(new FileInputStream(redPath));
    		break;
    	case "YELLOWDOOR":
    		image = new Image(new FileInputStream(yellowPath));
    		break;
    	case "BLUEDOOR":
    		image = new Image(new FileInputStream(bluePath));
    		break;
    	}
    }
}
