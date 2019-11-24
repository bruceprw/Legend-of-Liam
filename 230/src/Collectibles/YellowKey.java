package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class YellowKey extends Collectibles
{
	private String path = "Images\\YK.png";

	
	public YellowKey() throws FileNotFoundException
	{
		index=4;
		setImage();
	}
	
	public String getString()
	{
		return "YKEY";
	}
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

    
}
