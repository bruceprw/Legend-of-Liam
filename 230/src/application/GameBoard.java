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
import javafx.scene.Scene;
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

	final private String UP = "UP";
	final private String LEFT = "LEFT";
	final private String DOWN = "DOWN";
	final private String RIGHT = "RIGHT";
	private long time;
	final private int ONE = 1;
	final private int TWO = 2;

	public GameBoard(String filePath) throws FileNotFoundException {
		FileReader lvl = new FileReader(filePath);
		time = lvl.getTime();
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

	public long getTime() {
		return time;
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
		/*
		 * for (int i = 0; i < 7; i++) { System.out.print(temp[i]); }
		 */
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
	// Moves player Side to side
	/*
	 * public void moveHorizontal(int x){
	 * 
	 * if (((Player) board[playerY][playerX]).movable((Cell)
	 * background[playerY][playerX +x])) { if (((Cell) background[playerY][playerX
	 * +x]) instanceof Teleporter) { Teleporter temp = ((Teleporter)
	 * background[playerY][playerX +x]); int tempX = temp.getPairX(); int tempY =
	 * temp.getPairY(); board[tempY][tempX] = board[playerY][playerX];
	 * board[playerY][playerX] = new Empty(); playerY = tempY; playerX = tempX; }
	 * else { if (board[playerY][playerX +x] instanceof Collectible)
	 * acquire((Collectible) board[playerY][playerX +x]); board[playerY][playerX +x]
	 * = board[playerY][playerX]; board[playerY][playerX] = new Empty(); playerX +=
	 * x; System.out.println(playerX+x); }
	 * 
	 * } }
	 */
	// Moves player up and down
	/*
	 * public void moveVertical(int y){ if (((Player)
	 * board[playerY][playerX]).movable((Cell) background[playerY + y][playerX])) {
	 * if (((Cell) background[playerY + y][playerX]) instanceof Teleporter) {
	 * Teleporter temp = ((Teleporter) background[playerY + y][playerX]); int tempX
	 * = temp.getPairX(); int tempY = temp.getPairY(); board[tempY][tempX] =
	 * board[playerY][playerX]; board[playerY][playerX] = new Empty(); playerY =
	 * tempY; playerX = tempX; } else {
	 * 
	 * if (board[playerY + y][playerX] instanceof Collectible) acquire((Collectible)
	 * board[playerY + y][playerX]);
	 * 
	 * board[playerY + y][playerX] = board[playerY][playerX];
	 * board[playerY][playerX] = new Empty(); int temp = playerY; temp += y; playerY
	 * = temp; } } }
	 */

	public void playBoardSound(int x, int y) {
		board[y][x].playSound();
	}

	public void playBackSound(int x, int y) {
		background[y][x].playSound();
	}

	public int move(String way) {
		// moveEnemy();
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
					playerX += 1;
					// System.out.println(playerX);
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
					playerX = playerX - 1;
					// System.out.println(playerX);
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
					playerY = playerY - 1;
					// System.out.println(playerY);
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
					playerY = playerY + 1;
					// System.out.println(playerY);
				}
			}
			break;

		}
		moveEnemy();
		if (playerDead())
			return 2;
		if (end())
			return 1;
		return 0;
	}

	public boolean playerDead() {
		for (int i = 0; i < enemyX.size(); i++) {
			if (playerX == enemyX.get(i) && playerY == enemyY.get(i)) {
				return true;
			}
		}
		return false;
	}

	// TODO ERROR OCCURS WHEN ENEMY IS TO MOVE ONTO PLAYER DOESNT KNOW WHAT TO DO
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
				int currentEnemyX = enemyX.get(i);
				int currentEnemyY = enemyY.get(i);
				
				int newEnemyX;
				int newEnemyY;
				
				Enemy enemyHold = (Enemy) this.board[currentEnemyY][currentEnemyX];

				// find sub class of enemy
				switch (enemyHold.getString()) {
				case "DUMB":
					DumbTargettingEnemy dumbEnemy = (DumbTargettingEnemy) enemyHold;
					newEnemyX = dumbEnemy.moveTowardsPlayerOnX(currentEnemyX, playerX);
					newEnemyY = dumbEnemy.moveTowardsPlayerOnY(currentEnemyY, playerY);

					if (!dumbEnemy.isMovable(this.getCell(newEnemyX, newEnemyY))) {
						if (!dumbEnemy.isMovable(this.getCell(newEnemyX, currentEnemyY))) {
							newEnemyX = currentEnemyX;
						}
						if (!dumbEnemy.isMovable(this.getCell(currentEnemyX, newEnemyY))) {
							newEnemyY = currentEnemyY;
						}
					}

					//this needs to be a method
					board[newEnemyY][newEnemyX] = board[currentEnemyY][currentEnemyX];

					enemyX.set(i, newEnemyX);
					enemyY.set(i, newEnemyY);
					
					break;

				case "SMART":
					
					SmartTargettingEnemy smartEnemy = (SmartTargettingEnemy)enemyHold;
					Node node = smartEnemy.findPath(this.getBackground(),currentEnemyX,currentEnemyY,playerX,playerY);
					if(node!=null)
						System.out.println("next Node X & Y: " + node.toString());
					else System.out.println("Node is null fix me");
					newEnemyX = node.getX();
					newEnemyY = node.getY();
					
					board[newEnemyY][newEnemyX] = board[currentEnemyY][currentEnemyX];

					enemyX.set(i, newEnemyX);
					enemyY.set(i, newEnemyY);
					
					System.out.println("New smart position: " +newEnemyX+" "+newEnemyY);
					
					
					break;
				case "STRAIGHT":
					try {
						int[] XY = enemyHold.moveTo(currentEnemyX, currentEnemyY,
								this.getNextCell(currentEnemyX, currentEnemyY, enemyHold.getMovDirection()));
						board[XY[1]][XY[0]] = board[currentEnemyY][currentEnemyX];

						enemyX.set(i, XY[0]);
						enemyY.set(i, XY[1]);
					} catch (IndexOutOfBoundsException e) {
						int[] XY = enemyHold.moveTo(currentEnemyX, currentEnemyY,
								this.getNextCell(currentEnemyX, currentEnemyY, enemyHold.getMovDirection()));
						board[XY[1]][XY[0]] = board[currentEnemyY][currentEnemyX];

						enemyX.set(i, XY[0]);
						enemyY.set(i, XY[1]);
					}
					break;

					
				case "WALLHUG":
					if (enemyHold.isMovable(getNextCell(currentEnemyX, currentEnemyY, enemyHold.getMovDirection()))) {
						// check there is a wall
						if (checkWall(currentEnemyX, currentEnemyY, enemyHold.getMovDirection())) {
							// move to space if wall okay
							try {
								int[] XY = enemyHold.moveTo(currentEnemyX, currentEnemyY,
										this.getNextCell(currentEnemyX, currentEnemyY, enemyHold.getMovDirection()));
								board[XY[1]][XY[0]] = board[currentEnemyY][currentEnemyX];

								enemyX.set(i, XY[0]);
								enemyY.set(i, XY[1]);

								// if that didn't work reverse
							} catch (IndexOutOfBoundsException e) {
								int[] XY = enemyHold.moveTo(currentEnemyX, currentEnemyY,
										this.getNextCell(currentEnemyX, currentEnemyY, enemyHold.getMovDirection()));
								board[XY[1]][XY[0]] = board[currentEnemyY][currentEnemyX];

								enemyX.set(i, XY[0]);
								enemyY.set(i, XY[1]);
							}
						}
						// check corner if no wall
						if (this.checkCorner(currentEnemyX, currentEnemyY, enemyHold.getMovDirection())) {
							int[] XY = ((WallFollowingEnemy) enemyHold).moveToCorner(currentEnemyX, currentEnemyY,
									this.getNewWallDirection(currentEnemyX, currentEnemyY));
							board[XY[1]][XY[0]] = board[currentEnemyY][currentEnemyX];

							enemyX.set(i, XY[0]);
							enemyY.set(i, XY[1]);
						}

					}
					break;
				}
			} catch (ClassCastException exc) {
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

	private Element getBoardElement(int X, int Y) {
		return board[Y][X];
	}

	public Cell getCell(int X, int Y) {
		return (Cell) background[Y][X];
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