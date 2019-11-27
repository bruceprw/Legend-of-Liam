package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import application.Empty;
import application.GameBoard;
import cell.Ground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Straight line enemy moves in a straight line
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class StraightLineEnemy extends Enemy
{

	private String path = "Images\\straightE.jpg";
	private Image image;
	private final String UP = "UP";
	private final String DOWN = "DOWN";
	private final String LEFT = "LEFT";
	private final String RIGHT = "RIGHT";
	private String movDirection;

	public StraightLineEnemy(int currentX, int currentY, String movDirection) throws FileNotFoundException
	{
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;

		this.movDirection = movDirection;
		setImage();
	}

	public StraightLineEnemy(int currentX, int currentY) throws FileNotFoundException
	{
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;

		this.movDirection = UP;
		setImage();
	}

	public String getDirection()
	{
		return movDirection;
	}

	public String getString()
	{
		return "STRAIGHT";
	}

	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y)
	{
		gc.drawImage(image, x, y, 100, 100);
	}

	public int getX(GameBoard gb, int playerX, int playerY)
	{

		switch (movDirection)
		{
		case UP:
			if(checkMove(gb, currentPositionX, currentPositionY - 1))
			{

				return currentPositionX;
			}
			else
			{
				revDirection();
				return getX(gb, playerX, playerY);
			}

		case DOWN:
			if(checkMove(gb, currentPositionX, currentPositionY + 1))
				return currentPositionX;
			else
			{
				revDirection();
				return getX(gb, playerX, playerY);
			}

		case LEFT:
			if(checkMove(gb, currentPositionX - 1, currentPositionY))
			{
				currentPositionX += 1;
				return currentPositionX;
			}
			else
			{
				revDirection();
				return getX(gb, playerX, playerY);
			}

		case RIGHT:
			if(checkMove(gb, currentPositionX + 1, currentPositionY))
			{
				currentPositionX -= 1;
				return currentPositionX;
			}
			else
			{
				revDirection();
				return getX(gb, playerX, playerY);
			}
		default:
			return 0;
		}

	}

	public int getY(GameBoard gb, int playerX, int playerY)
	{

		switch (movDirection)
		{
		case UP:
			if(checkMove(gb, currentPositionX, currentPositionY - 1))
			{
				currentPositionY -= 1;
				return currentPositionY;
			}
			else
			{
				revDirection();
				return getY(gb, playerX, playerY);
			}

		case DOWN:
			if(checkMove(gb, currentPositionX, currentPositionY + 1))
			{
				currentPositionY += 1;
				return currentPositionY;
			}
			else
			{
				revDirection();
				return getY(gb, playerX, playerY);
			}

		case LEFT:
			if(checkMove(gb, currentPositionX - 1, currentPositionY))
				return currentPositionY;
			else
			{
				revDirection();
				return getY(gb, playerX, playerY);
			}

		case RIGHT:
			if(checkMove(gb, currentPositionX + 1, currentPositionY))
				return currentPositionY;
			else
			{
				revDirection();
				return getY(gb, playerX, playerY);
			}
		default:
			return 0;
		}

	}

	public void revDirection()
	{
		switch (movDirection)
		{
		case UP:
			movDirection = DOWN;
			break;
		case DOWN:
			movDirection = UP;
			break;
		case LEFT:
			movDirection = RIGHT;
			break;
		case RIGHT:
			movDirection = LEFT;
			break;
		}

	}

	public boolean horizontalNoMove(GameBoard gb, int playerX, int playerY)
	{
		Element[][] board = gb.getBoard();
		Element[][] bg = gb.getBackground();
		boolean left = board[currentPositionY][currentPositionX - 1] instanceof Empty;
		boolean right = board[currentPositionY][currentPositionX + 1] instanceof Empty;
		boolean bgLeft = bg[currentPositionY][currentPositionX - 1] instanceof Ground;
		boolean bgRight = bg[currentPositionY][currentPositionX + 1] instanceof Ground;
		return ((!left) && (!right)) || ((!bgLeft) && (!bgRight));
	}

	public boolean verticalNoMove(GameBoard gb, int playerX, int playerY)
	{
		Element[][] board = gb.getBoard();
		Element[][] bg = gb.getBackground();
		boolean up = board[currentPositionY - 1][currentPositionX] instanceof Empty;
		boolean down = board[currentPositionY + 1][currentPositionX] instanceof Empty;
		boolean bgUp = bg[currentPositionY - 1][currentPositionX] instanceof Ground;
		boolean bgDown = bg[currentPositionY + 1][currentPositionX] instanceof Ground;
		return ((!up) && (!down)) || ((!bgUp) && (!bgDown));
	}

}