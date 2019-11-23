package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Straight line enemy
 * moves in a straight line
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class StraightLineEnemy extends Enemy {
	
	private String path = "Images\\straightE.jpg";
	
    public StraightLineEnemy(int currentX, int currentY, boolean HV, String movDirection) {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.direction = HV;
        this.movDirection = movDirection;
    }

    //TODO write code for this 
    private boolean checkCorner()
    {
        return false;
    }
    
    String getDirection()
    {
    	return movDirection;
    }
    
    public String getString()
    {
    	return"STRAIGHT";
    }
    
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
    	Image image = new Image(new FileInputStream(path));
    	gc.drawImage(image, x, y,100,100);
    }

}