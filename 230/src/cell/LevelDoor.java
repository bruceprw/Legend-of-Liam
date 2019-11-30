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

public class LevelDoor extends Cell {

	private String path = "Images\\blue_door.jpg";
	private static String soundPath = "Sound\\creaking_door.mp3";
	private Image image;
	private int levelNo;
	private static Media music = new Media(new File(soundPath).toURI().toString());
	private static MediaPlayer mediaPlayer = new MediaPlayer(music);
	
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
			gc.fillText("Back", x, y+75);
		} else {
			gc.fillText(levelNo + "", x, y+75);
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