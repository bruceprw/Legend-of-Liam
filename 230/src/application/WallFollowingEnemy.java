package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Wall Following Enemies follows wall
 * 
 * @author Miles Singleton
 *
 */
public class WallFollowingEnemy extends Enemy
{

	private String path = "Images\\wallHugE.jpg";
	private Image image;

	public WallFollowingEnemy(int currentX, int currentY, String movDirection) throws FileNotFoundException
	{
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.movDirection = movDirection;
		setImage();
	}

	String getDirection()
	{
		return movDirection;
	}

	public String getString()
	{
		return "WALLHUG";
	}

	public void setImage() throws FileNotFoundException
	{
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y)
	{

		gc.drawImage(image, x, y, 100, 100);
	}
}