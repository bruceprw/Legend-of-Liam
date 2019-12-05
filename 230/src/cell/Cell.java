package cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

/**
 * Super class for all cell types
 * 
 * @author
 *
 */
public class Cell extends Element {
	// whether the cell can kill the user
	private boolean harmable;
	//the image of the cell type
	protected Image image;
	// sets if player is allowed to move to cell
	private boolean playerAllowed;
	// sets if enemy is allowed to move to cell
	private boolean enemyAllowed;
	// the path of the texture for the cell
	private String texturePath;
	// will contain the item required to access this cell
	private Item itemRequiredForCell;
	protected MediaPlayer mediaPlayer;
	private final int HUNDRED = 100;
	protected String name = "";

	/**
	 * Creates new instance of cell type
	 * 
	 * @param harmable            is this harmful to the player
	 * @param playerAllowed       can the player stand on it
	 * @param enemyAllowed        can the enemy stand on it
	 * @param texturePath         path for the image of the cell
	 * @param itemRequiredForCell what item is required to access the cell
	 */
	public Cell(boolean harmable, boolean playerAllowed, boolean enemyAllowed, String texturePath,
			Item itemRequiredForCell) {
		this.harmable = harmable;
		this.playerAllowed = playerAllowed;
		this.enemyAllowed = enemyAllowed;
		this.texturePath = texturePath;
		this.itemRequiredForCell = itemRequiredForCell;
	}

	/**
	 * Returns if the cell is harmful 
	 * @return True if it is, else false
	 */
	public boolean getIsHarmable() {
		return harmable;
	}

	/**
	 * Returns if player is allowed on the cell
	 * @return True if they are, else false
	 */
	public boolean getIsPlayerAllowed() {
		return playerAllowed;
	}

	/**
	 * Returns if the enemy is allowed on the cell
	 * @return True if they are, else false
	 */
	public boolean getIsEnemyAllowed() {
		return enemyAllowed;
	}

	/**
	 * Gets the path to the image used for the cells texture path
	 * @return path to cell image
	 */
	public String getTexturePath() {
		return texturePath;
	}

	/**
	 * Gets the item that is on the cell
	 * @return the item on the cell
	 */
	public Item getItemRequiredForCell() {
		return itemRequiredForCell;
	}

	/**
	 * Set if player is allowed on the cell
	 * @param playerAllowed True if they are, else false
	 */
	public void setPlayerAllowed(boolean playerAllowed) {
		this.playerAllowed = playerAllowed;
	}

	/**
	 * Set if enemy is allowed on the cell
	 * @param enemyAllowed True if they are, else false
	 */
	public void setEnemyAllowed(boolean enemyAllowed) {
		this.enemyAllowed = enemyAllowed;
	}

	/**
	 * Gets the sound to play for the cell
	 * @return the mediaPlayer object to do so
	 */
	public MediaPlayer getSound() {
		return mediaPlayer;
	}
	/**
     * Returns the name of the class to the fileOutputer class 
     * @return a one charector string to indicate the class
     */
    public String getString()
    {
    	return this.name;
    }

	/**
     * Set image for the cell.
     * @throws FileNotFoundException
     */
	public void setImage(String path) throws FileNotFoundException
	{
		image = new Image (new FileInputStream(path));
	}
	
	/**
	 * Draw image the cell image.
	 */
    public void draw(GraphicsContext gc,int x,int y) 
    {
		gc.drawImage(image,x,y,HUNDRED,HUNDRED);
    }
}