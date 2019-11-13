package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader
{
	private int mapSizeX;
	private int mapSizeY;
	private String[] map;
	private Elements[][] board;

	public Elements[][] read(String filePath) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\user\\Desktop\\Map.txt");
		Scanner in = new Scanner(file);
		String sizeLine = in.nextLine();
		System.out.println(sizeLine);
		Scanner sizeScan = new Scanner(sizeLine);
		sizeScan.useDelimiter(",");
		mapSizeX = sizeScan.nextInt();
		mapSizeY = sizeScan.nextInt();
		System.out.println(mapSizeX);
		System.out.println(mapSizeY);
		board = new Elements[mapSizeY][mapSizeX];
		map = new String[mapSizeY];
		for (int i = 0; i < mapSizeY; i++)
		{
			map[i] = in.nextLine();
		}

		subMap(map);
		
		while (in.hasNext())
		{
			String temp = in.nextLine();
			if (temp.equals("////"))
				break;
			Scanner addLine = new Scanner(temp);
			subAdd(addLine);
		}	
		return board;
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
			System.out.println(x);
			System.out.println(y);
			board[y][x] = new Player();
			break;
		case "ENEMY":
			String type = line.next();
			String way = line.next();
			if(type.equals("STRAIGHT"))
				board[y][x] = new StraightEnemy(way);
			break;
		case "WATER":
			board[y][x] = new Water();
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
					board[j][i] = new Wall();
					break;
				case ' ':
					board[j][i] = new Space();
					break;
				case 'D':
					board[j][i] = new Door();
					break;
				case 'G':
					board[j][i] = new Goal();
					break;
				case 'L':
					board[j][i] = new Lava();
					break;
				case 'W':
					board[j][i] = new Water();
					break;
				case '@':
					board[j][i] = new Teleporter();
					break;
				case 'T':
					board[j][i] = new Token();
					break;
				case 'K':
					board[j][i] = new Key();
					break;
				case 'B':
					board[j][i] = new Boot();
					break;
				case 'F':
					board[j][i] = new Flipper();
					break;
					
				}
			}
		}
	}
}
