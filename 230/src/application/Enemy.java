package application;

import cell.Cell;
import cell.ColouredDoor;
import cell.TokenDoor;

abstract class Enemy extends Element {

	protected String movDirection = "UP";

	// Constants
	/// Directions
	final protected String UP = "UP";
	final protected String LEFT = "LEFT";
	final protected String DOWN = "DOWN";
	final protected String RIGHT = "RIGHT";

	protected final int ONE = 1;
	protected final int TWO = 2;
	
	protected int currentPositionX;
	protected int currentPositionY;

	/**
	 * @return the movDirection
	 */
	public String getMovDirection() {
		return movDirection;
	}

	/**
	 * @param movDirection the movDirection to set
	 */
	public void setMovDirection(String movDirection) {
		this.movDirection = movDirection;
	}

	/**
	 * Method for working out where to move
	 * 
	 * @param currentX
	 * @param currentY
	 * @param nextCell
	 * @return
	 */
	public int[] moveTo(int currentX, int currentY, Cell nextCell) {
		switch (this.getMovDirection()) {
		case (UP):
			// TODO break this into a method
			if (this.isMovable(nextCell)) {
				//TODO remove variable and just return the int array
				int[] a = { currentX, currentY + ONE};
				return a;
			} else {
				//not movable so return a empty array which should throw an error when used
				this.reverseDirection();
				int[] a = {};
				return a;
			}
		case (LEFT):
			if (this.isMovable(nextCell)) {
				int[] a = { currentX - ONE , currentY};
				return a;
			} else {
				this.reverseDirection();
				// TODO get opposite cell
				this.moveTo(currentX, currentY, nextCell);
			}
		case (DOWN):
			if (this.isMovable(nextCell)) {
				int[] a = { currentX + ONE, currentY };
				return a;
			} else {
				this.reverseDirection();
				// TODO get opposite cell
				this.moveTo(currentX, currentY, nextCell);
			}
		case (RIGHT):
			if (this.isMovable(nextCell)) {
				int[] a = { currentX, currentY + ONE };
				return a;
			} else {
				this.reverseDirection();
				// TODO get opposite cell
				this.moveTo(currentX, currentY, nextCell);
			}
		default:
			throw new IllegalStateException("Undifened direction");

		}
	}

	/**
	 * Method for revesering the direction of Enemy
	 */
	public void reverseDirection() {
		switch (this.getMovDirection()) {
		case (UP):
			this.setMovDirection(DOWN);
			break;
		case (LEFT):
			this.setMovDirection(RIGHT);
			break;
		case (DOWN):
			this.setMovDirection(UP);
			break;
		case (RIGHT):
			this.setMovDirection(LEFT);
			break;
		default:
			throw new IllegalStateException("Undifened direction");
		}
	}

	/**
	 * Checks if given cell is movable
	 * 
	 * @param cell The cell the enemy wants to move to
	 * @return True for yes it is movable else false
	 */
	public boolean isMovable(Cell cell) {
		switch (cell.getString()) {
		case "GREENDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				return false;
			}

		case "REDDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				return false;
			}

		case "YELLOWDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				return false;
			}

		case "BLUEDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				return false;
			}

		case "W":
			return false;
		case "#":
			return false;
		case "D":
			if (((TokenDoor) cell).getOpened()) {
				return true;
			} else {
				return false;
			}

		case "@":
			return true;
		case "L":
			return false;
		case " ":
			return true;
		case "G":
			return true;
		default:
			return false;
		}
	}
}