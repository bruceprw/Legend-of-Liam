package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Token extends Collectibles
{
	private String path = "Images\\token.png";
	

	
	public Token() throws FileNotFoundException
	{
		index=0;
		setImage();
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	

}
