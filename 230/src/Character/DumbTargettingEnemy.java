package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.GameBoard;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * DumbTargettingEnemy.java Class for dumb targeting enemy Hunts the player
 * using DFS when not in range move in random directions
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class DumbTargettingEnemy extends Enemy
{

	private String path = "Images\\idiot.png";
	private Image image;

	public DumbTargettingEnemy(int currentX, int currentY, String movDirection)
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

	public DumbTargettingEnemy(int currentX, int currentY)
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

	public int[] moveTowardsPlayer(int enemyX, int enemyY, int playerX, int playerY)
	{
		int[] a =
		{ enemyX, enemyY };
		if(playerX > enemyX)
		{
			a[0] = enemyX + ONE;
			return a;
		}
		else if(playerX < enemyX)
		{
			a[0] = enemyX - ONE;
			return a;
		}
		else if(playerY > enemyY)
		{
			a[1] = enemyY + ONE;
			return a;
		}
		else if(playerY < enemyY)
		{

			a[1] = enemyY - ONE;
			return a;
		}
		else
		{
			return a;
		}
	}

	public String getString()
	{
		return "DUMB";
	}

	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y)
	{
		gc.drawImage(image, x, y, 100, 100);
	}

	public int getX(GameBoard gb, int playerX, int playerY, int x, int y)
	{
		int xD = playerX - x;
		int yD = playerY - y;
		int xDifference = Math.abs(xD);
		int yDifference = Math.abs(yD);
		if((xDifference > yDifference) && (xD > 0))
		{
			if(checkMove(gb, x + 1, y))
				return x + 1;
			else
				return x;
		}

		else if((xDifference > yDifference) && (xD < 0))
		{
			if(checkMove(gb,x-1,y))
				return x - 1;
			else
				return x;
		}
		else if((xDifference==yDifference)&&(xD<0))
			return x-1;
		else if((xDifference==yDifference)&&(xD>0))
			return x+1;
		else
			return x;
	}

	public int getY(GameBoard gb, int playerX, int playerY, int x, int y)
	{
		int xD = playerX - x;
		int yD = playerY - y;
		int xDifference = Math.abs(xD);
		int yDifference = Math.abs(yD);
		if((yDifference > xDifference) && (yD > 0))
		{
			if(checkMove(gb,x,y+1))
				return y+1;
			else
				return y;
		}
		else if((yDifference > xDifference) && (yD < 0))
		{
			if(checkMove(gb,x,y-1))
				return y-1;
			else
				return y;
		}
		else
			return y;
	}
}