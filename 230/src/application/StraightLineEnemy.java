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
	private Image image;
	
    public StraightLineEnemy(int currentX, int currentY, String movDirection) throws FileNotFoundException {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.movDirection = movDirection;
        setImage();
    }
    
    public StraightLineEnemy(int currentX, int currentY) throws FileNotFoundException {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.movDirection = UP;
        setImage();
    }
    
    String getDirection()
    {
    	return movDirection;
    }
    
    public String getString()
    {
    	return"STRAIGHT";
    }
    
    public void setImage() throws FileNotFoundException
    {
    	image = new Image(new FileInputStream(path));
    }
    
    public void draw(GraphicsContext gc,int x,int y)
    {
    	gc.drawImage(image, x, y,100,100);
    }

}