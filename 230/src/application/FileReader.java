package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Character.DumbTargettingEnemy;
import Character.Player;
import Character.SmartTargettingEnemy;
import Character.StraightLineEnemy;
import Character.WallFollowingEnemy;
import Collectibles.*;
import cell.*;

/**
 * Reads the level files to create the level described in the level file.
 * 
 * @author user
 *
 */

public class FileReader {
	private int mapSizeX;
	private int mapSizeY;
	private String[] map;
	private Element[][] board;
	private Element[][] background;
	private int playerX;
	private int playerY;
	private ArrayList<Integer> enemyX = new ArrayList<Integer>();
	private ArrayList<Integer> enemyY = new ArrayList<Integer>();
	private int goalX;
	private int goalY;
	private long time;

	/**
	 * Reads in the level info from the level files. Converts the information to
	 * different Class instance that works to each other.
	 * 
	 * @param filePath The file path of the level
	 * @throws FileNotFoundException
	 */
	public FileReader(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner in = new Scanner(file);
		String sizeLine = in.nextLine();
		Scanner sizeScan = new Scanner(sizeLine);
		sizeScan.useDelimiter(",");
		mapSizeX = sizeScan.nextInt();
		mapSizeY = sizeScan.nextInt();
		Scanner longScan = new Scanner(in.nextLine());
		time = longScan.nextLong();
		// System.out.println(time);
		longScan.close();
		board = new Element[mapSizeY][mapSizeX];
		background = new Element[mapSizeY][mapSizeX];
		map = new String[mapSizeY];
		for (int i = 0; i < mapSizeY; i++) {
			map[i] = in.nextLine();
		}

		substituteBackground(map);

		while (in.hasNext()) {
			String temp = in.nextLine();
			if (temp.equals("////"))
				break;
			Scanner addLine = new Scanner(temp);
			substituteBoard(addLine);
		}
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[y][x] == null)
					board[y][x] = new Empty();
			}
		}
		for (int y = 0; y < background.length; y++) {
			for (int x = 0; x < background[y].length; x++) {
				if (background[y][x] == null)
					background[y][x] = new Empty();
			}
		}
		sizeScan.close();
		in.close();
	}

	/**
	 * Gets the time passed so far in the game.
	 * 
	 * @return the time passed
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Gets the X coordinate of every enemy.
	 * 
	 * @return arrayList of x-coordinate.
	 */
	public ArrayList<Integer> getEnemyX() {
		return enemyX;
	}

	/**
	 * Gets the Y coordinate of every enemy.
	 * 
	 * @return arrayList of y-coordinate.
	 */
	public ArrayList<Integer> getEnemyY() {
		return enemyY;
	}

	/**
	 * Gets the player's X-coordinates.
	 * 
	 * @return player's X-coordinates.
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * Gets the player's y-coordinates.
	 * 
	 * @return player's y-coordinates.
	 */
	public int getPlayerY() {
		return playerY;
	}

	/**
	 * Gets the background element 2D array.
	 * 
	 * @return background element 2D array.
	 */
	public Element[][] getBackground() {
		return this.background;
	}

	/**
	 * Gets the board elements 2D array.
	 * 
	 * @return board elements 2D array.
	 */
	public Element[][] getBoard() {
		return this.board;
	}

	/**
	 * Read the level file and create objects on the game board based off this.
	 * 
	 * @param line The current line being read.
	 * @throws FileNotFoundException
	 */
	private void substituteBoard(Scanner line) throws FileNotFoundException {
		line.useDelimiter(",");
		int x = line.nextInt();
		int y = line.nextInt();

		String feature = line.next();
		switch (feature) {
		case "START":
			playerX = x;
			playerY = y;
			board[y][x] = new Player("Name");
			break;
		case "ENEMY":
			String type = line.next();
			if (type.equals("STRAIGHT")) {
				String way = line.next();
				board[y][x] = new StraightLineEnemy(x, y, way);
				enemyX.add(x);
				enemyY.add(y);
			} else if (type.equals("WALLHUG")) {
				String way = line.next();
				String hand = line.next();
				board[y][x] = new WallFollowingEnemy(x, y, way, hand);
				enemyX.add(x);
				enemyY.add(y);
			} else if (type.equals("DUMB")) {
				board[y][x] = new DumbTargettingEnemy(x, y);
				enemyX.add(x);
				enemyY.add(y);
			} else if (type.equals("SMART")) {
				board[y][x] = new SmartTargettingEnemy(x, y);
				enemyX.add(x);
				enemyY.add(y);
			}
			break;
		case "RKEY":
			board[y][x] = new RedKey();
			break;
		case "GKEY":
			board[y][x] = new GreenKey();
			break;
		case "BKEY":
			board[y][x] = new BlueKey();
			break;
		case "YKEY":
			board[y][x] = new YellowKey();
			break;
		case "REDDOOR":
			background[y][x] = new ColouredDoor("REDDOOR");
			break;
		case "GREENDOOR":
			background[y][x] = new ColouredDoor("GREENDOOR");
			break;
		case "BLUEDOOR":
			background[y][x] = new ColouredDoor("BLUEDOOR");
			break;
		case "YELLOWDOOR":
			background[y][x] = new ColouredDoor("YELLOWDOOR");
			break;
		case "DOOR":
			int a = line.nextInt();
			background[y][x] = new TokenDoor(a);
			break;
		case "LEVELDOOR":
			int levelNo = line.nextInt();
			background[y][x] = new LevelDoor(levelNo);
			break;
		case "TELEPORTER":
			int tempX = line.nextInt();
			int tempY = line.nextInt();
			background[y][x] = new Teleporter(tempX, tempY);
			break;
		case "INVENTORY":
			while (line.hasNext()) {
				String collecti = line.next();
				int num = line.nextInt();
				// System.out.println(playerY);
				// System.out.println(playerX);
				switch (collecti) {
				case "TOKEN":
					((Player) board[playerY][playerX]).setInventory(0, num);
					break;
				case "RKEY":
					((Player) board[playerY][playerX]).setInventory(1, num);
				case "GKEY":
					((Player) board[playerY][playerX]).setInventory(2, num);
					break;
				case "BKEY":
					((Player) board[playerY][playerX]).setInventory(3, num);
					break;
				case "YKEY":
					((Player) board[playerY][playerX]).setInventory(4, num);
					break;
				case "BOOT":
					((Player) board[playerY][playerX]).setInventory(5, num);
					break;
				case "FLIPPER":
					((Player) board[playerY][playerX]).setInventory(6, num);
					break;

				}
			}
			break;
		}
	}

	/**
	 * Get the goal X-coordinate.
	 * 
	 * @return goal X-coordinate.
	 */
	public int getGoalX() {
		return goalX;
	}

	/**
	 * Get the goal Y-coordinate.
	 * 
	 * @return goal's Y-coordinate.
	 */
	public int getGoalY() {
		return goalY;
	}

	/**
	 * Used to create class on the background array.
	 * 
	 * @param temp the information of the map in String array.
	 * @throws FileNotFoundException.
	 */
	private void substituteBackground(String[] temp) throws FileNotFoundException {

		for (int j = 0; j < temp.length; j++) {
			for (int i = 0; i < temp[j].length(); i++) {
				char temp1 = temp[j].charAt(i);
				switch (temp1) {
				case '#':
					background[j][i] = new Wall();
					break;
				case ' ':
					background[j][i] = new Ground();
					break;
				case 'G':
					this.goalX = i;
					this.goalY = j;
					background[j][i] = new Goal();
					break;
				case 'L':
					background[j][i] = new Lava();
					break;
				case 'W':
					background[j][i] = new Water();
					break;
				/*
				 * case '@': background[j][i] = new Teleporter(); break;
				 */
				case 'T':
					background[j][i] = new Ground();
					board[j][i] = new Token();
					break;
				case 'O':
					background[j][i] = new Ground();
					board[j][i] = new FireBoot();
					break;
				case 'F':
					background[j][i] = new Ground();
					board[j][i] = new Flipper();
					break;
				}
			}
		}
	}
}
