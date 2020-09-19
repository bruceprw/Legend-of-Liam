package Character;

import java.io.File;
import java.io.FileNotFoundException;
import application.GameBoard;

/**
 * Class for dumb enemy, always moves towards the player even if its into a
 * wall.
 * 
 * @author Miles Singleton, Andy Kuo
 * @version 1.0
 */
public class DumbTargettingEnemy extends Enemy {

	private static final String DUMB = "DUMB";
	private String path = "230" + File.separatorChar + "Images" + File.separatorChar + "idiot.png";

	/**
	 * Create a new instance of dumb enemy.
	 * 
	 * @param currentX The X position of the enemy.
	 * @param currentY The Y position of the enemy.
	 */
	public DumbTargettingEnemy(int currentX, int currentY) {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.name = DUMB;

		try {
			setImage(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * /** Get the next X-coordinate for the dumb enemy.
	 * 
	 * @param gb      The game board for checking elements.
	 * @param playerX The player's x-coordinate.
	 * @param playerY The player's y-coordinate.
	 * @param x       The enemy's x-coordinate.
	 * @param y       The enemy's y-coordinate.
	 * @return The position of the next move.
	 */
	public int getNewX(GameBoard gb, int playerX, int playerY, int x, int y) {
		int xD = playerX - x;
		int yD = playerY - y;
		int xDifference = Math.abs(xD);
		int yDifference = Math.abs(yD);
		if ((xDifference > yDifference) && (xD > 0)) {
			if (checkMove(gb, x + 1, y))
				return x + 1;
			else
				return x;
		}

		else if ((xDifference > yDifference) && (xD < 0)) {
			if (checkMove(gb, x - 1, y))
				return x - 1;
			else
				return x;
		} else if ((xDifference == yDifference) && (xD < 0)) {
			if (checkMove(gb, x - 1, y))
				return x - 1;
			else
				return x;
		} else if ((xDifference == yDifference) && (xD > 0)) {
			if (checkMove(gb, x + 1, y))
				return x + 1;
			else
				return x;
		} else {
			if (yD > 0 && !checkMove(gb, x, y + 1)) {
				if (xD > 0 && checkMove(gb, x + 1, y))
					return x + 1;
				else if (xD < 0 && checkMove(gb, x - 1, y))
					return x - 1;
			} else if (yD < 0 && !checkMove(gb, x, y - 1)) {
				if (xD > 0 && checkMove(gb, x + 1, y))
					return x + 1;
				else if (xD < 0 && checkMove(gb, x - 1, y))
					return x - 1;
			}

			return x;
		}

	}

	/**
	 * Get the next Y-coordinate for dumb enemy.
	 * 
	 * @param gb      The gameboard for checking elements.
	 * @param playerX The player's X-coordinate.
	 * @param playerY The player's Y-coordinate.
	 * @param x       The enemy's X-coordinate.
	 * @param y       The enemy's Y-coordinate.
	 * @return The position of the next move.
	 */
	public int getNewY(GameBoard gb, int playerX, int playerY, int x, int y) {
		int xD = playerX - x;
		int yD = playerY - y;
		int xDifference = Math.abs(xD);
		int yDifference = Math.abs(yD);
		if ((yDifference > xDifference) && (yD > 0)) {
			if (checkMove(gb, x, y + 1))
				return y + 1;
			else
				return y;
		} else if ((yDifference > xDifference) && (yD < 0)) {
			if (checkMove(gb, x, y - 1))
				return y - 1;
			else
				return y;
		} else if ((xDifference == yDifference) && (yD < 0)) {
			if (checkMove(gb, x + 1, y) && xD > 0)
				return y;
			else if (checkMove(gb, x - 1, y) && xD < 0)
				return y;
			else if (checkMove(gb, x, y - 1))
				return y - 1;
			else
				return y;
		} else if ((xDifference == yDifference) && (yD > 0)) {
			if (checkMove(gb, x + 1, y) && xD > 0)
				return y;
			else if (checkMove(gb, x - 1, y) && xD < 0)
				return y;
			else if (checkMove(gb, x, y + 1))
				return y + 1;
			else
				return y;
		} else
			return y;
	}
}