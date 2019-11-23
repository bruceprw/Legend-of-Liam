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
		this.board = lvl.getBoard();
		this.background = lvl.getBackground();
		playerX=lvl.getPlayerX();
		playerY=lvl.getPlayerY();
		
	}
	
	public void drawGame()
	{
		int zero = 0;
		int first = 100;
		int second = 200;
		int third = 300;
		int forth = 400;
		int fifth = 500;
		
		
		
	}
	
	public int getPlayerX()
	{
		return playerX;
	}
	public int getPlayerY()
	{
		return playerY;
	}
	
	public Element[][] getBoard()
	{
		return this.board;
	}
	
	public Element[][] getBackground()
	{
		return this.background;
	}
}