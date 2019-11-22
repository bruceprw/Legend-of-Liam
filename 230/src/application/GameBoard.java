package application;

import java.io.FileNotFoundException;

public class GameBoard
{
	private Element[][] board;
	private Element[][] background;
	private int playerX;
	private int playerY;
	
	public GameBoard(String filePath) throws FileNotFoundException
	{
		FileReader lvl = new FileReader(filePath);
		board = lvl.getBoard();
		background = lvl.getBackground();
		playerX = lvl.getPlayerX();
		playerY = lvl.getPlayerY();
		
	}
	
	public Element[][] getMap()
	{
		return board;
	}
	
	
}