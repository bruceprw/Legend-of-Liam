package application;

import java.io.FileNotFoundException;

public class GameBoard
{
	private Element[][] board;
	private Element[][] background;
	
	public GameBoard(String filePath) throws FileNotFoundException
	{
		FileReader lvl = new FileReader(filePath);
		board = lvl.getBoard();
		background = lvl.getBackground();
		
	}
	
	public Elements[][] getMap()
	{
		return board;
	}
	
	
}