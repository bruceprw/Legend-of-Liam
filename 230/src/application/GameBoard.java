package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Collectibles.BlueKey;
import Collectibles.Collectible;
import Collectibles.FireBoot;
import Collectibles.Flipper;
import Collectibles.GreenKey;
import Collectibles.RedKey;
import Collectibles.Token;
import Collectibles.YellowKey;
import cell.Cell;
import cell.Teleporter;
import cell.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class GameBoard {
	private Element[][] board;
	private Element[][] background;
	private Element[][] fog;
	private int playerX;
	private int playerY;
	private int goalX;
	private int goalY;

	private ArrayList<Integer> enemyX;
	private ArrayList<Integer> enemyY;

	final private String UP = "North";
	final private String LEFT = "East";
	final private String DOWN = "South";
	final private String RIGHT = "West";

	final private int ONE = 1;
	final private int TWO = 2;

	public GameBoard(String filePath) throws FileNotFoundException {
		FileReader lvl = new FileReader(filePath);
		this.board = lvl.getBoard();
		this.background = lvl.getBackground();
		playerX = lvl.getPlayerX();
		playerY = lvl.getPlayerY();
		fog = lvl.getFog();
		enemyX = lvl.getEnemyX();
		enemyY = lvl.getEnemyY();
		goalX = lvl.getGoalX();
		goalY = lvl.getGoalY();
	}

	public void drawFog(GraphicsContext gc) {
		for (int y = playerY - 3, j = 0; y < playerY + 4; y++, j += 100) {
			for (int x = playerX - 3, i = 0; x < playerX + 4; x++, i += 100) {
				fog[y][x].draw(gc, i, j);
			}
		}
	}

	public void setFog() {
		for (int y = playerY - 2; y < playerY + 3; y++) {
			for (int x = playerX - 2; x < playerX + 3; x++) {
				fog[y][x] = new Empty();
			}
		}
	}

	public void drawGame(GraphicsContext gc) throws FileNotFoundException {

		for (int y = playerY - 2, j = 0; y < playerY + 3; y++, j += 100) {
			for (int x = playerX - 3, i = 0; x < playerX + 4; x++, i += 100) {
				background[y][x].draw(gc, i, j);
				board[y][x].draw(gc, i, j);
			}
		}
		// TimeUnit.SECONDS.sleep(2);
		setFog();

		int[] temp = ((Player) board[playerY][playerX]).getInventory();
		for (int i = 0; i < 7; i++) {
			System.out.print(temp[i]);
		}
		System.out.println();
		drawItem(gc);
		// drawFog(gc);
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public boolean end() {
		return playerY == goalY && playerX == goalX;
	}

	public void drawItem(GraphicsContext gc) throws FileNotFoundException {
		int[] temp = ((Player) board[playerY][playerX]).getInventory();
		Token token = new Token();
		RedKey r = new RedKey();
		GreenKey g = new GreenKey();
		BlueKey b = new BlueKey();
		YellowKey y = new YellowKey();
		FireBoot f = new FireBoot();
		Flipper fl = new Flipper();
		gc.drawImage(token.getImage(), 0, 500, 75, 75);
		gc.drawImage(r.getImage(), 150, 500, 75, 75);
		gc.drawImage(g.getImage(), 275, 500, 75, 75);
		gc.drawImage(b.getImage(), 400, 500, 75, 75);
		gc.drawImage(y.getImage(), 525, 500, 75, 75);
		gc.drawImage(f.getImage(), 0, 600, 75, 75);
		gc.drawImage(fl.getImage(), 150, 600, 75, 75);
		gc.setFont(new Font("Arial", 50));
		gc.strokeText(": " + temp[0], 75, 560);
		gc.strokeText(": " + temp[1], 225, 560);
		gc.strokeText(": " + temp[2], 350, 560);
		gc.strokeText(": " + temp[3], 475, 560);
		gc.strokeText(": " + temp[4], 600, 560);
		gc.strokeText(": " + temp[5], 75, 660);
		gc.strokeText(": " + temp[6], 225, 660);
	}


	public void playBoardSound(int x, int y) throws IOException
	{
		switch(board[y][x].getString())
		{
		case "F": //flipper
			board[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "O": //fireboot
			board[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "RKEY":
			board[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "BKEY":
			board[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "YKEY":
			board[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "GKEY":
			board[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "T": //Token
			board[y][x].playSound("Sound\\341695__projectsu012__coins-1( coin).wav");
			break;
		}
	}
	
	public void playBackSound(int x, int y) throws IOException
	{
		switch(background[y][x].getString())
		{
		case "#": //wall
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "W": //water
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "L": //lava
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "D": //token door
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "@": //teleporter
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case " ": //ground
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "G": //goal
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "REDDOOR":
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "BLUEDOOR":
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "YELLOWDOOR":
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		case "GREENDOOR":
			background[y][x].playSound("Sound\\Water Splash-SoundBible.com-800223477.mp3");
			break;
		}
	}
	
	public boolean move(String way) {
		switch (way) {
		case "right":
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX + 1])) {
				if (((Cell) background[playerY][playerX + 1]) instanceof Teleporter) {
					Teleporter temp = ((Teleporter) background[playerY][playerX + 1]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {
					if (board[playerY][playerX + 1] instanceof Collectible)
						acquire((Collectible) board[playerY][playerX + 1]);
					board[playerY][playerX + 1] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playBoardSound(playerY,playerX+1);
					playBackSound(playerY,playerX+1);
					playerX = playerX + 1;
				}

			}
			break;
		case "left":
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX - 1])) {
				if (((Cell) background[playerY][playerX - 1]) instanceof Teleporter) {
					Teleporter temp = ((Teleporter) background[playerY][playerX - 1]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {

					if (board[playerY][playerX - 1] instanceof Collectible)
						acquire((Collectible) board[playerY][playerX - 1]);

					board[playerY][playerX - 1] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playBoardSound(playerY,playerX+1);
					playBackSound(playerY,playerX+1);
					playerX = playerX - 1;
				}
			}
			break;
		case "up":
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY - 1][playerX])) {
				if (((Cell) background[playerY - 1][playerX]) instanceof Teleporter) {
					Teleporter temp = ((Teleporter) background[playerY - 1][playerX]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {

					if (board[playerY - 1][playerX] instanceof Collectible)
						acquire((Collectible) board[playerY - 1][playerX]);

					board[playerY - 1][playerX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playBoardSound(playerY,playerX+1);
					playBackSound(playerY,playerX+1);
					playerY = playerY - 1;
				}
			}
			break;
		case "down":
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY + 1][playerX])) {
				if (((Cell) background[playerY + 1][playerX]) instanceof Teleporter) {
					Teleporter temp = ((Teleporter) background[playerY + 1][playerX]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {
					if (board[playerY + 1][playerX] instanceof Collectible)
						acquire((Collectible) board[playerY + 1][playerX]);

					board[playerY + 1][playerX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playBoardSound(playerY,playerX+1);
					playBackSound(playerY,playerX+1);
					playerY = playerY + 1;
				}
			}
			if (playerX == goalX && playerY == goalY)

				break;
		}
		return end();
	}

	/**
	 * method for moving enemy
	 * 
	 */
	// TODO break this up into smaller methods, its disgusting
	private void moveEnemy() {
		// get each enemy

		for (int i = 0; i < enemyX.size(); i++) {
			// store enemy
			try {
				int currentX = enemyX.get(i);
				int currentY = enemyY.get(i);
				Enemy enemyHold = (Enemy) this.board[currentX][currentY];

				// find sub class of enemy
				switch (enemyHold.getString()) {
				case "DUMB":
					// TODO fill this in
					break;
				case "SMART":
					// TODO fill this in
					// check next move is next to wall
					if (enemyHold.isMovable(getNextCell(currentX, currentY, enemyHold.getMovDirection()))) {
						// check there is a wall
						if (checkWall(currentX, currentY, enemyHold.getMovDirection())) {
							// move to space if wall okay
							try {
								int[] XY = enemyHold.moveTo(currentX, currentY,
										this.getNextCell(currentX, currentY, enemyHold.getMovDirection()));
								board[currentY][currentX] = board[XY[1]][XY[0]];
								// if that didn't work reverse
							} catch (IndexOutOfBoundsException e) {
								int[] XY = enemyHold.moveTo(currentX, currentY,
										this.getNextCell(currentX, currentY, enemyHold.getMovDirection()));
								board[currentY][currentX] = board[XY[1]][XY[0]];
							}
						}
						// check corner if no wall
						if (this.checkCorner(currentX, currentY, enemyHold.getMovDirection())) {
							int[] XY = ((WallFollowingEnemy) enemyHold).moveToCorner(currentX, currentY,
									this.getNewWallDirection(currentX, currentY));
							board[currentY][currentX] = board[XY[1]][XY[0]];
						}

					}

					break;
				case "STRAIGHT":
					try {
						int[] XY = enemyHold.moveTo(currentX, currentY,
								this.getNextCell(currentX, currentY, enemyHold.getMovDirection()));
						board[currentY][currentX] = board[XY[1]][XY[0]];
					} catch (IndexOutOfBoundsException e) {
						int[] XY = enemyHold.moveTo(currentX, currentY,
								this.getNextCell(currentX, currentY, enemyHold.getMovDirection()));
						board[currentY][currentX] = board[XY[1]][XY[0]];
					}

					break;
				case "WALLHUG":
					// TODO fill this in
					break;
				}
			} catch (ClassCastException exc) {
				// not a enemy class
				exc.printStackTrace();
			}

		}
	}

	/**
	 * Returns the next cell based off movDirection of element
	 * 
	 * @param X            the X coordinates
	 * @param Y            the Y coordinates
	 * @param movDirection the move direction of the element
	 * @return the Cell next to given X Y
	 */
	private Cell getNextCell(int X, int Y, String movDirection) {
		switch (movDirection) {
		case (UP):
			// TODO make method to get element from board & background
			return (Cell) background[Y + ONE][X];
		case (DOWN):
			return (Cell) background[Y - ONE][X];
		case (LEFT):
			return (Cell) background[Y][X - ONE];
		case (RIGHT):
			return (Cell) background[Y][X + ONE];
		default:
			throw new IllegalStateException("Undifened direction");

		}

	}

	/**
	 * Checks that there is a wall next to the enemy TODO refactor this into wall
	 * following enemy doesn't need to be here
	 * 
	 * @param X            coordinate
	 * @param Y            coordinate
	 * @param movDirection the mov direction
	 * @return True if there is a wall at the next space else return false
	 */
	private boolean checkWall(int X, int Y, String movDirection) {
		switch (movDirection) {
		case (UP):
			if ((Cell) background[Y + ONE][X + ONE] instanceof Wall
					|| (Cell) background[Y + ONE][X - ONE] instanceof Wall) {
				return true;
			} else {
				return false;
			}
		case (DOWN):
			if ((Cell) background[Y - ONE][X + ONE] instanceof Wall
					|| (Cell) background[Y - ONE][X - ONE] instanceof Wall) {
				return true;
			} else {
				return false;
			}
		case (LEFT):
			if ((Cell) background[Y + ONE][X - ONE] instanceof Wall
					|| (Cell) background[Y - ONE][X - ONE] instanceof Wall) {
				return true;
			} else {
				return false;
			}
		case (RIGHT):
			if ((Cell) background[Y + ONE][X - ONE] instanceof Wall
					|| (Cell) background[Y - ONE][X - ONE] instanceof Wall) {
				return true;
			} else {
				return false;
			}
		default:
			throw new IllegalStateException("Undifened direction");

		}
	}

	private String getNewWallDirection(int X, int Y) {
		if ((Cell) background[Y][X + TWO] instanceof Wall) {
			return RIGHT;
		} else if ((Cell) background[Y][X - TWO] instanceof Wall) {
			return LEFT;
		} else if ((Cell) background[Y + TWO][X] instanceof Wall) {
			return UP;
		} else if ((Cell) background[Y - TWO][X] instanceof Wall) {
			return DOWN;
		}
		return "REVERSE";

	}

	private boolean checkCorner(int X, int Y, String movDirection) {
		switch (movDirection) {
		case (UP):
		case (DOWN):
			if ((Cell) background[Y][X + TWO] instanceof Wall || (Cell) background[Y][X - TWO] instanceof Wall) {
				return true;
			} else {
				return false;
			}
		case (LEFT):
		case (RIGHT):
			if ((Cell) background[Y + TWO][X] instanceof Wall || (Cell) background[Y - TWO][X] instanceof Wall) {
				return true;
			} else {
				return false;
			}
		default:
			throw new IllegalStateException("Undifened direction");

		}
	}

	public void acquire(Collectible co) {
		((Player) board[playerY][playerX]).acquireInventory(co.getIndex());
	}

	public Element[][] getBoard() {
		return this.board;
	}

	public Element[][] getBackground() {
		return this.background;
	}
}