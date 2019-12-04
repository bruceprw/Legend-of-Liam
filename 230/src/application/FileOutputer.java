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

	/**
	 * Outputs the file with the game board
	 * 
	 * @param lvl the game board
	 * @param lt  the current level time
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

		String finalOutput = output + temp;
		System.out.println(finalOutput);
	}

	/**
	 * This methods saves the inventory information of the game.
	 * 
	 * @param lvl   the GameBoard instance
	 * @param board the board instance
	 */
	public void setRemain(GameBoard lvl, Element[][] board) {
		int[] inventory = ((Player) board[lvl.getPlayerY()][lvl.getPlayerX()]).getInventory();
		temp += "0,0,INVENTORY,";
		temp += "TOKEN," + inventory[0] + ",";
		temp += "RKEY," + inventory[1] + ",";
		temp += "GKEY," + inventory[2] + ",";
		temp += "BKEY," + inventory[3] + ",";
		temp += "YKEY," + inventory[4] + ",";
		temp += "BOOT," + inventory[5] + ",";
		temp += "FLIPPER," + inventory[6] + "\r\n";
		temp += "////";
	}

	/**
	 * This method sets part of the output.
	 * 
	 * @param board      the current board instance
	 * @param background the current background
	 */
	public void setOutput(Element[][] board, Element[][] background) {
		getSwitch(board, background);
	}

	/**
	 * This method get the current board to save as a file. Does not get all
	 * information required.
	 * 
	 * @param board      the current board instance
	 * @param background the current background
	 */
	public void setTemp(Element[][] board, Element[][] background) {
		getSwitch(board, background);
	}

	/**
	 * Turns the current board and background into a string that can be used to
	 * restore the current game.
	 * 
	 * @param board      board instance
	 * @param background background instance
	 */

	public void getSwitch(Element[][] board, Element[][] background) {
		for (int y = 0; y < board.length; y++) {
			//Gets board information
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
					temp += "" + x + "," + y + ",ENEMY,STRAIGHT," + ((StraightLineEnemy) board[y][x]).getDirection()
							+ "\r\n";
					break;
				case "WALLHUG":
					temp += "" + x + "," + y + ",ENEMY,WALLHUG," + ((WallFollowingEnemy) board[y][x]).getDirection()
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
		//gets background information
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
