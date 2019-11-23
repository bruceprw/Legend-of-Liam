package application;

import javafx.*;
import javafx.scene.image.Image;

/**
 * Player class -
 * 
 * @author Bruce Williams (972648)
 *
 */
public class Player  extends Element{
	private String name;
	private Image avatar;
	private int score;
	private int[] inventory= {0,0,0,0,0,0,0}; // Size of inventory size finalised when number of items finalised.
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

    public String getString()
    {
    	return"START";
    }
	
	public void setInventory(int position,int num)
	{
		inventory[position]=num;
	}
	
	public int[] getInventory()
	{
		return inventory;
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
}
