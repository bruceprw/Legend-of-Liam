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

	private final String soundPath = "Sound\\creaking_door.mp3";
	protected String movDirection = "UP";

	final protected String UP = "UP";
	final protected String LEFT = "LEFT";
	final protected String DOWN = "DOWN";
	final protected String RIGHT = "RIGHT";

	protected final int ONE = 1;
	protected final int TWO = 2;
	protected final int ZERO = 0;
	protected final int HUNDRED = 100;
	
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
	
	public String getString() {
		return name;
	}

	/**
	 * Draws the enemy's sprite.
	 * 
	 * @param gc The graphics context's canvas buffer, used to draw to
	 * @param X  The X coordinate of where it needs to be drawn to
	 * @param Y  The Y coordinate of where it needs to be drawn to
	 */
	public void draw(GraphicsContext gc, int X, int Y) {
		gc.drawImage(image, X, Y, HUNDRED, HUNDRED);
	}

	/**
	 * Sets the image for the enemy's sprite.
	 * 
	 * @param path The path to the image file, stored in sub class
	 * @throws FileNotFoundException
	 */
	protected void setImage(String path) throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}
	
	/**
	 * Set the move direction of the enemy.
	 * @param movDirection New move direction.
	 */
	public void setMovDirection(String movDirection) {
		this.movDirection = movDirection;
	}
	
	/**
	 * gets the current move direction.
	 * @return current move direction.
	 */
	public String getMovDirection() {
		return this.movDirection;
	}
	
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
	 * @param gb the game board in play
	 * @param x  the X coordinate of new position
	 * @param y  the Y coordinate of new position
	 * @return true if next move is valid else false
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
}