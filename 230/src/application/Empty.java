package application;

import cell.Cell;
import cell.Item;
import javafx.scene.canvas.GraphicsContext;

/**
 * The empty cell just for complete an array.
 * 
 * @author Andy Kuo
 * @version 1.0
 *
 */
public class Empty extends Cell {
	/**
	 * Create instance of empty cell.
	 */
	public Empty() {
		super(false, true, true, "", Item.NONE);
	}

	/**
	 * Draw nothing as the tile is empty.
	 */
	public void draw(GraphicsContext gc, int x, int y) {

	}
}
