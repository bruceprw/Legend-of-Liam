package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import application.Element;
import cell.Cell;
import cell.ColouredDoor;
import cell.TokenDoor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class is used to create a player on the screen.
 * 
 * @author Bruce Williams (972648)
 * @version 1.2
 */
public class Player extends Element {
	private static final String START = "START";

	private String name;
	private int[] inventory = { 0, 0, 0, 0, 0, 0, 0 };
	private int[][] pos;
	private Image image;

	private String path = "Images\\Player2.png";
	// private java.awt.Image img;

	/**
	 * @param name name of player.
	 * @throws FileNotFoundException if file not found.
	 */
	public Player(String name) throws FileNotFoundException {
		setName(name);
		setImage();
	}

	/**
	 * Sets the players name.
	 * 
	 * @param name name of player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the players name.
	 * 
	 * @return name name of player.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns player string.
	 * 
	 * @return START used in level loading.
	 */
	public String getString() {
		return START;
	}

	/*
	 * Guide to the items code 0=Token, 1=RedKey, 2=GreenKey, 3=BlueKey,
	 * 4=YellowKey, 5=Fireboot, 6=Flipper. See item.java.
	 */

	/**
	 * Adds a item to inventory, position corresponds to itemID:
	 * 
	 * @param position position of player.
	 */
	public void acquireInventory(int position) {
		inventory[position]++;
	}

	/**
	 * updates number of specified item to given number of items.
	 * 
	 * @param position itemID.
	 * @param number   number of items.
	 */
	public void setInventory(int position, int number) {
		inventory[position] += number;
	}

	/**
	 * Gets the number of tokens the player has.
	 * 
	 * @return number of tokens.
	 */
	public int getTokenNum() {
		return inventory[0];
	}

	/**
	 * Returns the inventory array, containing all items currently collected by the
	 * player.
	 * 
	 * @return inventory int array, each item is represented by its item code.
	 */
	public int[] getInventory() {
		return inventory;
	}

	/**
	 * updates position of player.
	 * 
	 * @param pos position of player.
	 */
	public void setPos(int[][] pos) {
		this.pos = pos;

	}

	/**
	 * Gets the position of the player.
	 * 
	 * @return pos position of player on board.
	 */
	public int[][] getPos() {
		return pos;
	}

	/**
	 * Sets the player's sprite.
	 * 
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error of this type.
	 */
	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

	/**
	 * Draws the player sprite to screen
	 * 
	 * @param gc the graphics buffer of the canvas to be drawn to.
	 * @param x  width of image.
	 * @param y  height of image.
	 * @param r  the rotation of the image.
	 */
	public void drawPlayer(GraphicsContext gc, int x, int y, int r) {
		gc.save();
		gc.translate(x + 50, y + 50);
		gc.rotate(r);
		// gc.drawImage(image, -(1), -(1));
		gc.drawImage(image, -(100 / 2), -62);
		gc.restore();
	}

	/**
	 * checks if a specific item is in the inventory of the player. Item is
	 * specified by an int.
	 * 
	 * @param i itemID.
	 * @return TRUE if inventory contains that specified item, else FALSE.
	 */
	public boolean checkInventory(int i) {
		return inventory[i] > 0;
	}

	/**
	 * Removes a specified item from inventory.
	 * 
	 * @param i The itemID.
	 */
	public void dropCol(int i) {
		inventory[i]--;
	}

	/**
	 * checks if player can move onto specified cell and responds appropriately.
	 * 
	 * @param cell cell on board.
	 * @return True if given tile is movable, else false.
	 */
	public boolean movable(Cell cell) {
		switch (cell.getString()) {
		case "GREENDOOR":
		case "REDDOOR":
		case "YELLOWDOOR":
		case "BLUEDOOR":
			return movableColouredDoor((ColouredDoor) cell);

		case "W":
			if (checkInventory(6)) {
				cell.playSound();
				return true;
			} else
				return false;

		case "#":
			cell.playSound();
			return false;

		case "D":
			return movableTokenDoor((TokenDoor) cell);

		case "L":
			if (checkInventory(5)) {
				cell.playSound();
				return true;
			} else
				return false;

		case "@":
		case " ":
		case "G":
			cell.playSound();
			return true;

		default:
			return false;
		}
	}

	/**
	 * Checks whether the coloured door can be walked onto. Potentially opens the
	 * ColouredDoor if the right key is in the inventory.
	 * 
	 * @param cell The ColouredDoor to be checked.
	 * @return True if the cell can be walked on. False, otherwise.
	 */
	private boolean movableColouredDoor(ColouredDoor cell) {
		int inventoryNum;

		switch (cell.getString()) {
		case "REDDOOR":
			inventoryNum = 1;
			break;
		case "GREENDOOR":
			inventoryNum = 2;
			break;
		case "BLUEDOOR":
			inventoryNum = 3;
			break;
		case "YELLOWDOOR":
			inventoryNum = 4;
			break;
		default:
			throw new IllegalArgumentException("Method called on wrong cell.");
		}

		if (cell.getOpened()) {
			return true;
		} else {
			if (checkInventory(inventoryNum)) {
				dropCol(inventoryNum);
				cell.playOpenSound();
				cell.openDoor();
				return true;
			} else {
				cell.playKnockSound();
				return false;
			}
		}
	}

	/**
	 * Checks whether the given token door can be walked onto. Potentially opens the
	 * token door if there are enough tokens in the inventory.
	 * 
	 * @param cell The TokenDoor to be checked.
	 * @return True if the cell can be walked on. False otherwise.
	 */
	private boolean movableTokenDoor(TokenDoor cell) {
		if (cell.getOpened()) {
			return true;
		} else {
			if (getTokenNum() >= cell.getTokenNum()) {
				for (int i = 0; i < (((TokenDoor) cell).getTokenNum()); i++) {
					dropCol(0);
				}
				cell.playOpenSound();
				cell.openDoor();
				return true;
			} else {
				cell.playKnockSound();
				return false;
			}
		}
	}
}
