package application;

public class Water extends Cell {
	
    public Water() {
        super(true, true, false, "", Item.FLIPPER);
    }
	
    public String getString()
    {
    	return"W";
    }
}
