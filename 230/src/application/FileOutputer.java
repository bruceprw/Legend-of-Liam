package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class FileOutputer
{
	private GameBoard lvl;
	private String output = "";
	private Element[][] background;
	private Element[][] board;

	public FileOutputer(GameBoard lvl)
	{
		this.lvl = lvl;

		background = lvl.getBackground();
		board = lvl.getBoard();
		output += "" + background[0].length + "," + background.length + "\r\n";
		String temp = "";
		
		output = setOutput(output,temp,board,background);
		temp = setTemp(output,temp,board,background);

		temp=setRemain(temp,lvl,board);
		
		try
		{
			File outputFile = new File("LevelFiles\\AndyLvl1.txt");
			PrintWriter out = new PrintWriter(outputFile);
			out.print(output+temp);
			out.close();
		}catch(Exception e)
		{
			System.out.println("Failed!");
		}
		
		
		//String finalOutput = output + temp;
		//System.out.println(finalOutput);
		//System.out.println(temp);
	}
	
	public String setRemain(String temp, GameBoard lvl, Element[][] board)
	{
		int[] inventory = ((Player) board[lvl.getPlayerY()][lvl.getPlayerX()]).getInventory();
		temp+="0,0,INVENTORY,";
		temp+="TOKEN,"+inventory[0]+",";
		temp+="RKEY,"+inventory[1]+",";
		temp+="GKEY,"+inventory[2]+",";
		temp+="BKEY,"+inventory[3]+",";
		temp+="YKEY,"+inventory[4]+",";
		temp+="BOOT,"+inventory[5]+",";
		temp+="FLIPPER,"+inventory[6]+"\r\n";
		temp+="////";
		return temp;
	}
	
	public String setOutput(String output,String temp, Element[][] board, Element[][] background)
	{
		for (int y = 0; y < board.length; y++)
		{
			for (int x = 0; x < board[y].length; x++)
			{
				switch (board[y][x].getString())
				{
				case "T":
					background[y][x] = board[y][x];
					break;
				case "RKEY":
					temp += "" + x + "," + y + ",RKEY" + "\r\n";
					break;
				case "GKEY":
					temp += "" + x + "," + y + ",GKEY" + "\r\n";
					break;
				case "BKEY":
					temp += "" + x + "," + y + ",BKEY" + "\r\n";
					break;
				case "YKEY":
					temp += "" + x + "," + y + ",YKEY" + "\r\n";
					break;
				case "F":
					background[y][x] = board[y][x];
					break;
				case "O":
					background[y][x] = board[y][x];
					break;
				case "START":
					temp+=""+x+","+y+",START"+"\r\n";
					break;
				case "STRAIGHT":
					temp += "" + x + "," + y + ",ENEMY,STRAIGHT," + ((StraightLineEnemy) board[y][x]).getDirection() + "\r\n";
					break;
				case "WALLHUG":
					temp += "" + x + "," + y + ",ENEMY,WALLHUG," + ((WallFollowingEnemy) board[y][x]).getDirection() + "\r\n";
					break;
				case "DUMB":
					temp += "" + x + "," + y + ",ENEMY,DUMB" + "\r\n";
					break;
				case "SMART":
					temp += "" + x + "," + y + ",ENEMY,SMART" + "\r\n";
					break;
				case "D":
					temp+= "" + x + "," + y + ",DOOR,"+((TokenDoor)board[y][x]).getTokenNum()+"\r\n";
					break;
				}
			}

		}

		for (int y = 0; y < background.length; y++)
		{
			for (int x = 0; x < background[y].length; x++)
			{
				switch (background[y][x].getString())
				{
				case "#":
					output += "#";
					break;
				case " ":
					output += " ";
					break;
				case "G":
					output += "G";
					break;
				case "L":
					output += "L";
					break;
				case "W":
					output += "W";
					break;
				case "@":
					output += "@";
					break;
				case "REDDOOR":
					output += " ";
					temp += "" + x + "," + y + ",REDDOOR\r\n";
					break;
				case "GREENDOOR":
					output += " ";
					temp += "" + x + "," + y + ",GREENDOOR\r\n";
					break;
				case "BLUEDOOR":
					output += " ";
					temp += "" + x + "," + y + ",BLUEDOOR\r\n";
					break;
				case "YELLOWDOOR":
					output += " ";
					temp += "" + x + "," + y + ",YELLOWDOOR\r\n";
					break;
				case "T":
					output += "T";
					break;
				case "F":
					output += "F";
					break;
				case "O":
					output += "O";
					break;
				}
			}
			output += "\r\n";
		}
		return output;
	}
	public String setTemp(String output,String temp, Element[][] board, Element[][] background)
	{
		for (int y = 0; y < board.length; y++)
		{
			for (int x = 0; x < board[y].length; x++)
			{
				switch (board[y][x].getString())
				{
				case "T":
					background[y][x] = board[y][x];
					break;
				case "RKEY":
					temp += "" + x + "," + y + ",RKEY" + "\r\n";
					break;
				case "GKEY":
					temp += "" + x + "," + y + ",GKEY" + "\r\n";
					break;
				case "BKEY":
					temp += "" + x + "," + y + ",BKEY" + "\r\n";
					break;
				case "YKEY":
					temp += "" + x + "," + y + ",YKEY" + "\r\n";
					break;
				case "F":
					background[y][x] = board[y][x];
					break;
				case "O":
					background[y][x] = board[y][x];
					break;
				case "START":
					temp+=""+x+","+y+",START"+"\r\n";
					break;
				case "STRAIGHT":
					temp += "" + x + "," + y + ",ENEMY,STRAIGHT," + ((StraightLineEnemy) board[y][x]).getDirection() + "\r\n";
					break;
				case "WALLHUG":
					temp += "" + x + "," + y + ",ENEMY,WALLHUG," + ((WallFollowingEnemy) board[y][x]).getDirection() + "\r\n";
					break;
				case "DUMB":
					temp += "" + x + "," + y + ",ENEMY,DUMB" + "\r\n";
					break;
				case "SMART":
					temp += "" + x + "," + y + ",ENEMY,SMART" + "\r\n";
					break;
				}
			}

		}

		for (int y = 0; y < background.length; y++)
		{
			for (int x = 0; x < background[y].length; x++)
			{
				switch (background[y][x].getString())
				{
				case "#":
					output += "#";
					break;
				case " ":
					output += " ";
					break;
				case "D":
					output += "D";
					break;
				case "G":
					output += "G";
					break;
				case "L":
					output += "L";
					break;
				case "W":
					output += "W";
					break;
				case "@":
					output += "@";
					break;
				case "REDDOOR":
					output += " ";
					temp += "" + x + "," + y + ",REDDOOR\r\n";
					break;
				case "GREENDOOR":
					output += " ";
					temp += "" + x + "," + y + ",GREENDOOR\r\n";
					break;
				case "BLUEDOOR":
					output += " ";
					temp += "" + x + "," + y + ",BLUEDOOR\r\n";
					break;
				case "YELLOWDOOR":
					output += " ";
					temp += "" + x + "," + y + ",YELLOWDOOR\r\n";
					break;
				case "T":
					output += "T";
					break;
				case "F":
					output += "F";
					break;
				case "O":
					output += "O";
					break;
				}
			}
			output += "\r\n";
		}
		return temp;
	}
}
