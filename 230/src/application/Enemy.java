package application;

import cell.Cell;
import cell.ColouredDoor;

abstract class Enemy extends Element {

	/// the current X and Y position
	protected int currentPositionX;
	protected int currentPositionY;

	/// the next position for the enemy
	protected int nextPositionX;
	protected int nextPositionY;

	protected String movDirection;

	//Constants
	///Directions
	final private String UP = "North";
	final private String LEFT = "East";
	final private String DOWN = "South";
	final private String RIGHT = "West";
	
	final private int ONE = 1;

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

	

	public int[] moveTo(int currentX, int currentY, Cell cell) {
		switch (this.getMovDirection()) {
		case (UP):
			// TODO break this into a method
			if (this.isMovable(cell)) {
				int[] a = { currentX + ONE, currentY };
				return a;
			} else {
				this.reverseDirection();
				int[] a = {};
				return a;
			}
		case (LEFT):
			if (this.isMovable(cell)) {
				int[] a = { currentX, currentY + ONE };
				return a;
			} else {
				this.reverseDirection();
				// TODO get opposite cell
				this.moveTo(currentX, currentY, cell);
			}
		case (DOWN):
			if (this.isMovable(cell)) {
				int[] a = { currentX - ONE, currentY };
				return a;
			} else {
				this.reverseDirection();
				// TODO get opposite cell
				this.moveTo(currentX, currentY, cell);
			}
		case (RIGHT):
			if (this.isMovable(cell)) {
				int[] a = { currentX, currentY - ONE };
				return a;
			} else {
				this.reverseDirection();
				// TODO get opposite cell
				this.moveTo(currentX, currentY, cell);
			}
		default:
			throw new IllegalStateException("Undifened direction");

		}
	}

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
			if (((ColouredDoor) cell).getOpened()) {
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