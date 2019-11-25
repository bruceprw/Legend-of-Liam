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
 *
 */
public class Player extends Element
{
	private String name;
	private Image avatar;
	private int score;
	private int[] inventory =
	{ 0, 0, 0, 0, 0, 0, 0 }; // Size of inventory size finalised when number of items finalised.
	private int[][] pos;
	private Image image;

	private String path = "Images\\player.jpg";

	public Player(String name) throws FileNotFoundException
	{
		setName(name);
		setAvatar(avatar);
		setImage();
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String getString()
	{
		return "START";
	}

	public void acquireInventory(int position)
	{
		inventory[position]++;
	}

	public void setInventory(int position, int num)
	{
		inventory[position] += num;
	}
	
	public int getTokenNum()
	{
		return inventory[0];
	}

	public int[] getInventory()
	{
		return inventory;
	}

	public void setAvatar(Image avatar)
	{
		this.avatar = avatar;
	}

	public Image getAvatar()
	{
		return avatar;
	}

	public void setPos(int[][] pos)
	{
		this.pos = pos;

	}

	public int[][] getPos()
	{
		return pos;
	}

	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y)
	{
		gc.drawImage(image, x, y);
	}

	public boolean checkInventory(int i)
	{
		return inventory[i] > 0;
	}

	public void dropCol(int i)
	{
		inventory[i]--;
	}

	public boolean movable(Cell cell)
	{
		switch (cell.getString())
		{
		case "GREENDOOR":
			if(((ColouredDoor) cell).getOpened())
			{
				return true;
			}
			else
			{
				if(checkInventory(2))
				{
					dropCol(2);
					((ColouredDoor)cell).openDoor();
					return true;
				}
				else
					return false;
			}

		case "REDDOOR":
			if(((ColouredDoor) cell).getOpened())
			{
				return true;
			}
			else
			{
				if(checkInventory(1))
				{
					dropCol(1);
					((ColouredDoor)cell).openDoor();
					return true;
				}
				else
					return false;
			}

		case "YELLOWDOOR":
			if(((ColouredDoor) cell).getOpened())
			{
				return true;
			}
			else
			{
				if(checkInventory(4))
				{
					dropCol(4);
					((ColouredDoor)cell).openDoor();
					return true;
				}
				else
					return false;
			}

		case "BLUEDOOR":
			if (((ColouredDoor)cell).getOpened())
			{
				return true;
			}
			else
			{
				if(checkInventory(3))
				{
					dropCol(3);
					((ColouredDoor)cell).openDoor();
					return true;
				}
				else
					return false;
			}

		case "W":
			if(checkInventory(6))
			{
				return true;
			}
			else
				return false;
		case "#":
			return false;
		case "D":
			if (((TokenDoor)cell).getOpened())
			{
				return true;
			}
			else
			{
				if(getTokenNum()==(((TokenDoor)cell).getTokenNum()))
				{
					for (int i=0; i<(((TokenDoor)cell).getTokenNum()); i++){
						dropCol(0);
					}
					((TokenDoor)cell).openDoor();
					return true;
				}
				else
					return false;
			}

		case "@":
			return true;
		case "L":
			if(checkInventory(5))
			{
				return true;
			}
			else
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
