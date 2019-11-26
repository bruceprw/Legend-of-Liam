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
	private Image image;
	
	  public DumbTargettingEnemy(int currentX,int currentY,String movDirection)
	    {
	        this.currentPositionX = currentX;
	        this.currentPositionY = currentY;
	        this.movDirection = UP;
	        
	        try
			{
				setImage();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
    public DumbTargettingEnemy(int currentX,int currentY)
    {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        try
		{
			setImage();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public int moveTowardsPlayerOnX(int enemyX, int playerX)
    {
    	if (playerX > enemyX)
    	{
    		return enemyX+ONE;
    	}
    	else if(playerX < enemyX)
    	{
    		return enemyX-ONE;
    	}
    	else
    	{
    		return enemyX;
    	}
    }
    
    public int moveTowardsPlayerOnY(int enemyY, int playerY)
    {
    	if (playerY > enemyY)
    	{
    		return enemyY+ONE;
    	}
    	else if(playerY < enemyY)
    	{
    		return enemyY-ONE;
    	}
    	else
    	{
    		return enemyY;
    	}
    }

    public String getString()
    {
    	return"DUMB";
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