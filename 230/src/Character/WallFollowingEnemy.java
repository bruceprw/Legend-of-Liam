package Character;

import java.io.FileNotFoundException;
import application.Element;
import application.Empty;
import application.GameBoard;
import cell.Ground;

/**
 * Class for the wall following enemy. A enemy that at all times must be next to
 * a wall tile.
 * 
 * @author Miles Singleton, Andy Kuo
 *
 */
public class WallFollowingEnemy extends Enemy
{

	private String path = "Images\\wallHugE.png";
	private String hand;
	private final String WALLHUG = "WALLHUG";

	/**
	 * Creates a new instance of a Wall following enemy.
	 * 
	 * @param newX         Enemy X position
	 * @param newY         Enemy Y position
	 * @param movDirection the direction that this enemy is moving
	 * @param hand         the hand that's touching the wall for direction
	 *                     determining.
	 * @throws FileNotFoundException
	 */
	public WallFollowingEnemy(int newX, int newY, String movDirection, String hand) throws FileNotFoundException
	{
		this.currentPositionX = newX;
		this.currentPositionY = newY;
		this.movDirection = movDirection;
		this.hand = hand;
		this.name = WALLHUG;
		setImage(path);
	}

	/**
	 * Gets new X position for the wall following enemy.
	 * 
	 * @param gb the current gameboard in play
	 * @param x  current X position of the enemy
	 * @param y  current Y position of the enemy.
	 * @return new X position
	 */
	public int getsNewX(GameBoard gb, int x, int y)
	{
		switch (movDirection)
		{
		case UP:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x, y - 1))
					return x;
				else
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb, x + 1, y))
						{
							// movDirection=RIGHT;
							// hand=UP;
							return x + 1;
						}
						else
						{
							// movDirection=DOWN;
							// hand=RIGHT;
							return x;
						}
					}
					else
					{
						if(checkMove(gb, x - 1, y))
						{
							// movDirection=LEFT;
							// hand=UP;
							return x - 1;
						}
						else
						{
							// movDirection=DOWN;
							// hand=LEFT;
							return x;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					// movDirection=LEFT;
					// hand=DOWN;
					return x - 1;
				}
				else
				{
					// movDirection=RIGHT;
					// hand=DOWN;
					return x + 1;
				}
			}

		case DOWN:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x, y + 1))
					return x;
				else
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb, x + 1, y))
						{
							// movDirection=RIGHT;
							// hand=DOWN;
							return x + 1;
						}
						else
						{
							// movDirection=UP;
							// hand=RIGHT;
							return x;
						}
					}
					else
					{
						if(checkMove(gb, x - 1, y))
						{
							// movDirection=LEFT;
							// hand=DOWN;
							return x - 1;
						}
						else
						{
							// movDirection=UP;
							// hand=LEFT;
							return x;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					// movDirection=LEFT;
					// hand=UP;
					return x - 1;
				}
				else
				{
					// movDirection=RIGHT;
					// hand=UP;
					return x + 1;
				}
			}

		case LEFT:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x - 1, y))
				{
					return x - 1;
				}
				else
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb, x, y + 1))
						{
							// movDirection=DOWN;
							// hand=LEFT;
							return x;
						}
						else
						{
							// movDirection=RIGHT;
							// hand=DOWN;
							return x + 1;
						}
					}
					else
					{
						if(checkMove(gb, x, y - 1))
						{
							// movDirection=UP;
							// hand=LEFT;
							return x;
						}
						else
						{
							// movDirection=RIGHT;
							// hand=UP;
							return x + 1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					// movDirection=UP;
					// hand=RIGHT;
					return x;
				}
				else
				{
					// movDirection=DOWN;
					// hand=RIGHT;
					return x;
				}
			}

		case RIGHT:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x + 1, y))
				{

					return x + 1;
				}
				else
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb, x, y + 1))
						{
							// movDirection=DOWN;
							// hand=RIGHT;
							return x;
						}
						else
						{
							// movDirection=LEFT;
							// hand=DOWN;
							return x - 1;
						}
					}
					else
					{
						if(checkMove(gb, x, y - 1))
						{
							// movDirection=UP;
							// hand=RIGHT;
							return x;
						}
						else
						{
							// movDirection=LEFT;
							// hand=UP;
							return x - 1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					// movDirection=UP;
					// hand=LEFT;
					return x;
				}
				else
				{
					// movDirection=DOWN;
					// hand=LEFT;
					return x;
				}
			}

		default:
			return 0;
		}

	}

	/**
	 * Gets new Y positon for the wall following enemy.
	 * 
	 * @param gb the current gameboard in play
	 * @param x  current X positon of the enemy
	 * @param y  current Y position of the enemy.
	 * @return new Y position
	 */
	public int getNewY(GameBoard gb, int x, int y)
	{
		switch (movDirection)
		{
		case UP:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x, y - 1))
				{
					return y - 1;
				}
				else
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb, x + 1, y))
						{
							movDirection = RIGHT;

							hand = UP;
							return y;
						}
						else
						{
							movDirection = DOWN;
							hand = RIGHT;
							return y + 1;
						}
					}
					else
					{
						if(checkMove(gb, x - 1, y))
						{
							movDirection = LEFT;
							hand = UP;
							return y;
						}
						else
						{
							movDirection = DOWN;
							hand = LEFT;
							return y + 1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					movDirection = LEFT;
					hand = DOWN;
					return y;
				}
				else
				{
					movDirection = RIGHT;
					hand = DOWN;
					return y;
				}
			}

		case DOWN:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x, y + 1))
				{

					return y + 1;
				}
				else
				{
					if(hand.equals(LEFT))
					{
						if(checkMove(gb, x + 1, y))
						{
							movDirection = RIGHT;
							hand = DOWN;
							return y;
						}
						else
						{
							movDirection = UP;
							hand = RIGHT;
							return y - 1;
						}
					}
					else
					{
						if(checkMove(gb, x - 1, y))
						{
							movDirection = LEFT;
							hand = DOWN;
							return y;
						}
						else
						{
							movDirection = UP;
							hand = LEFT;
							return y - 1;
						}
					}
				}
			}
			else
			{
				if(hand.equals(LEFT))
				{
					movDirection = LEFT;
					hand = UP;
					return y;
				}
				else
				{
					movDirection = RIGHT;
					hand = UP;
					return y;
				}
			}

		case LEFT:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x - 1, y))
				{
					return y;
				}
				else
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb, x, y + 1))
						{
							movDirection = DOWN;
							hand = LEFT;
							return y + 1;
						}
						else
						{
							movDirection = RIGHT;
							hand = DOWN;
							return y;
						}
					}
					else
					{
						if(checkMove(gb, x, y - 1))
						{
							movDirection = UP;
							hand = LEFT;

							return y - 1;
						}
						else
						{
							movDirection = RIGHT;
							hand = UP;

							return y;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					movDirection = UP;
					hand = RIGHT;

					return y - 1;
				}
				else
				{
					movDirection = DOWN;
					hand = RIGHT;

					return y + 1;
				}
			}

		case RIGHT:
			if(checkHandWall(gb, x, y))
			{
				if(checkMove(gb, x + 1, y))
					return y;
				else
				{
					if(hand.equals(UP))
					{
						if(checkMove(gb, x, y + 1))
						{
							movDirection = DOWN;
							hand = RIGHT;

							return y + 1;
						}
						else
						{
							movDirection = LEFT;
							hand = DOWN;

							return y;
						}
					}
					else
					{
						if(checkMove(gb, x, y - 1))
						{
							movDirection = UP;
							hand = RIGHT;

							return y - 1;
						}
						else
						{
							movDirection = LEFT;
							hand = UP;

							return y;
						}
					}
				}
			}
			else
			{
				if(hand.equals(UP))
				{
					movDirection = UP;
					hand = LEFT;

					return y - 1;
				}
				else
				{
					movDirection = DOWN;
					hand = LEFT;

					return y + 1;
				}
			}

		default:
			return 0;
		}

	}

	/**
	 * Checks whether the enemy's hand touching the wall.
	 * 
	 * @param gb the current gameboard in play.
	 * @param x  current X position of the enemy.
	 * @param y  current Y position of the enemy.
	 * @return True if next move will be next to a wall.
	 */
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

	
	/**
	 * Get where the hand is, only used by the file outputer class
	 * 
	 * @return the hand
	 */
	public String getHand()
	{
		return this.hand;
	}
}