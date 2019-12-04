package Collectibles;

import application.Element;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The super-class of all Collectibles.
 * @author user
 *
 */
public class Collectible extends Element
{
	protected Image image;
	protected int index;
	
	/**
	 * Get the image of the collectible.
	 * @return
	 */
	public Image getImage()
	{
		return image;
	}
	
	/**
	 * Draw the image.
	 */
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    /**
     * Index.
     * @return
     */
    public int getIndex()
    {
    	return index;
    }
    


}
