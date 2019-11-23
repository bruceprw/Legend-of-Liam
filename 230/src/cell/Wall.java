package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Cell {
	
	private String path = "Images\\wall.jpg";
	
    public Wall() {
        super(false, false, false, "", Item.NONE);
        
    } 
    
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
		Image image = new Image(new FileInputStream(path));
		gc.drawImage(image,x,y,100,100);
    }
}
