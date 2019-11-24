package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RedKey extends Collectibles
{
	private String path = "Images\\RK.png";

	
	public RedKey() throws FileNotFoundException
	{
		index=1;
		setImage();
	}
	
	public String getString()
	{
		return "RKEY";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

}
