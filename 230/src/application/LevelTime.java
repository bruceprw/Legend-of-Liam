package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevelTime
{

	private String username;
	private String time;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public LevelTime(String u, String t)
	{
		username = u;
		time = t;
	}

	public String getUsername()
	{
		return username;
	}

	public long getTime()
	{
		Date a = null;
		try
		{
			a = sdf.parse(time);
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a.getTime();
	}

	public String getStringTime()
	{
		return time;
	}
}