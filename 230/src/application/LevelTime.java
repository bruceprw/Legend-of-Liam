package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevelTime {

	private String username;
	private String time;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public LevelTime(String u, String t) {
		username = u;
		time = t;
	}
	
	public String getUsername() {
		return username;
	}
	
	public long getTime() throws ParseException 
	{
		Date a = sdf.parse(time);
		
		return a.getTime();
	}
	
	public String getStringTime()
	{
		return time;
	}
}