package Character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Element;
import application.Empty;
import application.GameBoard;
import cell.Ground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Straight line enemy moves in a straight line
 * 
 * @author Miles Singleton
 */
public class StraightLineEnemy extends Enemy {

	private String path = "Images\\straightE.png";
	private Image image;
	private String movDirection;

	private final String TYPE = "STRAIGHT";

	/**
	 * Constructor for straight line enemy
	 * 
	 * @param currentX     The X position of enemy on game board
	 * @param currentY     The Y position of enemy on game board
	 * @param movDirection the move direction of the enemy
	 * @throws FileNotFoundException Only thrown if image can't be loaded
	 */
	public StraightLineEnemy(int currentX, int currentY, String movDirection) throws FileNotFoundException {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;

		this.movDirection = movDirection;
		setImage();
	}

	/**
	 * Constructor for straight line enemy, used if move direction has not be
	 * defined
	 * 
	 * @param currentX The X position of enemy on game board
	 * @param currentY The Y position of enemy on game board
	 * @throws FileNotFoundException Only thrown if image can't be loaded
	 */
	public StraightLineEnemy(int currentX, int currentY) throws FileNotFoundException {
		this.currentPositionX = currentX;
		this.currentPositionY = currentY;

		this.movDirection = UP;
		setImage();
	}

	/**
	 * @return the move direction
	 */
	public String getDirection() {
		return movDirection;
	}

	/**
	 * @return the subclass of enemy
	 */
	public String getString() {
		return TYPE;
	}

	/**
	 * sets the image for enemy
	 * 
	 * @throws FileNotFoundException Thrown if file not found
	 */
	public void setImage() throws FileNotFoundException {
		image = new Image(new FileInputStream(path));
	}

	/**
	 * Draws the enemy sprite on screen
	 * 
	 * @param gc
	 * @param x  X coordinate on screen to draw at
	 * @param y  Y coordinate on screen to draw at
	 */
	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y, HUNDRED, HUNDRED);
	}

	/**
	 * Works out the new X coordinate
	 * 
	 * @param gb the game board currently in play
	 * @param x  the X position of the enemy
	 * @param y  the Y position of the enemy
	 * @return the new X position
	 */
	public int getX(GameBoard gb, int x, int y) {

		switch (movDirection) {
		case UP:
			if (checkMove(gb, x, y - ONE)) {
				return x;
			} else {
				return x;
			}

		case DOWN:
			if (checkMove(gb, x, y + ONE))
				return x;
			else {
				return x;
			}

		case LEFT:
			if (checkMove(gb, x - ONE, y)) {
				return x - ONE;
			} else {
				return x + ONE;
			}

		case RIGHT:
			if (checkMove(gb, x + ONE, y)) {
				return x + ONE;
			} else {
				return x - ONE;
			}
		default:
			return ZERO;
		}

	}

	/**
	 * Works out the new Y coordinate
	 * 
	 * @param gb the game board currently in play
	 * @param x  the X position of the enemy
	 * @param y  the Y position of the enemy
	 * @return the new Y position
	 */
	public int getY(GameBoard gb, int x, int y) {

		switch (movDirection) {
		case UP:
			if (checkMove(gb, x, y - ONE)) {
				return y - ONE;
			} else {
				reverseDirection();
				return y + ONE;
			}

		case DOWN:
			if (checkMove(gb, x, y + ONE)) {
				return y + ONE;
			} else {
				reverseDirection();
				return y - ONE;
			}

		case LEFT:
			if (checkMove(gb, x - ONE, y))
				return y;
			else {
				reverseDirection();
				return y;
			}

		case RIGHT:
			if (checkMove(gb, x + ONE, y))
				return y;
			else {
				reverseDirection();
				return y;
			}
		default:
			return 0;
		}

	}

	/**
	 * Checks if the next horizontal move is possible
	 * 
	 * @param gb current game board in play
	 * @return true if its valid else false
	 */
	//TODO variable not used, still needed?
	public boolean horizontalNoMove(GameBoard gb,int x,int y) {
		Element[][] board = gb.getBoard();
		Element[][] bg = gb.getBackground();
		boolean left = board[currentPositionY][currentPositionX - ONE] instanceof Empty;
		boolean right = board[currentPositionY][currentPositionX + ONE] instanceof Empty;
		boolean bgLeft = bg[currentPositionY][currentPositionX - ONE] instanceof Ground;
		boolean bgRight = bg[currentPositionY][currentPositionX + ONE] instanceof Ground;
		return ((!left) && (!right)) || ((!bgLeft) && (!bgRight));
	}

	/**
	 * Checks if the next horizontal move is valid
	 * 
	 * @param gb current game board in play
	 * @return true if its valid else false
	 */
	public boolean verticalNoMove(GameBoard gb,int x,int y) {
		Element[][] board = gb.getBoard();
		Element[][] bg = gb.getBackground();
		boolean up = board[currentPositionY - ONE][currentPositionX] instanceof Empty;
		boolean down = board[currentPositionY + ONE][currentPositionX] instanceof Empty;
		boolean bgUp = bg[currentPositionY - ONE][currentPositionX] instanceof Ground;
		boolean bgDown = bg[currentPositionY + ONE][currentPositionX] instanceof Ground;
		return ((!up) && (!down)) || ((!bgUp) && (!bgDown));
	}

}