package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cell.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Wall Following Enemies follows wall
 * 
 * @author Miles Singleton
 *
 */
public class WallFollowingEnemy extends Enemy {

	private String path = "Images\\wallHugE.jpg";
	private Image image;

	public WallFollowingEnemy(int currentX, int currentY, String movDirection) throws FileNotFoundException {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.movDirection = movDirection;
		setImage();
	}
	
	public WallFollowingEnemy(int currentX, int currentY) throws FileNotFoundException {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;
		this.movDirection = UP;
		setImage();
	}
	
	

	public int[] moveToCorner(int currentX, int currentY, String wallDirection) {
		int[] a = { 1, 2 };
		switch (this.getMovDirection()) {

		case (UP):
			a[0] = getNewWallXPosition(currentX, wallDirection);
			a[1] = currentY - ONE;
			return a;

		case (DOWN):
			a[0] = getNewWallXPosition(currentX, wallDirection);
			a[1] = currentY + ONE;
			return a;

		case (LEFT):
			a[0] = currentX - ONE;
			a[1] = getNewWallYPosition(currentY, wallDirection);
			return a;

		case (RIGHT):
			a[0] = currentX + ONE;
			a[1] = getNewWallYPosition(currentY, wallDirection);
			return a;
		default:
			throw new IllegalStateException("Undifened direction");
		}

	}

	private int getNewWallXPosition(int currentX, String wallDirection) {
		switch (wallDirection) {
		case (LEFT):
			return currentX - TWO;
		case (RIGHT):
			return currentX + TWO;
		default:
			throw new IllegalStateException("Undifened direction");
		}
	}

	private int getNewWallYPosition(int currentY, String wallDirection) {
		switch (wallDirection) {
		case (UP):
			return currentY - TWO;
		case (DOWN):
			return currentY + TWO;
		default:
			throw new IllegalStateException("Undifened direction");
		}
	}

	public String getDirection() {
		return movDirection;
	}

	public String getString() {
		return "WALLHUG";
	}

	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

	public void draw(GraphicsContext gc, int x, int y) {

		gc.drawImage(image, x, y, 100, 100);
	}
}