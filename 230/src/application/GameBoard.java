package application;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;

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
	
	public void drawGame(GraphicsContext gc) throws FileNotFoundException
	{
		for(int y = playerY-3,j=0;y<playerY+3;y++,j+=100)
		{
			for(int x = playerX-3, i=0;x<playerX+3;x++,i+=100)
			{
				background[y][x].draw(gc,i,j);
				board[y][x].draw(gc, i, j);
			}
		}
	}
	
	public int getPlayerX()
	{
		return playerX;
	}
	public int getPlayerY()
	{
		return playerY;
	}
	
	public void move(String way)
	{
		switch (way)
		{
		case "right":
			board[playerY][playerX+1]=board[playerY][playerX];
			board[playerY][playerX]=new Empty();
			playerX=playerX+1;
			break;
		case "left":
			
			board[playerY][playerX-1]=board[playerY][playerX];
			board[playerY][playerX]=new Empty();
			playerX =playerX-1;
			break;
		case "up":
			board[playerY-1][playerX]=board[playerY][playerX];
			board[playerY][playerX]=new Empty();
			playerY = playerY-1;
			break;
		case "down":
			board[playerY+1][playerX]=board[playerY][playerX];
			board[playerY][playerX]=new Empty();
			playerY=playerY+1;
			break;
		}
		
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