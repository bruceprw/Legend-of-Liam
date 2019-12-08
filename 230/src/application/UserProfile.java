package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to create and store user's profiles.
 * 
 * @author Andy Kuo
 *
 */
public class UserProfile {
	private String name;
	private String password;
	private int levelProg;
	private static String filePath = "UserProfiles\\profiles.txt";
	private static ArrayList<UserProfile> list = new ArrayList<UserProfile>();

	/**
	 * Creates a new user profile.
	 * 
	 * @param name      The name of the profile.
	 * @param password  The password of the profile.
	 * @param levelProg What level they have made it to.
	 */
	public UserProfile(String name, String password, int levelProg) {
		this.name = name;
		this.password = password;
		this.levelProg = levelProg;
	}

	/**
	 * Deletes the user profile. Requires there user name, password and level
	 * progress to delete.
	 * 
	 * @param username  The name of the profile.
	 * @param password  The password of the file.
	 * @param levelProg There level progress in the game.
	 */
	public static void deleteUserProfile(String username, String password, int levelProg) {
		readList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(username))
				list.remove(i);
		}
		outputList();
	}

	/**
	 * Updates the user profile Uses initialName to find the profile and enter the
	 * new information in to change.
	 * 
	 * @param initialName The name before the change.
	 * @param username    The new user name of the profile.
	 * @param password    The new password for the profile.
	 * @param levelProg   The updated level progress.
	 */
	public static void updateUserProfile(String initialName, String username, String password, int levelProg) {
		readList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(initialName)) {
				list.get(i).setName(username);
				list.get(i).setPassword(password);
				list.get(i).setLevelProg(levelProg);
			}
		}
		outputList();
		// reset and update
	}

	/**
	 * Reads the file that contains the user profiles.
	 */
	public static void readList() {
		File file = new File(filePath);
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (in.hasNext()) {
			Scanner curLine = new Scanner(in.nextLine());
			curLine.useDelimiter(",");
			String n = curLine.next();
			String p = curLine.next();
			int l = curLine.nextInt();
			list.add(new UserProfile(n, p, l));
			curLine.close();
		}
		in.close();
	}

	/**
	 * Used to output the user profile file to the program.
	 */
	public static void outputList() {
		File file = new File(filePath);
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			out.print("" + list.get(i).getName() + "," + list.get(i).getPassword() + "," + list.get(i).getLevelProg()
					+ "\r\n");
		}

		/*
		 * Added this as repeated calls of readList() put duplicate profiles in list,
		 * which were then outputted to the file.
		 */
		list.clear();

		out.close();

	}

	/**
	 * Used to create a new user profile, used in the title screen.
	 * 
	 * @param name      The name of the profile.
	 * @param password  The password of the profile.
	 * @param levelProg The level progress made by the user.
	 * @return True if the user already exists else False.
	 * @throws FileNotFoundException Tells the system that this method could cause
	 *                               an error whereby the file cannot be located.
	 */
	public static boolean createUserProfile(String name, String password, int levelProg) throws FileNotFoundException {
		readList();
		if (exists(name)) {
			// System.out.println(exists(name));

			// Clears list to avoid future duplicates being saved.
			list.clear();
			return true;
		} else {
			list.add(new UserProfile(name, password, 0));

			outputList();

		}
		return false;
	}

	/**
	 * Used to set the level progress.
	 * 
	 * @param l What level the user has gotten to.
	 */
	public void setLevelProg(int l) {
		this.levelProg = l;
	}

	/**
	 * Gets the level progress.
	 * 
	 * @return Which level the user has gotten to.
	 */
	public int getLevelProg() {
		return levelProg;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name Name of the user.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the user profiles name.
	 * 
	 * @return Name of the user.
	 */
	public String getName() {
		return name;

	}

	/**
	 * Sets the password for a user profile.
	 * 
	 * @param password The password for the profile.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the password of the user profile.
	 * 
	 * @return Password for the profile.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Checks if a profile exists by the name of the profile.
	 * 
	 * @param name The name of the requested profile.
	 * @return True if it exists else false.
	 */
	public static boolean exists(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
