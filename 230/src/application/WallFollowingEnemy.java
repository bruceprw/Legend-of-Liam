package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Wall Following Enemies 
 * follows wall 
 * @author Miles Singleton
 *
 */
public class WallFollowingEnemy extends Enemy {
	
	private String path = "Images\\wallHugE.jpg";

	
    public WallFollowingEnemy(int currentX, int currentY,String movDirection) {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;
        this.movDirection=movDirection;
    }
    
    String getDirection()
    {
    	return movDirection;
    }
    public String getString()
    {
    	return"WALLHUG";
    }
    
    public void draw(GraphicsContext gc,int x,int y) throws FileNotFoundException
    {
    	Image image = new Image(new FileInputStream(path));
    	gc.drawImage(image, x, y,100,100);
    }
}