package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TokenDoor extends Cell {
    private int tokenNumToUnlockDoor;
    private String path = "Images\\token_door.jpg";

    public TokenDoor(int tokenNumToUnlockDoor) {
        super(false, true, false, "", Item.NONE);
        this.tokenNumToUnlockDoor = tokenNumToUnlockDoor;
    }

    @Override
    public boolean isPlayerMoved() {
    }

	public String getTokenNum()
	{
		
		return tokenNumToUnlockDoor;
	}
	
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
		Image image = new Image(new FileInputStream(path));
		gc.drawImage(image,x,y,50,50);
    }
}
