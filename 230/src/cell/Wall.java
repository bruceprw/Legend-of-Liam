package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Cell {
	
	private String path = "Images\\wall.jpg";
	private Image image;
	
    public Wall() throws FileNotFoundException {
        super(false, false, false, "", Item.NONE);
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
