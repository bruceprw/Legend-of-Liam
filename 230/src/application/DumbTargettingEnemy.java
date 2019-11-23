package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * DumbTargettingEnemy.java
 * Class for dumb targeting enemy
 * Hunts the player using DFS
 * when not in range move in random directions
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class DumbTargettingEnemy extends Enemy
{
	
	private String path = "Images\\idiot.png";

	
    public DumbTargettingEnemy(int currentX,int currentY, boolean HV)
    {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.direction = HV;
    }

    @Override
    protected void findNewPosition() {
        //find player location
        //move in said direction 
            //if wall stationary
        
        //if gone

        //random direction
    }
    public String getString()
    {
    	return"DUMB";
    }
    
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
    	Image image = new Image(new FileInputStream(path));
    	gc.drawImage(image, x, y);
    }
}