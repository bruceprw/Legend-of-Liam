package application;

import java.io.FileNotFoundException;

import Collectibles.BlueKey;
import Collectibles.Collectible;
import Collectibles.FireBoot;
import Collectibles.Flipper;
import Collectibles.GreenKey;
import Collectibles.RedKey;
import Collectibles.Token;
import Collectibles.YellowKey;
import cell.Cell;
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

		int[] temp = ((Player) board[playerY][playerX]).getInventory();
		for (int i = 0; i < 7; i++)
		{
			System.out.print(temp[i]);
		}
		System.out.println();
		drawItem(gc);
		// drawFog(gc);
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
		int[] temp = ((Player) board[playerY][playerX]).getInventory();
		Token token = new Token();
		RedKey r = new RedKey();
		GreenKey g = new GreenKey();
		BlueKey b = new BlueKey();
		YellowKey y = new YellowKey();
		FireBoot f = new FireBoot();
		Flipper fl = new Flipper();
		gc.drawImage(token.getImage(), 0, 500, 75, 75);
		gc.drawImage(r.getImage(), 150, 500, 75, 75);
		gc.drawImage(g.getImage(), 275, 500, 75, 75);
		gc.drawImage(b.getImage(), 400, 500, 75, 75);
		gc.drawImage(y.getImage(), 525, 500, 75, 75);
		gc.drawImage(f.getImage(), 0, 600, 75, 75);
		gc.drawImage(fl.getImage(), 150, 600, 75, 75);
		gc.setFont(new Font("Arial", 50));
		gc.strokeText(": " + temp[0], 75, 560);
		gc.strokeText(": " + temp[1], 225, 560);
		gc.strokeText(": " + temp[2], 350, 560);
		gc.strokeText(": " + temp[3], 475, 560);
		gc.strokeText(": " + temp[4], 600, 560);
		gc.strokeText(": " + temp[5], 75, 660);
		gc.strokeText(": " + temp[6], 225, 660);
	}

	public void move(String way)
	{
		switch (way)
		{
		case "right":
			if(((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX + 1]))
			{
				if(board[playerY][playerX + 1] instanceof Collectible)
					acquire((Collectible) board[playerY][playerX + 1]);
				board[playerY][playerX + 1] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerX = playerX + 1;
			}
			break;
		case "left":
			if(((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX - 1]))
			{
				if(board[playerY][playerX - 1] instanceof Collectible)
					acquire((Collectible) board[playerY][playerX - 1]);

				board[playerY][playerX - 1] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerX = playerX - 1;
			}
			break;
		case "up":
			if(((Player) board[playerY][playerX]).movable((Cell) background[playerY - 1][playerX]))
			{
				if(board[playerY - 1][playerX] instanceof Collectible)
					acquire((Collectible) board[playerY - 1][playerX]);

				board[playerY - 1][playerX] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerY = playerY - 1;
			}
			break;
		case "down":
			if(((Player) board[playerY][playerX]).movable((Cell) background[playerY + 1][playerX]))
			{
				if(board[playerY + 1][playerX] instanceof Collectible)
					acquire((Collectible) board[playerY + 1][playerX]);

				board[playerY + 1][playerX] = board[playerY][playerX];
				board[playerY][playerX] = new Empty();
				playerY = playerY + 1;
			}
			break;
		}
	}

	public void acquire(Collectible co)
	{
		((Player) board[playerY][playerX]).acquireInventory(co.getIndex());
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