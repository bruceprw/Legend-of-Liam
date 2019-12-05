package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Character.DumbTargettingEnemy;
import Character.Enemy;
import Character.Path;
import Character.Player;
import Character.SmartTargettingEnemy;
import Character.StraightLineEnemy;
import Character.WallFollowingEnemy;
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Stores the game board instance and moves the player and enemy.
 * 
 * @author user
 *
 */
public class GameBoard {
	private Element[][] board;
	private Element[][] background;
	private int playerX;
	private int playerY;
	private int goalX;
	private int goalY;
	private int rotation;

	private Media playerDie = new Media(new File("Sound\\player_killed.wav").toURI().toString());

	private MediaPlayer mediaPlayer;

	private ArrayList<Integer> enemyX;
	private ArrayList<Integer> enemyY;

	private long time;

	/**
	 * Reads in info from level file and set the important information.
	 * 
	 * @param filePath the name of the level file you wanted to retrieve.
	 * @throws FileNotFoundException
	 */
	public GameBoard(String filePath) throws FileNotFoundException {
		FileReader lvl = new FileReader(filePath);
		time = lvl.getTime();
		this.board = lvl.getBoard();
		this.background = lvl.getBackground();
		playerX = lvl.getPlayerX();
		playerY = lvl.getPlayerY();
		enemyX = lvl.getEnemyX();
		enemyY = lvl.getEnemyY();
		goalX = lvl.getGoalX();
		goalY = lvl.getGoalY();
	}

	/**
	 * Gets how much time has passed in the level.
	 * 
	 * @return the time passed.
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Draws the maps and the board elements. Also the items held by player.
	 * 
	 * @param gc the canvas graphics context
	 * @throws FileNotFoundException
	 */
	public void drawGame(GraphicsContext gc) throws FileNotFoundException {

		for (int y = playerY - 2, j = 0; y < playerY + 3; y++, j += 100) {
			for (int x = playerX - 3, i = 0; x < playerX + 4; x++, i += 100) {
				background[y][x].draw(gc, i, j);
				board[y][x].draw(gc, i, j);
				background[y][x].drawPlayer(gc, i, j, rotation);
				board[y][x].drawPlayer(gc, i, j, rotation);
			}
		}
		drawItem(gc);
	}

	/**
	 * Get the player's x-coordinate.
	 * 
	 * @return player's x-coordinate
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * Get player's y-coordinate.
	 * 
	 * @return player's y-coordinate
	 */
	public int getPlayerY() {
		return playerY;
	}

	/**
	 * Checks whether the player got to the goal
	 * 
	 * @return true if player got to the goal.
	 */
	public boolean end() {
		return playerY == goalY && playerX == goalX;
	}

	/**
	 * Draws the items on the board.
	 * 
	 * @param gc canvas's graphics context.
	 * @throws FileNotFoundException
	 */
	public void drawItem(GraphicsContext gc) throws FileNotFoundException {
		int[] temp = ((Player) board[playerY][playerX]).getInventory();
		Token token = new Token();
		RedKey r = new RedKey();
		GreenKey g = new GreenKey();
		BlueKey b = new BlueKey();
		YellowKey y = new YellowKey();
		FireBoot f = new FireBoot();
		Flipper fl = new Flipper();
		gc.setFill(Color.MEDIUMSEAGREEN);
		gc.fillRect(0, 510, 700, 200);
		gc.setFill(Color.GOLD);
		gc.drawImage(token.getImage(), 0, 610, 75, 75);
		gc.drawImage(r.getImage(), 0, 510, 75, 75);
		gc.drawImage(g.getImage(), 150, 510, 75, 75);
		gc.drawImage(b.getImage(), 300, 510, 75, 75);
		gc.drawImage(y.getImage(), 450, 510, 75, 75);
		gc.drawImage(f.getImage(), 150, 610, 75, 75);
		gc.drawImage(fl.getImage(), 300, 610, 75, 75);
		gc.setFont(new Font("Arial", 50));
		gc.fillText(": " + temp[0], 75, 670);
		gc.fillText(": " + temp[1], 75, 570);
		gc.fillText(": " + temp[2], 225, 570);
		gc.fillText(": " + temp[3], 375, 570);
		gc.fillText(": " + temp[4], 525, 570);
		gc.fillText(": " + temp[5], 225, 670);
		gc.fillText(": " + temp[6], 375, 670);

	}

	/**
	 * Plays the sound of board element that player steps on.
	 * 
	 * @param x the next step that player steps on
	 * @param y the next step that player steps on
	 */
	public void playBoardSound(int x, int y) {
		board[y][x].playSound();
	}

	/**
	 * Plays the sound of background element that player steps on.
	 * 
	 * @param x the next step that player steps on
	 * @param y the next step that player steps on
	 */
	public void playBackSound(int x, int y) {
		background[y][x].playSound();
	}

	/**
	 * Checks whether the player touches the enemy.
	 * 
	 * @param x the x-coordinate that player about to step on.
	 * @param y the y-coordinate that player about to step on.
	 * @return true if the position is an instance of an enemy.
	 */
	public boolean touchEnemy(int x, int y) {
		return board[y][x] instanceof Enemy;
	}

	// TODO this needs to be broken into several methods it far too big,
	/**
	 * Moves the player and the enemy. Also plays sound when player steps on an
	 * enemy Checks whether the player is dead or not.
	 * 
	 * @param way Where the player wants to move to.
	 * @return 0 if nothing happens
	 * @return 1 if player got to the goal.
	 * @return 2 if the player is dead.
	 */
	public int move(String way) {
		// moveEnemy();
		switch (way) {
		case "right":
			mediaPlayer = ((Cell) background[playerY][playerX + 1]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX + 1])) {
				if (touchEnemy(playerX + 1, playerY)) {
					return 2;
				}
				if (((Cell) background[playerY][playerX + 1]) instanceof Teleporter) {
					rotation = 90;
					Teleporter temp = ((Teleporter) background[playerY][playerX + 1]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {
					if (board[playerY][playerX + 1] instanceof Collectible) {
						playBoardSound(playerX + 1, playerY);
						// board[playerY][playerX + 1].playSound();
						acquire((Collectible) board[playerY][playerX + 1]);
					}
					rotation = 90;
					board[playerY][playerX + 1] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerX += 1;

					// System.out.println(playerX);
				}

			}
			break;
		case "left":
			mediaPlayer = ((Cell) background[playerY][playerX - 1]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX - 1])) {
				if (touchEnemy(playerX - 1, playerY)) {
					return 2;
				}

				if (((Cell) background[playerY][playerX - 1]) instanceof Teleporter) {
					rotation = 270;
					Teleporter temp = ((Teleporter) background[playerY][playerX - 1]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {
					if (board[playerY][playerX - 1] instanceof Collectible) {
						playBoardSound(playerX - 1, playerY);
						acquire((Collectible) board[playerY][playerX - 1]);
						board[playerY][playerX - 1].playSound();
					}
					rotation = 270;
					board[playerY][playerX - 1] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerX = playerX - 1;
				}

			}
			break;
		case "up":
			mediaPlayer = ((Cell) background[playerY - 1][playerX]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY - 1][playerX])) {
				if (touchEnemy(playerX, playerY - 1)) {
					return 2;
				}

				if (((Cell) background[playerY - 1][playerX]) instanceof Teleporter) {
					rotation = 0;
					Teleporter temp = ((Teleporter) background[playerY - 1][playerX]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {

					if (board[playerY - 1][playerX] instanceof Collectible) {
						playBoardSound(playerX, playerY - 1);
						board[playerY][playerX + 1].playSound();
						acquire((Collectible) board[playerY - 1][playerX]);
					}
					rotation = 0;
					board[playerY - 1][playerX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = playerY - 1;
				}
			}
			break;
		case "down":
			mediaPlayer = ((Cell) background[playerY + 1][playerX]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY + 1][playerX])) {
				if (touchEnemy(playerX, playerY + 1)) {
					return 2;
				}

				if (((Cell) background[playerY + 1][playerX]) instanceof Teleporter) {
					rotation = 180;
					Teleporter temp = ((Teleporter) background[playerY + 1][playerX]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				} else {

					if (board[playerY + 1][playerX] instanceof Collectible) {
						playBoardSound(playerX, playerY + 1);
						board[playerY][playerX + 1].playSound();
						acquire((Collectible) board[playerY + 1][playerX]);
					}
					rotation = 180;
					board[playerY + 1][playerX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = playerY + 1;
				}
			}
			break;
		}
		moveEnemy();
		if (playerDead() || checkPlayerDead()) {
			mediaPlayer = new MediaPlayer(playerDie);
			mediaPlayer.play();
			// mediaPlayer.stop();
			return 2;
		}
		if (end())
			return 1;
		return 0;
	}

	
	/**
	 * 
	 */
	public void moveUP()
	{
		
	}
	
	public void moveDown()
	{
		
	}
	
	public void moveLeft()
	{
		
	}
	
	public void moveRight()
	{
		
	}
	
	
	/**
	 * Checks if the next position the enemy will move to is the player.
	 * 
	 * @return true if the next step is player and therefore the player is dead.
	 */
	private boolean checkPlayerDead() {
		return !(board[playerY][playerX] instanceof Player);
	}

	/**
	 * Checks if the player is about to move onto a tile with a enemy.
	 * 
	 * @return true if player walks towards enemy.
	 */
	private boolean playerDead() {
		for (int i = 0; i < enemyX.size(); i++) {
			if (playerX == enemyX.get(i) && playerY == enemyY.get(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves every enemy on the game board.
	 */
	private void moveEnemy() {
		for (int i = 0; i < enemyX.size(); i++) {
			switch (board[enemyY.get(i)][enemyX.get(i)].toString()) {
			case "STRAIGHT":
				StraightLineEnemy a = (StraightLineEnemy) board[enemyY.get(i)][enemyX.get(i)];
				boolean hori = a.horizontalNoMove(this);
				boolean lOR = a.getMovDirection().equals("LEFT") || a.getMovDirection().equals("RIGHT");
				boolean verti = a.verticalNoMove(this);
				boolean UD = a.getMovDirection().equals("UP") || a.getMovDirection().equals("DOWN");
				if ((hori && lOR) || (verti && UD)) {

				} else {
					int newX = a.getX(this, enemyX.get(i), enemyY.get(i));
					int newY = a.getY(this, enemyX.get(i), enemyY.get(i));
					board[newY][newX] = board[enemyY.get(i)][enemyX.get(i)];
					board[enemyY.get(i)][enemyX.get(i)] = new Empty();
					enemyY.set(i, newY);
					enemyX.set(i, newX);
					// System.out.print(enemyX.get(i));
					// System.out.print(","+enemyY.get(i));
					// System.out.println(",straight");
				}
				break;
			case "WALLHUG":
				WallFollowingEnemy b = (WallFollowingEnemy) board[enemyY.get(i)][enemyX.get(i)];
				int newX = b.getsNewX(this, enemyX.get(i), enemyY.get(i));
				int newY = b.getNewY(this, enemyX.get(i), enemyY.get(i));
				// System.out.println(newY);
				board[newY][newX] = board[enemyY.get(i)][enemyX.get(i)];
				board[enemyY.get(i)][enemyX.get(i)] = new Empty();
				enemyY.set(i, newY);
				enemyX.set(i, newX);
				// System.out.println(Enemy.checkMove(this,22,21));

				break;
			case "DUMB":
				DumbTargettingEnemy c = (DumbTargettingEnemy) board[enemyY.get(i)][enemyX.get(i)];
				int newXa = c.getNewX(this, playerX, playerY, enemyX.get(i), enemyY.get(i));
				// System.out.print(newXa+",");
				int newYa = c.getNewY(this, playerX, playerY, enemyX.get(i), enemyY.get(i));
				// System.out.println(newYa);
				if (newXa == enemyX.get(i) && newYa == enemyY.get(i)) {

				} else {
					board[newYa][newXa] = board[enemyY.get(i)][enemyX.get(i)];
					board[enemyY.get(i)][enemyX.get(i)] = new Empty();
					enemyY.set(i, newYa);
					enemyX.set(i, newXa);
				}
				break;
			case "SMART":
				SmartTargettingEnemy d = (SmartTargettingEnemy) board[enemyY.get(i)][enemyX.get(i)];
				Path e = d.getPath(this, enemyX.get(i), enemyY.get(i), playerX, playerY);

				int newXe = e.getX();
				int newYe = e.getY();

				// Checks whether there are path that goes to player as if there are no path the
				// enemy won't move.
				if (newXe == enemyX.get(i) && newYe == enemyY.get(i)) {

				} else {
					board[newYe][newXe] = board[enemyY.get(i)][enemyX.get(i)];
					board[enemyY.get(i)][enemyX.get(i)] = new Empty();
					enemyY.set(i, newYe);
					enemyX.set(i, newXe);
				}
				break;
			}
		}
	}

	/**
	 * Get the cell at X,Y coordinate to retrieve other info.
	 * 
	 * @param X X-coordinate
	 * @param Y Y-coordinate
	 * @return the cell from the background
	 */
	public Cell getCell(int X, int Y) {
		return (Cell) background[Y][X];
	}

	/**
	 * Allow the player to get collectible.
	 * 
	 * @param co the collectible
	 */
	public void acquire(Collectible co) {
		((Player) board[playerY][playerX]).acquireInventory(co.getIndex());
	}

	/**
	 * Get the game board
	 * 
	 * @return the board.
	 */
	public Element[][] getBoard() {
		return this.board;
	}

	/**
	 * Get the background.
	 * 
	 * @return the background.
	 */
	public Element[][] getBackground() {
		return this.background;
	}
}