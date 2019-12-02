package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Character.DumbTargettingEnemy;
import Character.Enemy;
import Character.Node;
import Character.Path;
import Character.Player;
import Character.SmartTargettingEnemy;
import Character.StraightLineEnemy;
import Character.WallFollowingEnemy;
import Collectibles.BlueKey;
import Collectibles.Collectible;
import Collectibles.FireBoot;
import Collectibles.Flipper;
import Collectibles.GreenKey;
import Collectibles.RedKey;
import Collectibles.Token;
import Collectibles.YellowKey;
import cell.Cell;
import cell.Teleporter;
import cell.Wall;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameBoard
{
	private Element[][] board;
	private Element[][] background;
	private Element[][] fog;
	private int playerX;
	private int playerY;
	private int goalX;
	private int goalY;
	
	private Media playerDie = new Media(new File("Sound\\player_killed.wav").toURI().toString());
	
	private MediaPlayer mediaPlayer;
	
	private ArrayList<Integer> enemyX;
	private ArrayList<Integer> enemyY;

	private long time;

	public GameBoard(String filePath) throws FileNotFoundException
	{
		FileReader lvl = new FileReader(filePath);
		time = lvl.getTime();
		this.board = lvl.getBoard();
		this.background = lvl.getBackground();
		playerX = lvl.getPlayerX();
		playerY = lvl.getPlayerY();
		fog = lvl.getFog();
		enemyX = lvl.getEnemyX();
		enemyY = lvl.getEnemyY();
		goalX = lvl.getGoalX();
		goalY = lvl.getGoalY();
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

	public long getTime()
	{
		return time;
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

		// int[] temp = ((Player) board[playerY][playerX]).getInventory();
		/*
		 * for (int i = 0; i < 7; i++) { System.out.print(temp[i]); }
		 */
		// System.out.println();
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

	public boolean end()
	{
		return playerY == goalY && playerX == goalX;

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
		gc.setFill(Color.MEDIUMSEAGREEN);
		gc.fillRect(0, 510, 700, 200);
		gc.setFill(Color.GOLD);
		gc.drawImage(token.getImage(), 0, 610, 75, 75);
		gc.drawImage(r.getImage(), 0, 510, 75, 75);
		gc.drawImage(g.getImage(), 150, 510, 75, 75);
		gc.drawImage(b.getImage(), 300, 510, 75, 75);
		gc.drawImage(y.getImage(), 450, 510, 75, 75);
		gc.drawImage(f.getImage(), 150, 610, 75, 75);
		gc.drawImage(fl.getImage(), 300, 610, 75, 75);
		gc.setFont(new Font("Arial", 50));
		gc.fillText(": " + temp[0], 75, 670);
		gc.fillText(": " + temp[1], 75, 570);
		gc.fillText(": " + temp[2], 225, 570);
		gc.fillText(": " + temp[3], 375, 570);
		gc.fillText(": " + temp[4], 525, 570);
		gc.fillText(": " + temp[5], 225, 670);
		gc.fillText(": " + temp[6], 375, 670);
		
	}

	public void playBoardSound(int x, int y)
	{
		board[y][x].playSound();
	}

	public void playBackSound(int x, int y)
	{
		background[y][x].playSound();
	}

	public boolean touchEnemy(int x, int y)
	{
		return board[y][x] instanceof Enemy;
	}

	//TODO this needs to be broken into several methods it far too big,
	//TODO also comment this what does this even do?
	public int move(String way)
	{
		// moveEnemy();
		switch (way)
		{
		case "right":
			mediaPlayer = ((Cell)background[playerY][playerX+1]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX + 1]))
			{
				if (touchEnemy(playerX + 1, playerY))
				{
					return 2;
				}
				if (((Cell) background[playerY][playerX + 1]) instanceof Teleporter)
				{
					Teleporter temp = ((Teleporter) background[playerY][playerX + 1]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				}
				else
				{
					if (board[playerY][playerX + 1] instanceof Collectible)
					{
						playBoardSound(playerX+1,playerY);
						//board[playerY][playerX + 1].playSound();
						acquire((Collectible) board[playerY][playerX + 1]);
					}
					board[playerY][playerX + 1] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerX += 1;
					// System.out.println(playerX);
				}

			}
			break;
		case "left":
			mediaPlayer = ((Cell)background[playerY][playerX-1]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY][playerX - 1]))
			{
				if (touchEnemy(playerX - 1, playerY))
				{
					return 2;
				}

				if (((Cell) background[playerY][playerX - 1]) instanceof Teleporter)
				{
					Teleporter temp = ((Teleporter) background[playerY][playerX - 1]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				}
				else
				{
					if (board[playerY][playerX - 1] instanceof Collectible)
					{
						playBoardSound(playerX-1,playerY);
						acquire((Collectible) board[playerY][playerX - 1]);
						board[playerY][playerX - 1].playSound();
					}

					board[playerY][playerX - 1] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerX = playerX - 1;
					// System.out.println(playerX);
				}

			}
			break;
		case "up":
			mediaPlayer = ((Cell)background[playerY-1][playerX]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY - 1][playerX]))
			{
				if (touchEnemy(playerX, playerY - 1))
				{
					return 2;
				}

				if (((Cell) background[playerY - 1][playerX]) instanceof Teleporter)
				{
					Teleporter temp = ((Teleporter) background[playerY - 1][playerX]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				}
				else
				{

					if (board[playerY - 1][playerX] instanceof Collectible)
					{
						playBoardSound(playerX,playerY-1);
						board[playerY][playerX + 1].playSound();
						acquire((Collectible) board[playerY - 1][playerX]);
					}
					board[playerY - 1][playerX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = playerY - 1;
					// System.out.println(playerY);
				}
			}
			break;
		case "down":
			mediaPlayer = ((Cell)background[playerY+1][playerX]).getSound();
			mediaPlayer.play();
			mediaPlayer.stop();
			if (((Player) board[playerY][playerX]).movable((Cell) background[playerY + 1][playerX]))
			{
				if (touchEnemy(playerX, playerY + 1))
				{
					return 2;
				}

				if (((Cell) background[playerY + 1][playerX]) instanceof Teleporter)
				{
					Teleporter temp = ((Teleporter) background[playerY + 1][playerX]);
					int tempX = temp.getPairX();
					int tempY = temp.getPairY();
					board[tempY][tempX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = tempY;
					playerX = tempX;
				}
				else
				{

					if (board[playerY + 1][playerX] instanceof Collectible)
					{
						playBoardSound(playerX,playerY+1);
						board[playerY][playerX + 1].playSound();
						acquire((Collectible) board[playerY + 1][playerX]);
					}
					board[playerY + 1][playerX] = board[playerY][playerX];
					board[playerY][playerX] = new Empty();
					playerY = playerY + 1;
					// System.out.println(playerY);
				}
			}
			break;

		}
		moveEnemy();
		//System.out.println(checkPlayerDead());
		if (playerDead() || checkPlayerDead())
		{
			mediaPlayer = new MediaPlayer(playerDie);
			mediaPlayer.play();
			//mediaPlayer.stop();
			return 2;
		}
		if (end())
			return 1;
		return 0;
	}

	public boolean checkPlayerDead()
	{
		return !(board[playerY][playerX] instanceof Player);
	}

	public boolean playerDead()
	{
		for (int i = 0; i < enemyX.size(); i++)
		{
			if (playerX == enemyX.get(i) && playerY == enemyY.get(i))
			{
				return true;
			}
		}
		return false;
	}

	public boolean touchPlayer(int x, int y)
	{
		return board[y][x] instanceof Player;
	}

	public void moveEnemy()
	{
		for (int i = 0; i < enemyX.size(); i++)
		{
			switch (board[enemyY.get(i)][enemyX.get(i)].getString())
			{
			case "STRAIGHT":
				StraightLineEnemy a = (StraightLineEnemy) board[enemyY.get(i)][enemyX.get(i)];
				boolean hori = a.horizontalNoMove(this, enemyX.get(i), enemyY.get(i));
				boolean lOR = a.getMovDirection().equals("LEFT") || a.getMovDirection().equals("RIGHT");
				boolean verti = a.verticalNoMove(this, enemyX.get(i), enemyY.get(i));
				boolean UD = a.getMovDirection().equals("UP") || a.getMovDirection().equals("DOWN");
				if ((hori && lOR) || (verti && UD))
				{

				}
				else
				{
					int newX = a.getX(this, enemyX.get(i), enemyY.get(i));
					int newY = a.getY(this, enemyX.get(i), enemyY.get(i));
					board[newY][newX] = board[enemyY.get(i)][enemyX.get(i)];
					board[enemyY.get(i)][enemyX.get(i)] = new Empty();
					enemyY.set(i, newY);
					enemyX.set(i, newX);
					// System.out.print(enemyX.get(i));
					// System.out.print(","+enemyY.get(i));
					// System.out.println(",straight");
				}
				break;
			case "WALLHUG":
				WallFollowingEnemy b = (WallFollowingEnemy) board[enemyY.get(i)][enemyX.get(i)];
				int newX = b.getX(this, enemyX.get(i), enemyY.get(i));
				int newY = b.getY(this, enemyX.get(i), enemyY.get(i));
				// System.out.println(newY);
				board[newY][newX] = board[enemyY.get(i)][enemyX.get(i)];
				board[enemyY.get(i)][enemyX.get(i)] = new Empty();
				enemyY.set(i, newY);
				enemyX.set(i, newX);
				// System.out.println(Enemy.checkMove(this,22,21));

				break;
			case "DUMB":
				DumbTargettingEnemy c = (DumbTargettingEnemy) board[enemyY.get(i)][enemyX.get(i)];
				int newXa = c.getX(this, playerX, playerY, enemyX.get(i), enemyY.get(i));
				// System.out.print(newXa+",");
				int newYa = c.getY(this, playerX, playerY, enemyX.get(i), enemyY.get(i));
				// System.out.println(newYa);
				if (newXa == enemyX.get(i) && newYa == enemyY.get(i))
				{

				}
				else
				{
					board[newYa][newXa] = board[enemyY.get(i)][enemyX.get(i)];
					board[enemyY.get(i)][enemyX.get(i)] = new Empty();
					enemyY.set(i, newYa);
					enemyX.set(i, newXa);
				}
				break;
			case "SMART":
				SmartTargettingEnemy d = (SmartTargettingEnemy) board[enemyY.get(i)][enemyX.get(i)];
				Path e = d.getPath(this, enemyX.get(i), enemyY.get(i), playerX, playerY);

				int newXe = e.getX();
				int newYe = e.getY();

				if (newXe == e.getX() && newYe == e.getY())
				{

				}
				else
				{
					board[newYe][newXe] = board[enemyY.get(i)][enemyX.get(i)];
					board[enemyY.get(i)][enemyX.get(i)] = new Empty();
					enemyY.set(i, newYe);
					enemyX.set(i, newXe);
				}

				// System.out.println(newXe+","+newYe);

				
				/* System.out.print(enemyX.get(i)); System.out.print(",");
				 System.out.print(enemyY.get(i)); System.out.print(",");
				 System.out.println(board[enemyY.get(i)][enemyX.get(i)].getString());
				 System.out.print(playerX); System.out.print(","+playerY);
				 System.out.println("Player");*.
				 

				/*
				 * System.out.print(enemyX.get(i)); System.out.print(","+enemyY.get(i));
				 * System.out.println(",SMART"); System.out.println("bca");
				 */
				break;

			}
		}
	}

	public Cell getCell(int X, int Y)
	{
		return (Cell) background[Y][X];
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