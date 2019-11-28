package Character;

import application.Element;
import application.GameBoard;

public class Path extends Element
{
	private int x;
	private int y;
	private int counter;
	
	Path(GameBoard gb, int x, int y, int counter)
	{
		
		this.x=x;
		this.y=y;
		this.counter=counter;
	}
	
	public boolean faster(Path p)
	{
		
		boolean a = this.x==p.getX();
		boolean b = this.y==p.getY();
		boolean c = this.counter<p.getCount();
		return a&&b&&c;
	}
	
	public boolean equals(Path p)
	{
		return this.x==p.getX()&&this.y==p.getY()&&this.counter==p.getCount();
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
		return ""+x+","+y+","+counter;
	}
	
	/*
	public void setNext()
	{
		if(Enemy.checkMove(gb, x, y - 1))
			this.up = new Path(gb, x, y - 1, counter + 1,playerX,playerY);
		if(Enemy.checkMove(gb, x, y + 1))
			this.down = new Path(gb, x, y + 1, counter + 1,playerX,playerY);
		if(Enemy.checkMove(gb, x - 1, y))
			this.right = new Path(gb, x - 1, y, counter + 1,playerX,playerY);
		if(Enemy.checkMove(gb, x + 1, y))
			this.left = new Path(gb, x + 1, y, counter + 1,playerX,playerY);
	}*/
}
