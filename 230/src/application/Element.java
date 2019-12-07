package application;

import javafx.scene.canvas.GraphicsContext;

/**
 * The super-class of all other elements.
 * 
 * @author Andy Kuo
 * @version 1.0
 *
 */
public abstract class Element {

	/**
	 * Draw the image according to the x,y position.
	 * 
	 * @param gc the graphics context of canvas.
	 * @param x  the x coordinate.
	 * @param y  the y coordinate.
	 */
	public void draw(GraphicsContext gc, int x, int y) {

	}

	/**
	 * Draw the image according to the x,y position.
	 * 
	 * @param gc the graphics context of canvas.
	 * @param x  the x coordinate.
	 * @param y  the y coordinate.
	 */
	public void drawPlayer(GraphicsContext gc, int x, int y, int r) {

	}

	public String getString() {
		return null;
	}

	/**
	 * Plays sound from stored sound file.
	 */
	public void playSound() {

	}
}