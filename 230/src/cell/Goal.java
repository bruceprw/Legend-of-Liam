package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Goal extends Cell {

	private String path = "Images\\goal.png";
	private Image image;
	
    public Goal() throws FileNotFoundException {
        super(false, true, false, "", Item.NONE);
        setImage();
    }

    public void setImage() throws FileNotFoundException
    {
    	Image image = new Image(new FileInputStream(path));
    }
    
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
}