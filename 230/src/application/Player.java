package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Collectibles.Collectible;
import cell.Cell;
import cell.ColouredDoor;
import cell.TokenDoor;
import javafx.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Player class -
 * 
 * @author Bruce Williams (972648)
 * @version 1.2
 */
public class Player extends Element {
	private String name;
	private Image avatar;
	private int score;
	private int[] inventory = { 0, 0, 0, 0, 0, 0, 0 }; 
	private int[][] pos;
	private Image image;

	private String path = "Images\\player.jpg";
	/**
	 * @param name name of player.
	 * @throws FileNotFoundException.
	 */
	public Player(String name) throws FileNotFoundException {
		setName(name);
		setAvatar(avatar);
		setImage();
	}
	/**
	 * 
	 * @param name name of player.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return name name of player.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return "START".
	 */
	public String getString() {
		return "START";
	}
	/**
	 * Adds 1 of item to inventory, position corresponds to itemID:
	 * 0=Token, 1=RedKey, 2=GreenKey, 3=BlueKey, 4=YellowKey, 5=Fireboot, 6=Flipper.
	 *	
	 * @param position.
	 */
	public void acquireInventory(int position) {
		inventory[position]++;
	}
	/**
	 * updates number of specifed item to given num.
	 * @param position itemID.
	 * @param num number of items.
	 */
	public void setInventory(int position, int num) {
		inventory[position] += num;
	}
	/**
	 * 
	 * @return number of tokens.
	 */
	public int getTokenNum() {
		return inventory[0];
	}
	/**
	 * 
	 * @return inventory
	 */
	public int[] getInventory() {
		return inventory;
	}
	/**
	 * Updates avatar.
	 * @param avatar
	 */
	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}
	/**
	 * 
	 * @return avatar the visual representation of the player.
	 */
	public Image getAvatar() {
		return avatar;
	}
	
	/**
	 * updates position of player
	 * @param pos position of player
	 */
	public void setPos(int[][] pos) {
		this.pos = pos;

	}
	/**
	 * 
	 * @return pos position of player on board
	 */
	public int[][] getPos() {
		return pos;
	}

	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}
	/**
	 * @param x width of image
	 * @param y height of imagge
	 */
	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y);
	}
	/**
	 * 
	 * @param i itemID
	 * @return TRUE if inventory contains that specified item, else FALSE.
	 */
	public boolean checkInventory(int i) {
		return inventory[i] > 0;
	}
	
	/**
	 * Removes 1 of specified item
	 * @param i itemID
	 */
	public void dropCol(int i) {
		inventory[i]--;
	}
	/**
	 * checks if player can move onto specified cell
	 * @param cell 
	 * @return
	 */
	public boolean movable(Cell cell) {
		switch (cell.getString()) {
		case "GREENDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				if (checkInventory(2)) {
					dropCol(2);
					((ColouredDoor) cell).openDoor();
					return true;
				} else
					return false;
			}

		case "REDDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				if (checkInventory(1)) {
					dropCol(1);
					((ColouredDoor) cell).openDoor();
					return true;
				} else
					return false;
			}

		case "YELLOWDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				if (checkInventory(4)) {
					dropCol(4);
					((ColouredDoor) cell).openDoor();
					return true;
				} else
					return false;
			}

		case "BLUEDOOR":
			if (((ColouredDoor) cell).getOpened()) {
				return true;
			} else {
				if (checkInventory(3)) {
					dropCol(3);
					((ColouredDoor) cell).openDoor();
					return true;
				} else
					return false;
			}

		case "W":
			if (checkInventory(6)) {
				return true;
			} else
				return false;
		case "#":
			return false;
		case "D":
			if (((TokenDoor) cell).getOpened()) {
				return true;
			} else {
				if (getTokenNum() == (((TokenDoor) cell).getTokenNum())) {
					for (int i = 0; i < (((TokenDoor) cell).getTokenNum()); i++) {
						dropCol(0);
					}
					((TokenDoor) cell).openDoor();
					return true;
				} else
					return false;
			}

		case "@":
			return true;
		case "L":
			if (checkInventory(5)) {
				return true;
			} else
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
