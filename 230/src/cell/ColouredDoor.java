package cell;

public class ColouredDoor extends Cell {

	private String colour;
	private String greenPath = "Images\\green_door.png";
	private String redPath = "Images\\red_door.jpg";
	private String yellowPath = "Images\\yellow_door.jpg";
	private String bluePath = "Images\\blue_door.jpg";
	
    public ColouredDoor(String colour) {
        super(false, true, false, "", colouredKey);
        this.colour = colour;
    }
    
    public String getColour()
    {
    	return colour;
    }
    
    
}
