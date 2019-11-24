package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Flipper extends Collectibles
{
	private String path = "Images\\flipper.jpg";

	
	public Flipper() throws FileNotFoundException
	{
		index=6;
		setImage();
	}
	
	public String getString()
	{
		return "F";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	
	

}
