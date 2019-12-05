package cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Stores image and sound of ColouredDoor.
 * 
 * @author user
 *
 */
public class ColouredDoor extends Cell
{

	private String colour;
	private String greenPath = "Images\\updateimage\\closed_green_door.png";
	private String redPath = "Images\\updateimage\\closed_red_door.png";
	private String yellowPath = "Images\\updateimage\\closed_yellow_door.png";
	private String bluePath = "Images\\updateimage\\closed_blue_door.png";
	private static String knockSoundPath = "Sound\\door_knock.mp3";
	private static String openSoundPath = "Sound\\creaking_door.mp3";
	private Image image;
	private boolean opened;
	private static Media knockMusic = new Media(new File(knockSoundPath).toURI().toString());
	private static Media openMusic = new Media(new File(openSoundPath).toURI().toString());
	private static MediaPlayer mediaPlayer;
	private static String openedYPath = "Images\\updateimage\\open_yellow_door.png";
	private static String openedGPath = "Images\\updateimage\\open_green_door.png";
	private static String openedRPath = "Images\\updateimage\\open_red_door.png";
	private static String openedBPath = "Images\\updateimage\\open_red_door.png";

	/**
	 * Sets the information of the door.
	 * 
	 * @param colour the colour of the door.
	 * @throws FileNotFoundException
	 */
	public ColouredDoor(String colour) throws FileNotFoundException
	{
		super(false, true, false, "", Item.GREEN_KEY);
		this.colour = colour;
		this.opened = false;
		setImage(colour);
	}

	/**
	 * Plays the sound
	 */
	public MediaPlayer getSound()
	{
		// Change the sound track as there are different "door" condition.
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
	 * Get the colour.
	 * 
	 * @return the colour
	 */
	public String getColour()
	{
		return colour;
	}

	/**
	 * Open the door.
	 */
	public void openDoor()
	{
		opened = true;
		switch (colour)
		{
		case "GREENDOOR":
			try
			{
				image = new Image(new FileInputStream(openedGPath));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			break;
		case "REDDOOR":
			try
			{
				image = new Image(new FileInputStream(openedRPath));
			}
			catch (FileNotFoundException e2)
			{
				e2.printStackTrace();
			}
			break;
		case "YELLOWDOOR":
			try
			{
				image = new Image(new FileInputStream(openedYPath));
			}
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
			break;
		case "BLUEDOOR":
			try
			{
				image = new Image(new FileInputStream(openedBPath));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			break;
		}

	}

	/**
	 * Checks whether the door is opened or not.
	 * 
	 * @return true if the door is opened.
	 */
	public boolean getOpened()
	{
		return opened;
	}

	/**
	 * Draw the door according to different colour instance.
	 */
	public void draw(GraphicsContext gc, int x, int y)
	{
		switch (colour)
		{
		case "GREENDOOR":
			gc.drawImage(image, x, y, 100, 100);
			break;
		case "REDDOOR":
			gc.drawImage(image, x, y, 100, 100);
			break;
		case "YELLOWDOOR":
			gc.drawImage(image, x, y, 100, 100);
			break;
		case "BLUEDOOR":
			gc.drawImage(image, x, y, 100, 100);
			break;
		}
	}

	/**
	 * Plays the sound that the door is closed.
	 */
	public void playKnockSound()
	{
		mediaPlayer = new MediaPlayer(knockMusic);
		mediaPlayer.play();
	}

	/**
	 * Plays the sound that the door is opened.
	 */
	public void playOpenSound()
	{
		mediaPlayer = new MediaPlayer(openMusic);
		mediaPlayer.play();
	}

	/**
	 * Get the string of the door.
	 */
	public String getString()
	{
		return colour;
	}

	/**
	 * Set the image to different colour according to the colour of the instance.
	 * 
	 * @param colour
	 * @throws FileNotFoundException
	 */
	public void setImage(String colour) throws FileNotFoundException
	{
		switch (colour)
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
