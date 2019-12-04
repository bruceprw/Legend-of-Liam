package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The super-class of all other elements.
 * @author user
 *
 */
public class Element
{
	/**
	 * 
	 * @return String for output purpose.
	 */
	public String getString()
	{
		return"";
	}
	
	/**
	 * Draw the image according to the x,y position.
	 * @param gc the graphics context of canvas.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void draw(GraphicsContext gc, int x, int y)
	{
		
	}
	
	/**
	 * Draw the image according to the x,y position.
	 * @param gc the graphics context of canvas.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void drawPlayer(GraphicsContext gc, int x, int y, int r)
	{
		
	}
	
	/**
	 * Plays sound.
	 */
	public void playSound() {
		
	}
}