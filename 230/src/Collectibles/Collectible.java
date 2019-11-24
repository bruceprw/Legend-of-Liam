package Collectibles;

import application.Element;
import application.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Collectible extends Element
{
	protected Image image;
	protected int index;
	
	public Image getImage()
	{
		return image;
	}
	
    public void draw(GraphicsContext gc,int x,int y)
    {
		gc.drawImage(image,x,y,100,100);
    }
    
    public int getIndex()
    {
    	return index;
    }
    


}
