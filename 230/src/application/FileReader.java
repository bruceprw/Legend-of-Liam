package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader
{
	private int mapSizeX;
	private int mapSizeY;
	private String[] map;
	private Element[][] board;
	private Element[][] background;
	private int playerX;
	private int playerY;

	public FileReader(String filePath) throws FileNotFoundException
	{
		File file = new File(filePath);
		Scanner in = new Scanner(file);
		String sizeLine = in.nextLine();
		Scanner sizeScan = new Scanner(sizeLine);
		sizeScan.useDelimiter(",");
		mapSizeX = sizeScan.nextInt();
		mapSizeY = sizeScan.nextInt();
		board = new Element[mapSizeY][mapSizeX];
		background = new Element[mapSizeY][mapSizeX];
		map = new String[mapSizeY];
		for (int i = 0; i < mapSizeY; i++)
		{
			map[i] = in.nextLine();
		}

		subMap(map);

		while (in.hasNext())
		{
			String temp = in.nextLine();
			if(temp.equals("////"))
				break;
			Scanner addLine = new Scanner(temp);
			subAdd(addLine);
		}
		for (int y = 0; y < board.length; y++)
		{
			for (int x = 0; x < board[y].length; x++)
			{
				if(board[y][x] == null)
					board[y][x] = new Space();
			}
		}
		for (int y = 0; y < background.length; y++)
		{
			for (int x = 0; x < background[y].length; x++)
			{
				if(background[y][x] == null)
					background[y][x] = new Space();
			}
		}
		/*
		for (int y = 0; y < board.length; y++)
		{
			for (int x = 0; x < board[y].length; x++)
			{
				System.out.print(board[y][x].getString());
			}
			System.out.println();
		}
		for (int y = 0; y < background.length; y++)
		{
			for (int x = 0; x < background[y].length; x++)
			{
				System.out.print(background[y][x].getString());
			}
			System.out.println();
		}*/

	}

	public int getPlayerX()
	{
		return playerX;
	}

	public int getPlayerY()
	{
		return playerY;
	}

	public Element[][] getBackground()
	{
		return this.background;
	}

	public Element[][] getBoard()
	{
		return this.board;
	}

	public void subAdd(Scanner line)
	{
		line.useDelimiter(",");
		int x = line.nextInt();
		int y = line.nextInt();
		String feature = line.next();
		switch (feature)
		{
		case "START":
			playerX = x;
			playerY = y;
			board[y][x] = new Player();
			break;
		case "ENEMY":
			String type = line.next();
			if(type.equals("STRAIGHT"))
			{
				String way = line.next();
				board[y][x] = new StraightLineEnemy(way);
			}
			else if(type.equals("WALLHUG"))
			{
				String way = line.next();
				board[y][x] = new WallFollowingEnemy(way);
			}
			else if(type.equals("DUMB"))
				board[y][x] = new DumbTargettingEnemy();
			else if(type.equals("SMART"))
				board[y][x] = new SmartTargettingEnemy();
			break;
		case "RKEY":
			board[y][x] = new RedKey();
			break;
		case "GKEY":
			board[y][x] = new GreenKey();
			break;
		case "BKEY":
			board[y][x] = new BlueKey();
			break;
		case "YKEY":
			board[y][x] = new YellowKey();
			break;
		case "REDDOOR":
			background[y][x] = new RedDoor();
			break;
		case "GREENDOOR":
			background[y][x] = new GreenDoor();
			break;
		case "BLUEDOOR":
			background[y][x] = new BlueDoor();
			break;
		case "YELLOWDOOR":
			background[y][x] = new YellowDoor();
			break;
		case "INVENTORY":
			while (line.hasNext())
			{
				String collecti = line.next();
				int num = line.nextInt();
				switch (collecti)
				{
				case "TOKEN":
					((Player) board[playerY][playerX]).setInventory(0, num);
					break;
				case "RKEY":
					((Player) board[playerY][playerX]).setInventory(1, num);
				case "GKEY":
					((Player) board[playerY][playerX]).setInventory(2, num);
					break;
				case "BKEY":
					((Player) board[playerY][playerX]).setInventory(3, num);
					break;
				case "YKEY":
					((Player) board[playerY][playerX]).setInventory(4, num);
					break;
				case "BOOT":
					((Player) board[playerY][playerX]).setInventory(5,num);
					break;
				case "FLIPPER":
					((Player) board[playerY][playerX]).setInventory(6,num);
					break;
				}
			}
			break;
		}
	}

	public void subMap(String[] temp)
	{

		for (int j = 0; j < temp.length; j++)
		{
			for (int i = 0; i < temp[j].length(); i++)
			{
				char temp1 = temp[j].charAt(i);
				switch (temp1)
				{
				case '#':
					background[j][i] = new Wall();
					break;
				case ' ':
					background[j][i] = new Space();
					break;
				case 'D':
					background[j][i] = new Door();
					break;
				case 'G':
					background[j][i] = new Goal();
					break;
				case 'L':
					background[j][i] = new Lava();
					break;
				case 'W':
					background[j][i] = new Water();
					break;
				case '@':
					background[j][i] = new Teleporter();
					break;
				case 'T':
					board[j][i] = new Token();
					break;
				case 'O':
					board[j][i] = new FireBoot();
					break;
				case 'F':
					board[j][i] = new Flipper();
					break;
				}
			}
		}
	}
}
