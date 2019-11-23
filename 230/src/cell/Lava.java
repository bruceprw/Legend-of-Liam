package cell;

public class Lava extends Cell {

	private String path = "Images\\fire.jpg";
	
    public Lava() {
        super(true, true, false, "", Item.FIRE_BOOTS);
    }

    public String getString()
    {
    	return"L";
    }
}
