package cell;

public class Ground extends Cell {

    public Ground(){
        super(false, true, true, "", Item.NONE);
    }

    @Override
    public boolean moveToCell() {
        return true;
    }
}
