package application;

import javafx.*;
import javafx.scene.image.Image;

/**
 * Player class -
 * 
 * @author Bruce Williams (972648)
 *
 */
public class Player {
	private String name;
	private Image avatar;
	private int score;
	private int[] inventory = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private int[][] pos;

	public Player(String name) {
		setName(name);
		setAvatar(avatar);

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}

	public Image getAvatar() {
		return avatar;
	}

	public void setPos(int[][] pos) {
		this.pos = pos;

	}

	public int[][] getPos() {
		return pos;
	}

	/**
	 * @param index - index corresponds to itemID (0=token, 1=redKey, 2=greenKey,
	 *  3=blueKey, 4=yellowKey, 5=fireBoot, 6=flipper).
	 * @param num the number of that item held by the player.
	 */
	public void setInventory(int index, int num) {
		this.inventory[index] = num;
	}

	public int getInventory(int index) {
		return inventory[index];

	}
}

