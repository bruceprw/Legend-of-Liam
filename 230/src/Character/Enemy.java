package Character;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import application.Empty;
import application.GameBoard;
import cell.Ground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The super class of all enemy.
 * 
 * @author Miles Singleton
 *
 */
public abstract class Enemy extends Element {

	protected static final String UP = "UP";
	protected static final String LEFT = "LEFT";
	protected static final String DOWN = "DOWN";
	protected static final String RIGHT = "RIGHT";

	protected static final int ONE = 1;
	protected static final int TWO = 2;
	protected static final int ZERO = 0;
	protected static final int HUNDRED = 100;
	
	// is this a constant?
	private final String soundPath = "Sound\\creaking_door.mp3";
	
	protected String movDirection = "UP";
	protected String name = "Enemy";

	protected int currentPositionX;
	protected int currentPositionY;

	protected Image image;

	/**
	 * plays sound effect for enemy.
	 */
	public void playSound() {
		Media music = new Media(new File(soundPath).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(music);
		mediaPlayer.play();
	}
	
	/**
	 * Gets the name of the Enemy.
	 * 
	 * @return The name of the Enemy.
	 */
	public String getString() {
		return name;
	}

	/**
	 * Draws the enemy's sprite.
	 * 
	 * @param gc The graphics context's canvas buffer, used to draw to.
	 * @param X  The X coordinate of where it needs to be drawn to.
	 * @param Y  The Y coordinate of where it needs to be drawn to.
	 */
	public void draw(GraphicsContext gc, int X, int Y) {
		gc.drawImage(image, X, Y, HUNDRED, HUNDRED);
	}
	
	/**
	 * Set the move direction of the enemy.
	 * @param movDirection New move direction.
	 */
	public void setMovDirection(String movDirection) {
		this.movDirection = movDirection;
	}
	
	/**
	 * Gets the current move direction.
	 * @return current move direction.
	 */
	public String getMovDirection() {
		return this.movDirection;
	}
	
	/**
	 * Reverses the enemy's current move direction.
	 */
	public void reverseDirection() {
		switch (this.getMovDirection()) {
		case (UP):
			this.setMovDirection(DOWN);
			break;
		case (LEFT):
			this.setMovDirection(RIGHT);
			break;
		case (DOWN):
			this.setMovDirection(UP);
			break;
		case (RIGHT):
			this.setMovDirection(LEFT);
			break;
		default:
			throw new IllegalStateException("Undifened direction");
		}
	}


	/**
	 * Checks if next move is possible.
	 * 
	 * @param gb The game board in play.
	 * @param x  The X coordinate of new position.
	 * @param y  The Y coordinate of new position.
	 * @return True if next move is valid else false.
	 */
	public static boolean checkMove(GameBoard gb, int x, int y) {
		Element[][] board = gb.getBoard();
		Element[][] background = gb.getBackground();
		boolean a = (board[y][x] instanceof Player || board[y][x] instanceof Empty);

		if ((background[y][x] instanceof Ground) && (a))
			return true;
		else
			return false;

	}
	
	/**
	 * Sets the image for the enemy's sprite.
	 * 
	 * @param path The path to the image file, stored in sub class.
	 * @throws FileNotFoundException Tells the system that this
	 * method could cause an error of this type.
	 */
	protected void setImage(String path) throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}
}