package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Lava extends Cell {

	private String path = "Images\\fire.jpg";
	
    public Lava() {
        super(true, true, false, "", Item.FIRE_BOOTS);
    }

    public String getString()
    {
    	return"L";
    }
    
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
		Image image = new Image(new FileInputStream(path));
		gc.drawImage(image,x,y,100,100);
    }
}
