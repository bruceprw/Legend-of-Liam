package Character;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import application.GameBoard;

/**
 * Smart targeting enemies uses BFS to find player and chase
 * 
 * @author Miles Singleton
 * @version 1.0
 */
public class SmartTargettingEnemy extends Enemy {

	private String path = "Images\\smart.png";
	private final String SMART = "SMART";

	/**
	 * Creates a new instance of a smart enemy.
	 * 
	 * @param newX The X positon for the enemy
	 * @param newY The Y positon for the enemy
	 * @throws FileNotFoundException
	 */
	public SmartTargettingEnemy(int newX, int newY) throws FileNotFoundException {
		this.currentPositionX = newX;
		this.currentPositionY = newY;
		this.name = SMART;

		setImage(path);
	}

	/**
	 * Gets the path the enemy follows towards the player
	 * 
	 * @param gb      The gameboard info. As the enemy can't go through other
	 *                elements.
	 * @param x       enemy's x-coordinate.
	 * @param y       enemy's y-coordinate.
	 * @param playerX player's x-coordinate.
	 * @param playerY player's y-coordinate.
	 * @return the next path the enemy will follow.
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
			return new Path(gb, x, y, 0);
		} else {
			Path f = fl.get(0);
			ArrayList<Path> ad = new ArrayList<Path>();

			for (int i = 0; i < fl.size(); i++) {
				if (isAdjacent(fl.get(i), f))
					ad.add(fl.get(i));
			}
			// System.out.println(ad.get(0));
			return ad.get(0);
		}
	}

	/**
	 * Checks whether two paths are adjacent to each other.
	 * 
	 * @param a Path one
	 * @param b Path two
	 * @return true if they are adjacent
	 */
	private boolean isAdjacent(Path a, Path b) {
		boolean c = a.getX() == b.getX();
		boolean d = a.getY() == b.getY();
		boolean e = (a.getX() - 1 == b.getX()) && d;
		boolean f = (a.getX() + 1 == b.getX()) && d;
		boolean g = e || f;
		boolean h = (a.getY() - 1 == b.getY()) && c;
		boolean i = (a.getY() + 1 == b.getY()) && c;
		boolean j = h || i;
		return g || j;
	}

	/**
	 * Set the path by the Breadth-first-search algorithms.
	 * 
	 * @param gb      the gameboard instance
	 * @param x       the x-coordinate of enemy.
	 * @param y       the y-coordinate of enemy.
	 * @param playerX the player's x-coordinate.
	 * @param playerY the player's y-coordinate.
	 * @param counter the steps taken from the enemy's position
	 * @param path    the list of path information.
	 */
	public void setPaths(GameBoard gb, int x, int y, int playerX, int playerY, int counter, ArrayList<Path> path) {
		path.add(new Path(gb, playerX, playerY, counter));
		if (x == playerX && y == playerY) {
			return;
		}
		// System.out.println(x+","+y);
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
	 * @param p       the list to be checked
	 * @param x       the x-coordinate of enemy.
	 * @param y       the y-coordinate of enemy.
	 * @param counter the
	 * @return true if its in the list else false
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
}