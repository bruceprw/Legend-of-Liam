package cell;

import application.Element;
import javafx.scene.media.MediaPlayer;

public class Cell extends Element
{
	// whether the cell can kill the user
	private boolean harmable;
	// sets if player is allowed to move to cell
	private boolean playerAllowed;
	// sets if enemy is allowed to move to cell
	private boolean enemyAllowed;
	// the path of the texture for the cell
	private String texturePath;
	// will contain the item required to access this cell
	private Item itemRequiredForCell;
	private MediaPlayer mediaPlayer;

	public Cell(boolean harmable, boolean playerAllowed, boolean enemyAllowed, String texturePath, Item itemRequiredForCell)
	{
		this.harmable = harmable;
		this.playerAllowed = playerAllowed;
		this.enemyAllowed = enemyAllowed;
		this.texturePath = texturePath;
		this.itemRequiredForCell = itemRequiredForCell;
		
		//item = Item.NONE;
	}

	/*
	 * public boolean isItemAdded() {
	 * 
	 * }
	 * 
	 * public boolean isItemDeducted() {
	 * 
	 * }
	 * 
	 * public boolean isPlayerMoved() {
	 * 
	 * }
	 * 
	 * public boolean isPlayerKilled() {
	 * 
	 * }
	 */

	public boolean getIsHarmable()
	{
		return harmable;
	}

	public boolean getIsPlayerAllowed()
	{
		return playerAllowed;
	}

	public boolean getIsEnemyAllowed()
	{
		return enemyAllowed;
	}

	public String getTexturePath()
	{
		return texturePath;
	}

	public Item getItemRequiredForCell()
	{
		return itemRequiredForCell;
	}

	public void setConsumesItem(boolean consumeableItem)
	{
	}

	public void setPlayerAllowed(boolean playerAllowed)
	{
		this.playerAllowed = playerAllowed;
	}

	public void setEnemyAllowed(boolean enemyAllowed)
	{
		this.enemyAllowed = enemyAllowed;
	}
	
	public MediaPlayer getSound()
	{
		return mediaPlayer;
	}
	
/*
	public void setItem(Item item)
	{
		this.item = item;
	}*/
}