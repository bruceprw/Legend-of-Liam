package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads the leader board file and output the leader board to leaderboard page.
 * 
 * @author Andy Kuo
 *
 */
public class Leaderboard {
	private String filePath;
	private ArrayList<LevelTime> list = new ArrayList<LevelTime>();

	/**
	 * Creates instance of leaderboard.
	 * 
	 * @param level the level and file title of each leaderboard.
	 */
	public Leaderboard(String level) {
		initLeaderboard(level);
	}

	/**
	 * Initiate the file reading.
	 * 
	 * @param level Which levels leadboard should be displayed
	 */
	public void initLeaderboard(String level) {
		this.filePath = "Leaderboard\\leaderboard" + level + ".txt";
		readLevelTime();
		sortList();
	}

	/**
	 * Read the level info.
	 */
	public void readLevelTime() {
		File file = new File(filePath);
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (in.hasNext()) {
			String line = in.nextLine();
			Scanner curLine = new Scanner(line);
			curLine.useDelimiter(",");
			String name = curLine.next();
			long time = curLine.nextLong();
			addLevelTime(name, time);
			curLine.close();
		}

	}

	/**
	 * Output the information after the information has been changed.
	 */
	public void outputList() {
		File file = new File(filePath);
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (LevelTime t : list) {
			String temp = t.getUsername() + "," + t.getTime() + "\r\n";
			out.print(temp);
		}
		out.close();
	}

	/**
	 * Update the list by adding new value.
	 * 
	 * @param name the name of player.
	 * @param time the time taken to finish the game.
	 */
	public void addLevelTime(String name, long time) {
		LevelTime lt = new LevelTime(name, time);
		list.add(lt);
		outputList();
	}

	/**
	 * Gets the list that stores the names and time taken.
	 * 
	 * @return the list that stores names and time taken.
	 */
	public ArrayList<LevelTime> getList() {
		return list;
	}

	/**
	 * Sort the list according to time difference.
	 */
	public void sortList() {
		for (int i = 0; i < list.size(); i++) {
			LevelTime key = list.get(i);
			int j = i - 1;
			while (j >= 0 && list.get(j).getTime() > key.getTime()) {
				list.set(j + 1, list.get(j));
				j = j - 1;
			}
			list.set(j + 1, key);
		}
	}
}
