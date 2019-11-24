package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Water extends Cell {
	
	private String path = "Images\\water.jpg";
	private Image image;
	
    public Water() throws FileNotFoundException {
        super(true, true, false, "", Item.FLIPPER);
        setImage();
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
