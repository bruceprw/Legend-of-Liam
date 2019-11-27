package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import application.Empty;
import application.GameBoard;
import cell.Cell;
import cell.Ground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Wall Following Enemies follows wall
 * 
 * @author Miles Singleton
 *
 */
public class WallFollowingEnemy extends Enemy
{

	private String path = "Images\\wallHugE.jpg";
	private Image image;
	private String movDirection;
	private String hand;

	private int wallX = 0;
	private int wallY = 0;

	public WallFollowingEnemy(int currentX, int currentY, String movDirection, String hand) throws FileNotFoundException
	{
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.movDirection = movDirection;
		this.hand=hand;
		setImage();
	}

	public WallFollowingEnemy(int currentX, int currentY) throws FileNotFoundException
	{
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.movDirection = UP;
		setImage();
	}

	public int[] moveToCorner(int currentX, int currentY, String wallDirection)
	{
		int[] a =
		{ 1, 2 };
		switch (this.getMovDirection())
		{

		case (UP):
			a[0] = getNewWallXPosition(currentX, wallDirection);
			a[1] = currentY - ONE;
			return a;

		case (DOWN):
			a[0] = getNewWallXPosition(currentX, wallDirection);
			a[1] = currentY + ONE;
			return a;

		case (LEFT):
			a[0] = currentX - ONE;
			a[1] = getNewWallYPosition(currentY, wallDirection);
			return a;

		case (RIGHT):
			a[0] = currentX + ONE;
			a[1] = getNewWallYPosition(currentY, wallDirection);
			return a;
		default:
			throw new IllegalStateException("Undifened direction");
		}

	}

	private int getNewWallXPosition(int currentX, String wallDirection)
	{
		switch (wallDirection)
		{
		case (LEFT):
			return currentX - TWO;
		case (RIGHT):
			return currentX + TWO;
		default:
			throw new IllegalStateException("Undifened direction");
		}
	}

	private int getNewWallYPosition(int currentY, String wallDirection)
	{
		switch (wallDirection)
		{
		case (UP):
			return currentY - TWO;
		case (DOWN):
			return currentY + TWO;
		default:
			throw new IllegalStateException("Undifened direction");
		}
	}

	public String getDirection()
	{
		return movDirection;
	}

	public String getString()
	{
		return "WALLHUG";
	}

	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y)
	{

		gc.drawImage(image, x, y, 100, 100);
	}

	public void changeDirection()
	{

	}

	public int getX(GameBoard gb, int x, int y)
	{
		switch (movDirection)
		{
		case UP:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x,y-1))
					return x;
				else 
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb,x+1,y))
						{
							//movDirection=RIGHT;
							//hand=UP;
							return x+1;
						}
						else
						{
							//movDirection=DOWN;
							//hand=RIGHT;
							return x;
						}
					}
					else
					{
						if(checkMove(gb,x-1,y))
						{
							//movDirection=LEFT;
							//hand=UP;
							return 	x-1;
						}
						else
						{
							//movDirection=DOWN;
							//hand=LEFT;
							return 	x;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					//movDirection=LEFT;
					//hand=DOWN;
					return 	x-1;
				}
				else
				{
					//movDirection=RIGHT;
					//hand=DOWN;
					return x+1;
				}
			}
			
		case DOWN:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x,y+1))
					return x;
				else 
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb,x+1,y))
						{
							//movDirection=RIGHT;
							//hand=DOWN;
							return 	x+1;
						}
						else
						{
							//movDirection=UP;
							//hand=RIGHT;
							return x;
						}
					}
					else
					{
						if(checkMove(gb,x-1,y))
						{
							//movDirection=LEFT;
							//hand=DOWN;
							return x-1;
						}
						else
						{
							//movDirection=UP;
							//hand=LEFT;
							return x;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					//movDirection=LEFT;
					//hand=UP;
					return 	x-1;
				}
				else
				{
					//movDirection=RIGHT;
					//hand=UP;
					return 	x+1;
				}
			}
			
		case LEFT:
			if(checkHandWall(gb,x,y))
			{
				System.out.println(checkHandWall(gb,x,y));
				if(checkMove(gb,x-1,y))
				{
					return x-1;
				}
				else 
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb,x,y+1))
						{
							//movDirection=DOWN;
							//hand=LEFT;
							return 	x;
						}
						else
						{
							//movDirection=RIGHT;
							//hand=DOWN;
							return 	x+1;
						}
					}
					else
					{
						if(checkMove(gb,x,y-1))
						{
							//movDirection=UP;
							//hand=LEFT;
							return x;
						}
						else
						{
							//movDirection=RIGHT;
							//hand=UP;
							return x+1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					//movDirection=UP;
					//hand=RIGHT;
					return 	x;
				}
				else
				{
					//movDirection=DOWN;
					//hand=RIGHT;
					return x;
				}
			}			
			
		case RIGHT:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x+1,y))
				{
					
					return x+1;
				}
				else 
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb,x,y+1))
						{
							//movDirection=DOWN;
							//hand=RIGHT;
							return x;
						}
						else
						{
							//movDirection=LEFT;
							//hand=DOWN;
							return 	x-1;
						}
					}
					else
					{
						if(checkMove(gb,x,y-1))
						{
							//movDirection=UP;
							//hand=RIGHT;
							return 	x;
						}
						else
						{
							//movDirection=LEFT;
							//hand=UP;
							return 	x-1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					//movDirection=UP;
					//hand=LEFT;
					return x;
				}
				else
				{
					//movDirection=DOWN;
					//hand=LEFT;
					return x;
				}
			}
			
		default:
			return 0;
		}
		
	}

	public int getY(GameBoard gb, int x, int y)
	{
		switch (movDirection)
		{
		case UP:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x,y-1))
				{
					return y-1;
				}
				else 
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb,x+1,y))
						{
							movDirection=RIGHT;
							
							hand=UP;
							return y;
						}
						else
						{
							movDirection=DOWN;
							hand=RIGHT;
							return y+1;
						}
					}
					else
					{
						if(checkMove(gb,x-1,y))
						{
							movDirection=LEFT;
							hand=UP;
							return 	y;
						}
						else
						{
							movDirection=DOWN;
							hand=LEFT;
							return y+1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					movDirection=LEFT;
					hand=DOWN;
					return y;
				}
				else
				{
					movDirection=RIGHT;
					hand=DOWN;
					return y;
				}
			}
			
		case DOWN:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x,y+1))
				{
					
					return y+1;
				}
				else 
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb,x+1,y))
						{
							movDirection=RIGHT;
							hand=DOWN;
							return 	y;
						}
						else
						{
							movDirection=UP;
							hand=RIGHT;
							return 	y-1;
						}
					}
					else
					{
						if(checkMove(gb,x-1,y))
						{
							movDirection=LEFT;
							hand=DOWN;
							return 	y;
						}
						else
						{
							movDirection=UP;
							hand=LEFT;
							return 	y-1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					movDirection=LEFT;
					hand=UP;
					return y;
				}
				else
				{
					movDirection=RIGHT;
					hand=UP;
					return y;
				}
			}
			
		case LEFT:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x-1,y))
				{
					return y;
				}
				else 
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb,x,y+1))
						{
							movDirection=DOWN;
							hand=LEFT;
							return y+1;
						}
						else
						{
							movDirection=RIGHT;
							hand=DOWN;
							return 	y;
						}
					}
					else
					{
						if(checkMove(gb,x,y-1))
						{
							movDirection=UP;
							hand=LEFT;

							return y-1;
						}
						else
						{
							movDirection=RIGHT;
							hand=UP;

							return y;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					movDirection=UP;
					hand=RIGHT;

					return y-1;
				}
				else
				{
					movDirection=DOWN;
					hand=RIGHT;

					return y+1;
				}
			}
			
			
		case RIGHT:
			if(checkHandWall(gb,x,y))
			{
				if(checkMove(gb,x+1,y))
					return y;
				else 
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb,x,y+1))
						{
							movDirection=DOWN;
							hand=RIGHT;

							return y+1;
						}
						else
						{
							movDirection=LEFT;
							hand=DOWN;

							return y;
						}
					}
					else
					{
						if(checkMove(gb,x,y-1))
						{
							movDirection=UP;
							hand=RIGHT;

							return y-1;
						}
						else
						{
							movDirection=LEFT;
							hand=UP;

							return y;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					movDirection=UP;
					hand=LEFT;

					return y-1;
				}
				else
				{
					movDirection=DOWN;
					hand=LEFT;

					return y+1;
				}
			}
			
		default:
			return 0;
		}
		
	}

	public boolean checkHandWall(GameBoard gb, int x, int y)
	{
		Element[][] board = gb.getBoard();
		Element[][] bg = gb.getBackground();
		
		switch (hand)
		{
		case UP:
			return (!(board[y - 1][x] instanceof Empty)) || (!(bg[y - 1][x] instanceof Ground));
		case DOWN:
			return (!(board[y + 1][x] instanceof Empty)) || (!(bg[y + 1][x] instanceof Ground));
		case LEFT:
			return (!(board[y][x - 1] instanceof Empty)) || (!(bg[y][x - 1] instanceof Ground));
		case RIGHT:
			return (!(board[y][x + 1] instanceof Empty)) || (!(bg[y][x + 1] instanceof Ground));
		default:
			return true;
		}
	}

	public String getHand()
	{
		return this.hand;
	}
}