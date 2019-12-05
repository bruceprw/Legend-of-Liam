package cell;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cell.Cell;
import cell.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

/**
 * The door that the player pass to get to different level.
 * @author user
 *
 */
public class LevelDoor extends Cell {

	private String path = "Images\\updateimage\\closed_blue_door.png";
	private static String soundPath = "Sound\\creaking_door.mp3";
	private Image image;
	private int levelNo;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	private static final int NUM_Y_OFFSET = 90;
	private static final int BACK_X_OFFSET = 8;
	private static final int SINGLE_DIGIT_X_OFFSET = 15;
	private static final int DOUBLE_DIGIT_X_OFFSET = 8;
	
	/**
	 * Create the instance of the level door.
	 * @param levelNo
	 * @throws FileNotFoundException
	 */
    public LevelDoor(int levelNo) throws FileNotFoundException{
        super(false, true, false, "", Item.NONE);
        this.levelNo = levelNo;
        setImage();
    }

    
    public boolean moveToCell() {
        return true;
    }
    
    public int getLevelNo() {
    	return levelNo;
    }
    
    public MediaPlayer getSound()
    {
    	return mediaPlayer;
    }
    
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
    
    public void draw(GraphicsContext gc,int x,int y) 
    {
		gc.drawImage(image,x,y,100,100);
		
		gc.setFont(new Font("Arial", 30));
		if (levelNo == 0) {
			gc.fillText("<-", x + BACK_X_OFFSET, y + NUM_Y_OFFSET);
		} else if (levelNo > 0 && levelNo < 10){
			gc.fillText(levelNo + "", x + SINGLE_DIGIT_X_OFFSET, y + NUM_Y_OFFSET);
		} else {
			gc.fillText(levelNo + "", x + DOUBLE_DIGIT_X_OFFSET, y + NUM_Y_OFFSET);
		}
    }
    
    public void playSound()
    {
    	mediaPlayer.play();
    }
    
    public String getString()
    {
    	return " ";
    }
}