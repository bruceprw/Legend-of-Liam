 package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard
{
	private String filePath = "Leaderboard\\leaderboard.txt";
	private ArrayList<LevelTime> list = new ArrayList<LevelTime>();

	public Leaderboard()
	{
		readLevelTime();
		sortList();
	}

	public void readLevelTime()
	{
		File file = new File(filePath);
		Scanner in =null;
		try
		{
			in = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (in.hasNext())
		{
			String line = in.nextLine();
			Scanner curLine = new Scanner(line);
			curLine.useDelimiter(",");
			String name = curLine.next();
			String time = curLine.next();
			addLevelTime(name, time);
			curLine.close();
		}
		
	}

	public void outputList()
	{
		File file = new File(filePath);
		PrintWriter out =
		null;
		try
		{
			out = new PrintWriter(file);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(LevelTime t : list)
		{
			String temp = t.getUsername()+","+t.getStringTime()+"\r\n";
			out.print(temp);
		}
		out.close();
	}

	public void addLevelTime(String name, String time)
	{
		LevelTime lt = new LevelTime(name, time);
		list.add(lt);
		outputList();
	}

	public ArrayList<LevelTime> getList()
	{
		return list;
	}

	public void sortList()
	{
		for (int i = 0; i < list.size(); i++)
		{
			LevelTime key = list.get(i);
			int j = i - 1;
			while (j >= 0 && list.get(j).getTime() > key.getTime())
			{
				list.set(j+1, list.get(j));
				j = j - 1;
			}
			list.set(j+1, key);
		}
	}
}
