package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Fog extends Cell
{

	private String path = "Images\\fog.jpg";
	
	public Fog(Item itemRequiredForCell)
	{
		super(false, false, false, "", itemRequiredForCell);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(GraphicsContext gc, int x, int y) throws FileNotFoundException
	{
		Image image = new Image(new FileInputStream(path));
		gc.drawImage(image, x, y,100,100);
	}
	
}
