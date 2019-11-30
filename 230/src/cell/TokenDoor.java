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
	private static String knockSoundPath = "Sound\\door_knock.mp3";
	private static String openSoundPath = "Sound\\creaking_door.mp3";
	private static Media knockMusic = new Media(new File(knockSoundPath).toURI().toString());
	private static Media openMusic = new Media(new File(openSoundPath).toURI().toString());
	private static MediaPlayer mediaPlayer;
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

    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
    
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
	
    public void playKnockSound()
    {
    	mediaPlayer = new MediaPlayer(knockMusic);
    	mediaPlayer.play();
    }
    
    public void playOpenSound()
    {
    	mediaPlayer=new MediaPlayer(openMusic);
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
