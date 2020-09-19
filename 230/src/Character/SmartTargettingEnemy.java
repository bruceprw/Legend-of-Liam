package Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import application.GameBoard;

/**
 * Smart targeting enemies uses BFS to find player and chase.
 * 
 * @author Miles Singleton, Andy Kuo
 * @version 1.0
 */
public class SmartTargettingEnemy extends Enemy {

	private final String SMART = "SMART";
	private String path = "230" + File.separatorChar + "Images" + File.separatorChar + "smart.png";

	/**
	 * Creates a new instance of a smart enemy.
	 * 
	 * @param newX The X position for the enemy.
	 * @param newY The Y position for the enemy.
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error of this type.
	 */
	public SmartTargettingEnemy(int newX, int newY) throws FileNotFoundException {
		this.currentPositionX = newX;
		this.currentPositionY = newY;
		this.name = SMART;

		setImage(path);
	}

	/**
	 * Gets the path the enemy follows towards the player.
	 * 
	 * @param gb      The gameboard info. As the enemy can't go through other
	 *                elements.
	 * @param x       Enemy's x-coordinate.
	 * @param y       Enemy's y-coordinate.
	 * @param playerX Player's x-coordinate.
	 * @param playerY Player's y-coordinate.
	 * @return The next path the enemy will follow.
	 */
	public Path getPath(GameBoard gb, int x, int y, int playerX, int playerY) {
		ArrayList<Path> temp = new ArrayList<Path>();
		setPaths(gb, x, y, playerX, playerY, 0, temp);
		int counter = 0;
		ArrayList<Path> fl = new ArrayList<Path>();

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getX() == x && temp.get(i).getY() == y) {
				counter = temp.get(i).getCount();
				fl.add(temp.get(i));
			}
		}
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getCount() < counter)
				fl.add(temp.get(i));
		}
		if (fl.size() == 0) {
			return new Path(x, y, 0);
		} else {
			Path f = fl.get(0);
			ArrayList<Path> ad = new ArrayList<Path>();

			for (int i = 0; i < fl.size(); i++) {
				if (isAdjacent(fl.get(i), f))
					ad.add(fl.get(i));
			}
			return ad.get(0);
		}
	}

	/**
	 * Set the path by the Breadth-first-search algorithms.
	 * 
	 * @param gb      The gameboard instance.
	 * @param x       The x-coordinate of enemy.
	 * @param y       The y-coordinate of enemy.
	 * @param playerX The player's x-coordinate.
	 * @param playerY The player's y-coordinate.
	 * @param counter The steps taken from the enemy's position.
	 * @param path    The list of path information.
	 */
	public void setPaths(GameBoard gb, int x, int y, int playerX, int playerY, int counter, ArrayList<Path> path) {
		path.add(new Path(playerX, playerY, counter));
		if (x == playerX && y == playerY) {
			return;
		}
		if (Enemy.checkMove(gb, playerX + 1, playerY) || (playerX + 1 == x && playerY == y)) {
			if (checkVisited(path, playerX + 1, playerY, counter + 1)) {

			} else {
				setPaths(gb, x, y, playerX + 1, playerY, counter + 1, path);
			}

		}

		if (Enemy.checkMove(gb, playerX - 1, playerY) || (playerX - 1 == x && playerY == y)) {
			if (checkVisited(path, playerX - 1, playerY, counter + 1)) {
				;
			} else {
				setPaths(gb, x, y, playerX - 1, playerY, counter + 1, path);
			}
		}

		if (Enemy.checkMove(gb, playerX, playerY + 1) || (playerX == x && playerY + 1 == y)) {
			if (checkVisited(path, playerX, playerY + 1, counter + 1)) {

			} else
				setPaths(gb, x, y, playerX, playerY + 1, counter + 1, path);
		}

		if (Enemy.checkMove(gb, playerX, playerY - 1) || (playerX == x && playerY - 1 == y)) {
			if (checkVisited(path, playerX, playerY - 1, counter + 1))
				;
			else
				setPaths(gb, x, y, playerX, playerY - 1, counter + 1, path);
		}

	}

	/**
	 * Check whether the list includes the same path.
	 * 
	 * @param p       The list to be checked.
	 * @param x       The x-coordinate of enemy.
	 * @param y       The y-coordinate of enemy.
	 * @param counter The number of visited cells.
	 * @return True if its in the list else false.
	 */
	public boolean checkVisited(ArrayList<Path> p, int x, int y, int counter) {
		for (int i = 0; i < p.size(); i++) {
			boolean xB = p.get(i).getX() == x;
			boolean yB = p.get(i).getY() == y;
			p.get(i).getCount();
			if (xB && yB)
				return true;
		}
		return false;
	}

	/**
	 * Checks whether two paths are adjacent to each other.
	 * 
	 * @param firstPath  First path to compare.
	 * @param secondPath Second path to compare to.
	 * @return True if they are adjacent, else false.
	 */
	private boolean isAdjacent(Path firstPath, Path secondPath) {
		boolean c = firstPath.getX() == secondPath.getX();
		boolean d = firstPath.getY() == secondPath.getY();
		boolean e = (firstPath.getX() - 1 == secondPath.getX()) && d;
		boolean f = (firstPath.getX() + 1 == secondPath.getX()) && d;
		boolean g = e || f;
		boolean h = (firstPath.getY() - 1 == secondPath.getY()) && c;
		boolean i = (firstPath.getY() + 1 == secondPath.getY()) && c;
		boolean j = h || i;
		return g || j;
	}
}