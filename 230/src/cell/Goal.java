package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Goal extends Cell {

	private String path = "Images\\goal.png";
	
    public Goal() {
        super(false, true, false, "", Item.NONE);
    }

    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
		Image image = new Image(new FileInputStream(path));
		gc.drawImage(image,x,y,50,50);
    }
}