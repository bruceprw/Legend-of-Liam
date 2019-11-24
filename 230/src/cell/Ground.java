package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ground extends Cell {

	private String path = "Images\\ground.jpg";
	private Image image;
	
    public Ground() throws FileNotFoundException{
        super(false, true, true, "", Item.NONE);
        setImage();
    }

    
    public boolean moveToCell() {
        return true;
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
