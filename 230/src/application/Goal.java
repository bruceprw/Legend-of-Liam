package application;

public class Goal extends Cell {

    public Goal() {
        super(false, true, false, "", Item.NONE);
    }
    public String getString()
    {
    	return"G";
    }

}