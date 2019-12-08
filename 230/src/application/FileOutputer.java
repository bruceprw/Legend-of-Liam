package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Character.Player;
import Character.StraightLineEnemy;
import Character.WallFollowingEnemy;
import cell.*;

/**
 * This class is used to output the save files for the game.
 * 
 * @author Andy Kuo
 * @version 1.0
 */
public class FileOutputer {
	private String output = "";
	private String temp = "";
	private Element[][] background;
	private Element[][] board;

	private final int TOKEN_POSTION = 0;
	private final int RKEY_POSTION = 1;
	private final int GKEY_POSTION = 2;
	private final int BKEY_POSTION = 3;
	private final int YKEY_POSTION = 4;
	private final int BOOT_POSTION = 5;
	private final int FLIPPER_POSTION = 6;

	/**
	 * Outputs the file with the game board.
	 * 
	 * @param lvl the game board.
	 * @param lt  the current level time.
	 */
	public FileOutputer(GameBoard lvl, LevelTime lt) {
		background = lvl.getBackground();
		board = lvl.getBoard();
		output += "" + background[0].length + "," + background.length + "\r\n";
		long a = lt.getTime();
		output += "" + a + "\r\n";

		setOutput(board, background);

		setRemain(lvl, board);

		// attempts to read in the file

		try {
			File outputFile = new File("LevelFiles\\" + lt.getUsername() + ".txt");
			PrintWriter out = new PrintWriter(outputFile);
			out.print(output + temp);
			out.close();

			// if it does not work it prints the
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This methods saves the inventory information of the game.
	 * 
	 * @param lvl   the GameBoard instance.
	 * @param board the board instance.
	 */
	public void setRemain(GameBoard lvl, Element[][] board) {
		Player player = ((Player) board[lvl.getPlayerY()][lvl.getPlayerX()]);
		int[] inventory = player.getInventory();
		temp += "0,0,INVENTORY,";
		temp += "TOKEN," + inventory[TOKEN_POSTION] + ",";
		temp += "RKEY," + inventory[RKEY_POSTION] + ",";
		temp += "GKEY," + inventory[GKEY_POSTION] + ",";
		temp += "BKEY," + inventory[BKEY_POSTION] + ",";
		temp += "YKEY," + inventory[YKEY_POSTION] + ",";
		temp += "BOOT," + inventory[BOOT_POSTION] + ",";
		temp += "FLIPPER," + inventory[FLIPPER_POSTION] + "\r\n";
		temp += "////";
	}

	/**
	 * This method sets part of the output.
	 * 
	 * @param board      the current board instance.
	 * @param background the current background.
	 */
	public void setOutput(Element[][] board, Element[][] background) {
		getSwitch(board, background);
	}

	/**
	 * This method get the current board to save as a file. Does not get all
	 * information required.
	 * 
	 * @param board      the current board instance.
	 * @param background the current background.
	 */
	public void setTemp(Element[][] board, Element[][] background) {
		getSwitch(board, background);
	}

	/**
	 * Turns the current board and background into a string that can be used to
	 * restore the current game.
	 * 
	 * @param board      board instance.
	 * @param background background instance.
	 */

	public void getSwitch(Element[][] board, Element[][] background) {
		for (int y = 0; y < board.length; y++) {
			// Gets board information
			for (int x = 0; x < board[y].length; x++) {
				switch (board[y][x].getString()) {
				case "T":
					background[y][x] = board[y][x];
					break;
				case "RKEY":
					temp += "" + x + "," + y + ",RKEY" + "\r\n";
					break;
				case "GKEY":
					temp += "" + x + "," + y + ",GKEY" + "\r\n";
					break;
				case "BKEY":
					temp += "" + x + "," + y + ",BKEY" + "\r\n";
					break;
				case "YKEY":
					temp += "" + x + "," + y + ",YKEY" + "\r\n";
					break;
				case "F":
					background[y][x] = board[y][x];
					break;
				case "O":
					background[y][x] = board[y][x];
					break;
				case "START":
					temp += "" + x + "," + y + ",START" + "\r\n";
					break;
				case "STRAIGHT":
					temp += "" + x + "," + y + ",ENEMY,STRAIGHT," + ((StraightLineEnemy) board[y][x]).getMovDirection()
							+ "\r\n";
					break;
				case "WALLHUG":
					temp += "" + x + "," + y + ",ENEMY,WALLHUG," + ((WallFollowingEnemy) board[y][x]).getMovDirection()
							+ "," + ((WallFollowingEnemy) board[y][x]).getHand() + "\r\n";
					break;
				case "DUMB":
					temp += "" + x + "," + y + ",ENEMY,DUMB" + "\r\n";
					break;
				case "SMART":
					temp += "" + x + "," + y + ",ENEMY,SMART" + "\r\n";
					break;
				case "D":
					break;
				}
			}
		}
		// gets background information
		for (int y = 0; y < background.length; y++) {
			for (int x = 0; x < background[y].length; x++) {
				switch (background[y][x].getString()) {
				case "#":
					output += "#";
					break;
				case " ":
					output += " ";
					break;
				case "G":
					output += "G";
					break;
				case "L":
					output += "L";
					break;
				case "W":
					output += "W";
					break;
				case "@":
					output += " ";
					int tempX = ((Teleporter) background[y][x]).getPairX();
					int tempY = ((Teleporter) background[y][x]).getPairY();
					temp += "" + x + "," + y + ",TELEPORTER," + tempX + "," + tempY + "\r\n";
					break;
				case "D":
					output += " ";
					temp += "" + x + "," + y + ",DOOR," + ((TokenDoor) background[y][x]).getTokenNum() + "\r\n";
					break;
				case "REDDOOR":
					output += " ";
					temp += "" + x + "," + y + ",REDDOOR\r\n";
					break;
				case "GREENDOOR":
					output += " ";
					temp += "" + x + "," + y + ",GREENDOOR\r\n";
					break;
				case "BLUEDOOR":
					output += " ";
					temp += "" + x + "," + y + ",BLUEDOOR\r\n";
					break;
				case "YELLOWDOOR":
					output += " ";
					temp += "" + x + "," + y + ",YELLOWDOOR\r\n";
					break;
				case "T":
					output += "T";
					break;
				case "F":
					output += "F";
					break;
				case "O":
					output += "O";
					break;
				}
			}
			output += "\r\n";
		}
	}
}
