package application;

import java.io.FileNotFoundException;

import Collectibles.Collectibles;
import Collectibles.Token;
import cell.Cell;
import cell.Fog;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class GameBoard
{
	private Element[][] board;
	private Element[][] background;
	private Element[][] fog;
	private int playerX;
	private int playerY;

	public GameBoard(String filePath) throws FileNotFoundException
	{
		FileReader lvl = new FileReader(filePath);
		this.board = lvl.getBoard();
		this.background = lvl.getBackground();
		playerX = lvl.getPlayerX();
		playerY = lvl.getPlayerY();
		fog = lvl.getFog();
	}

	public void drawFog(GraphicsContext gc)
	{
		for (int y = playerY - 3, j = 0; y < playerY + 4; y++, j += 100)
		{
			for (int x = playerX - 3, i = 0; x < playerX + 4; x++, i += 100)
			{
				fog[y][x].draw(gc, i, j);
			}
		}
	}

	public void setFog()
	{
		for (int y = playerY - 2; y < playerY + 3; y++)
		{
			for (int x = playerX - 2; x < playerX + 3; x++)
			{
				fog[y][x] = new Empty();
			}
		}
	}

	public void drawGame(GraphicsContext gc) throws FileNotFoundException
	{

		for (int y = playerY - 2, j = 0; y < playerY + 3; y++, j += 100)
		{
			for (int x = playerX - 3, i = 0; x < playerX + 4; x++, i += 100)
			{
				background[y][x].draw(gc, i, j);
				board[y][x].draw(gc, i, j);
			}
		}
		// TimeUnit.SECONDS.sleep(2);
		setFog();
		
		int[] temp = ((Player)board[playerY][playerX]).getInventory();
		for(int i=0;i<7;i++)
		{
			System.out.print(temp[i]);
		}
		System.out.println();
		//drawItem(gc);
		//drawFog(gc);
	}

	public int getPlayerX()
	{
		return playerX;
	}

	public int getPlayerY()
	{
		return playerY;
	}
	
	public void drawItem(GraphicsContext gc) throws FileNotFoundException
	{
		int [] temp = ((Player)board[playerY][playerX]).getInventory();
		Token token = new Token();
		gc.drawImage(token.getImage(), 0, 500,100,100);
		gc.setFont(new Font("Arial",50));
		gc.strokeText(": "+temp[0], 100,560 );
	}

	public void move(String way)
	{
		switch (way)
		{
		case "right":
			if(((Cell) background[playerY][playerX+1]).getIsPlayerAllowed())
			{
				if (board[playerY][playerX+1] instanceof Collectibles)
					acquire((Collectibles)board[playerY][playerX+1]);
				
				board[playerY][playerX + 1] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerX = playerX + 1;
			}
			break;
		case "left":
			if(((Cell) background[playerY][playerX-1]).getIsPlayerAllowed())
			{
				if (board[playerY][playerX-1] instanceof Collectibles)
					acquire((Collectibles)board[playerY][playerX-1]);

				board[playerY][playerX - 1] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerX = playerX - 1;
			}
			break;
		case "up":
			if(((Cell) background[playerY-1][playerX]).getIsPlayerAllowed())
			{
				if (board[playerY-1][playerX] instanceof Collectibles)
					acquire((Collectibles)board[playerY-1][playerX]);

				board[playerY - 1][playerX] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerY = playerY - 1;
			}
			break;
		case "down":
			if(((Cell) background[playerY+1][playerX]).getIsPlayerAllowed())
			{
				if (board[playerY+1][playerX] instanceof Collectibles)
					acquire((Collectibles)board[playerY+1][playerX]);

				board[playerY + 1][playerX] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerY = playerY + 1;
			}
			break;
		}
	}
    
    public void acquire(Collectibles co)
    {
    	((Player)board[playerY][playerX]).acquireInventory(co.getIndex());
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