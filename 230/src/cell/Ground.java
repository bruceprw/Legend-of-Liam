package cell;

public class Ground extends Cell {

	private String path = "Images\\ground.jpg";
	
    public Ground(){
        super(false, true, true, "", Item.NONE);
    }

    
    public boolean moveToCell() {
        return true;
    }
}
