package Collectibles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RedKey extends Element
{
	private String path = "Images\\RK.png";
	
	public String getString()
	{
		return "RKEY";
	}
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
		Image image = new Image(new FileInputStream(path));
		gc.drawImage(image,x,y,100,100);
    }
}
