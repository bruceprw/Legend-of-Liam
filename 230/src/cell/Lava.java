package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Lava extends Cell {

	private String path = "Images\\fire.jpg";
	private Image image;
	
    public Lava() throws FileNotFoundException {
        super(true, true, false, "", Item.FIRE_BOOTS);
        setImage();
    }

    public String getString()
    {
    	return"L";
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
