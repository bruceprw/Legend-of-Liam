package Character;

import application.Element;
import application.GameBoard;

public class Path extends Element
{
	private int x;
	private int y;
	private Path up;
	private Path down;
	private Path right;
	private Path left;
	private GameBoard gb;
	private int counter;
	private boolean wallHug;
	private String hand;
	private Element[][] board;
	private Element[][] bg;
	private String movDirection;

	Path(GameBoard gb, int x, int y, boolean wallHug,String movDirection,String hand)
	{
		this.movDirection=movDirection;
		this.wallHug = wallHug;
		this.gb = gb;
		this.x = x;
		this.y = y;
		this.hand=hand;
		setWallHug();
	}

	Path(GameBoard gb, int x, int y, int counter)
	{
		this.counter = counter;
		this.gb = gb;
		this.x = x;
		this.y = y;
		setNext();
	}

	public void setNext()
	{
		if(Enemy.checkMove(gb, x, y - 1))
			this.up = new Path(gb, x, y - 1, counter + 1);
		if(Enemy.checkMove(gb, x, y + 1))
			this.down = new Path(gb, x, y + 1, counter + 1);
		if(Enemy.checkMove(gb, x - 1, y))
			this.right = new Path(gb, x - 1, y, counter + 1);
		if(Enemy.checkMove(gb, x + 1, y))
			this.left = new Path(gb, x + 1, y, counter + 1);
	}

	public void setWallHug()
	{
		
	}
}
