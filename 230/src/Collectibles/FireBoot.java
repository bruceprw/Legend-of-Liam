package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FireBoot extends Element
{
	private String path = "Images\\Fireboot.jpg";
	private Image image;
	
	public FireBoot() throws FileNotFoundException
	{
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
	
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
}
