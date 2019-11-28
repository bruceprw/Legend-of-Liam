package Character;

import java.util.ArrayList;

import application.Element;
import application.GameBoard;

public class Path extends Element
{
	private int x;
	private int y;
	private int counter;
	Path up;
	Path down;
	Path left;
	Path right;
	private int playerX, playerY;
	private boolean found = false;

	Path(GameBoard gb, int x, int y, int counter, int playerX, int playerY, ArrayList<Path> temp)
	{
		temp.add(this);
		this.playerX = playerX;
		this.playerY = playerY;
		this.x = x;
		this.y = y;
		this.counter = counter;
		if(x == playerX && y == playerY)
			found = true;
		if(found == false)
		{
			if(Enemy.checkMove(gb, x - 1, y))
			{
				left = new Path(gb, x - 1, y, counter + 1, playerX, playerY,temp);
			}
			if(Enemy.checkMove(gb, x + 1, y))
			{
				right = new Path(gb, x + 1, y, counter + 1, playerX, playerY,temp);
			}
			if(Enemy.checkMove(gb, x, y - 1))
			{
				up = new Path(gb, x, y - 1, counter + 1, playerX, playerY,temp);
			}
			if(Enemy.checkMove(gb, x, y + 1))
			{
				down = new Path(gb, x, y + 1, counter + 1, playerX, playerY,temp);
			}
		}
		else 
		{
			
		}

	}

	public boolean faster(Path p)
	{

		boolean a = this.x == p.getX();
		boolean b = this.y == p.getY();
		boolean c = this.counter < p.getCount();
		return a && b && c;
	}

	public boolean equals(Path p)
	{
		return this.x == p.getX() && this.y == p.getY() && this.counter == p.getCount();
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;

	}

	public int getCount()
	{
		return counter;
	}

	public String toString()
	{
		return "" + x + "," + y + "," + counter;
	}

	/*
	 * public void setNext() { if(Enemy.checkMove(gb, x, y - 1)) this.up = new
	 * Path(gb, x, y - 1, counter + 1,playerX,playerY); if(Enemy.checkMove(gb, x, y
	 * + 1)) this.down = new Path(gb, x, y + 1, counter + 1,playerX,playerY);
	 * if(Enemy.checkMove(gb, x - 1, y)) this.right = new Path(gb, x - 1, y, counter
	 * + 1,playerX,playerY); if(Enemy.checkMove(gb, x + 1, y)) this.left = new
	 * Path(gb, x + 1, y, counter + 1,playerX,playerY); }
	 */
}
