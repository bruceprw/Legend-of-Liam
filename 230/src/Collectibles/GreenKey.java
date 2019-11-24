package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GreenKey extends Collectibles
{
	private String path = "Images\\GK.png";

	
	public GreenKey() throws FileNotFoundException
	{
		index=2;
		setImage();
	}
	
	public String getString()
	{
		return "GKEY";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	

}
