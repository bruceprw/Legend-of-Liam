package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores information of tokendoor.
 * @author user
 *
 */
public class TokenDoor extends Cell {
	/*The token number needed to unlock the door.*/
    private int tokenNumToUnlockDoor;
    private String path = "Images\\token_door.png";
	private static String knockSoundPath = "Sound\\door_knock.mp3";
	private static String openSoundPath = "Sound\\creaking_door.mp3";
	private static Media knockMusic = new Media(new File(knockSoundPath).toURI().toString());
	private static Media openMusic = new Media(new File(openSoundPath).toURI().toString());
	private static MediaPlayer mediaPlayer;
    private Image image;
    private boolean opened;
    private static String openedDoorPath = "Images\\open_token_door.png";
    
    /**
     * Create instance of the door.
     * @param tokenNumToUnlockDoor number of tokens need to open the door.
     * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
     */
    public TokenDoor(int tokenNumToUnlockDoor) throws FileNotFoundException {
        super(false, true, false, "", Item.NONE);
        this.tokenNumToUnlockDoor = tokenNumToUnlockDoor;
        this.opened=false;
        setImage();
    }

    /**
     * Gets the media player of the door.
     * @return media player that stores the sound.
     * 
     */
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
    
    /**
     * Get the token number needed to open door.
     * @return Number of tokens.
     */
	public int getTokenNum()
	{
		return tokenNumToUnlockDoor;
	}
	
	/**
	 * Open the door.
	 */
	public void openDoor()
	{
		this.opened=true;
		try
		{
			image = new Image(new FileInputStream(openedDoorPath));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * return the state of the door.
	 * @return true if the door is opened.
	 */
	public boolean getOpened()
	{
		return opened;
	}
	
	/**
	 * Set the image.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
	
	/**
	 * Plays the sound that the door isn't opened.
	 */
    public void playKnockSound()
    {
    	mediaPlayer = new MediaPlayer(knockMusic);
    	mediaPlayer.play();
    }
    
    /**
     * Plays the sound that the door is opening.
     */
    public void playOpenSound()
    {
    	mediaPlayer=new MediaPlayer(openMusic);
    	mediaPlayer.play();
    }
	
    /**
     * Draw the door.
     */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    /**
     * Get the String for output purpose.
     */
    public String getString()
    {
    	return "D";
    }
}
