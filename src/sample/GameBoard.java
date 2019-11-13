package application;

import java.io.FileNotFoundException;

public class GameBoard
{
	private Elements[][] board;
	
	public GameBoard(String filePath) throws FileNotFoundException
	{
		FileReader lvl = new FileReader();
		board = lvl.read(filePath);
	}
	
	public Elements[][] getMap()
	{
		return board;
	}
	
	
}