package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevelTime
{

	private String username;
	private long time;
	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

	public LevelTime(String u, long t)
	{
		username = u;
		System.out.println(t);
		time = t;
	}

	public String getUsername()
	{
		return username;
	}

	public long getTime()
	{
		Date a = new Date(time);
		//System.out.println(a);
		return a.getTime();
	}

	public String getStringTime()
	{
		Date d =new Date(time);
		String a = sdf.format(d);
		return a;
	}
}