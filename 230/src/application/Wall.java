package application;

public class Wall extends Cell {
	
    public Wall() {
        super(false, false, false, "", Item.NONE);
        
    } 
    public String getString()
    {
    	return"#";
    }
}
