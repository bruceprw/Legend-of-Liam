package application;


import cell.Cell;
import cell.Item;
import javafx.scene.canvas.GraphicsContext;

/**
 * The empty cell just for complete an array.
 * @author user
 *
 */
public class Empty extends Cell
{
	/**
	 * Create instance of empty cell.
	 */
	public Empty()
	{
		super(false, true, true, "", Item.NONE);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Draw nothing.
	 */
	public void draw(GraphicsContext gc,int x, int y)
	{
		
	}
}
