package Character;

import application.Element;

/**
 * Stores the path information for the smart enemy.
 * 
 * @author user
 *
 */
public class Path extends Element {
	Path up;
	Path down;
	Path left;
	Path right;
	private int x;
	private int y;
	
	/**
	 * The number of steps taken from the start of the enemy.
	 */
	private int counter;

	/**
	 * Stores the information of the path.
	 * @param x       the x-coordinate 
	 * @param y       the y-coordinate
	 * @param counter counter of the current path from the start of the enemy.
	 */
	public Path(int x, int y, int counter) {
		this.x = x;
		this.y = y;
		this.counter = counter;

	}

	/**
	 * Checks whether two paths has the same distance from the start of the enemy.
	 * 
	 * @param p the other path
	 * @return true if they are the same.
	 */
	public boolean equals(Path p) {
		return this.x == p.getX() && this.y == p.getY() && this.counter == p.getCount();
	}

	/**
	 * Get the x-coordinate.
	 * 
	 * @return x-coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y-coordinate
	 * 
	 * @return y-coordinate
	 */
	public int getY() {
		return y;

	}

	/**
	 * Get the counter
	 * 
	 * @return the counter
	 */
	public int getCount() {
		return counter;
	}

	/**
	 * Switch the information to string.
	 * 
	 * @return A String representation of the Path.
	 */
	public String toString() {
		return "" + x + "," + y + "," + counter;
	}

}
