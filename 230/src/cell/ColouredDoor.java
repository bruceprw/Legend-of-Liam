package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ColouredDoor extends Cell {

	private String colour;
	private String greenPath = "Images\\green_door.jpg";
	private String redPath = "Images\\red_door.jpg";
	private String yellowPath = "Images\\yellow_door.jpg";
	private String bluePath = "Images\\blue_door.jpg";
	
    public ColouredDoor(String colour) {
        super(false, true, false, "", Item.GREEN_KEY);
        this.colour = colour;
    }
    
    public String getColour()
    {
    	return colour;
    }
    
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
    	switch(colour)
    	{
    	case "Green":
    		Image image = new Image(new FileInputStream(greenPath));
    		gc.drawImage(image,x,y,50,50);
    		break;
    	case "Red":
    		Image image1 = new Image(new FileInputStream(greenPath));
    		gc.drawImage(image1,x,y,50,50);
    		break;
    	case "Yellow":
    		Image image2 = new Image(new FileInputStream(greenPath));
    		gc.drawImage(image2,x,y,50,50);
    		break;
    	case "Blue":
    		Image image3 = new Image(new FileInputStream(greenPath));
    		gc.drawImage(image3,x,y,50,50);
    		break;
    	}
    }
}
