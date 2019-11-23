package application;

public class ColouredDoor extends Cell {
	private String colour;
	
    public ColouredDoor(String colour) {
        super(false, true, false, "", colouredKey);
        this.colour = colour;
    }
}