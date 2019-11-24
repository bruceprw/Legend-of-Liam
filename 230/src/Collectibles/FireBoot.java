package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FireBoot extends Collectible
{
	private String path = "Images\\Fireboot.jpg";
	
	public FireBoot() throws FileNotFoundException
	{
		index=5;
		setImage();
	}
	
	public String getString()
	{
		return "O";
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
	
}
