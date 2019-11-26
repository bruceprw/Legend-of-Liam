package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TokenDoor extends Cell {
    private int tokenNumToUnlockDoor;
    private String path = "Images\\token_door.jpg";
	private String soundPath = "Sound\\creaking_door.mp3";
    private Image image;
    private boolean opened;

    public TokenDoor(int tokenNumToUnlockDoor) throws FileNotFoundException {
        super(false, true, false, "", Item.NONE);
        this.tokenNumToUnlockDoor = tokenNumToUnlockDoor;
        this.opened=false;
        setImage();
    }
    /*
    @Override
    public boolean isPlayerMoved() {
    }*/

	public int getTokenNum()
	{
		
		return tokenNumToUnlockDoor;
	}
	
	public void openDoor()
	{
		this.opened=true;
	}
	
	public boolean getOpened()
	{
		return opened;
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
    
    public String getString()
    {
    	return "D";
    }
}
