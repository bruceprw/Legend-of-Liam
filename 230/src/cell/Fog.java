package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Fog extends Cell
{

	private String path = "Images\\fog.jpg";
	private Image image;
	
	public Fog() throws FileNotFoundException
	{
		super(false, false, false, "",Item.FIRE_BOOTS);
		setImage();
		// TODO Auto-generated constructor stub
	}
	
	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}
	
	public void draw(GraphicsContext gc, int x, int y) 
	{
		gc.drawImage(image, x, y,100,100);
	}
	
}