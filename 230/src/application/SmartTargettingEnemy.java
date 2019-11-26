package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Smart targeting enemies
 * uses BFS to find player and chase
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class SmartTargettingEnemy extends Enemy
{
	private String path = "Images\\smart.jpg";
	private Image image;
	
	public SmartTargettingEnemy(int currentX,int currentY,String movDirection) throws FileNotFoundException
    {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;
        this.movDirection = UP;
        
        setImage();
    }
	
    public SmartTargettingEnemy(int currentX,int currentY) throws FileNotFoundException
    {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;
        
        setImage();
    }
    
    public String getString()
    {
    	return"SMART";
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