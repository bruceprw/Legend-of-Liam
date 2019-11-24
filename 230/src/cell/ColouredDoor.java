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
	private Image image;
	
    public ColouredDoor(String colour) throws FileNotFoundException {
        super(false, true, false, "", Item.GREEN_KEY);
        this.colour = colour;
        setImage(colour);
    }
    
    public String getColour()
    {
    	return colour;
    }
    
    public void draw(GraphicsContext gc,int x,int y)
    {
    	switch(colour)
    	{
    	case "Green":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	case "Red":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	case "Yellow":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	case "Blue":
    		gc.drawImage(image,x,y,100,100);
    		break;
    	}
    }
    
    public void setImage (String colour) throws FileNotFoundException
    {
    	switch(colour)
    	{
    	case "Green":
    		image = new Image(new FileInputStream(greenPath));
    		break;
    	case "Red":
    		image = new Image(new FileInputStream(redPath));
    		break;
    	case "Yellow":
    		image = new Image(new FileInputStream(yellowPath));
    		break;
    	case "Blue":
    		image = new Image(new FileInputStream(bluePath));
    		break;
    	}
    }
}
