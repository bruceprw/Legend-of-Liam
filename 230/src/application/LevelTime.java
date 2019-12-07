package application;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * used to work out and store the time it takes to complete a
 * given level by a user.
 * 
 * @author
 *
 */
public class LevelTime {

	private String username;
	private long time;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

	/**
	 * Creates a new instance of the LevelTime and outputs the time.
	 * 
	 * @param u The username of the current player.
	 * @param t The time to complete the level.
	 */
	public LevelTime(String u, long t) {
		username = u;
		time = t;
	}

	/**
	 * Gets the stored username.
	 * 
	 * @return the user name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the time and formats its correctly.
	 * 
	 * @return The time it took to do the level.
	 */
	public long getTime() {
		Date a = new Date(time);
		// System.out.println(a);
		return a.getTime();
	}

	/**
	 * Gets the time but as a string formated correctly.
	 * 
	 * @return The time it took to complete the level as a string.
	 */
	public String getStringTime() {
		Date d = new Date(time);
		String a = sdf.format(d);
		return a;
	}
}