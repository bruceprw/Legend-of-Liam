package application;

public class ColouredDoor extends Cell {
	private String colour;
	private String greenPath = "Images\\Green.png";
	
    public ColouredDoor(String colour) {
        super(false, true, false, "");
        this.colour = colour;
    }
    
    
}