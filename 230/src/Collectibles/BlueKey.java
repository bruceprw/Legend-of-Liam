package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BlueKey extends Collectibles
{
	private String path = "Images\\BK.png";
	
	public BlueKey() throws FileNotFoundException
	{
		index=3;
		setImage();
	}
	
	public String getString()
	{
		return "BKEY";
	}
	
	public void setImage() throws FileNotFoundException
	{
		this.image = new Image (new FileInputStream(path));
	}
	

    
}
