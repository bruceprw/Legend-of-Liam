package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserProfile
{
	private String name;
	private String password;
	private int levelProg;
	private static String filePath = "UserProfiles\\profiles.txt";
	private static ArrayList<UserProfile> list = new ArrayList<UserProfile>();

	public UserProfile(String name, String password, int levelProg)
	{
		this.name = name;
		this.password = password;
		this.levelProg = levelProg;
	}

	public static void deleteUserProfile(String username, String password, int levelProg)
	{
		readList();
		for(int i=0;i<list.size();i++)
		{
			if (list.get(i).getName().equals(username))
				list.remove(i);
		}
		outputList();
	}
	
	public static void updateUserProfile(String initialName, String username, String password, int levelProg)
	{
		readList();
		for(int i=0;i<list.size();i++)
		{
			if (list.get(i).getName().equals(initialName))
			{
				list.get(i).setName(username);
				list.get(i).setPassword(password);
				list.get(i).setLevelProg(levelProg);
			}
		}
		outputList();
		// reset and update
	}
	
	public static void readList()
	{
		File file = new File(filePath);
		Scanner in=null;
		try
		{
			in = new Scanner(file);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (in.hasNext())
		{
			Scanner curLine = new Scanner(in.nextLine());
			curLine.useDelimiter(",");
			String n = curLine.next();
			String p = curLine.next();
			int l = curLine.nextInt();
			list.add(new UserProfile(n,p,l));
			curLine.close();
		}
		in.close();
	}
	
	public static void outputList()
	{
		File file = new File(filePath);
		PrintWriter out=null;
		try
		{
			out = new PrintWriter(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		for(int i=0;i<list.size();i++)
		{
			out.print(""+list.get(i).getName()+","+list.get(i).getPassword()+","+list.get(i).getLevelProg()+"\r\n");
		}
		
		
		out.close();
		
	}

	public static boolean createUserProfile(String name, String password, int levelProg) throws FileNotFoundException
	{
		readList();
		if (exists(name))
		{
			//System.out.println(exists(name));
			return true;
		}
		else
		{
			list.add(new UserProfile(name,password,0));
			
			outputList();
			
		}
		return false;
	}

	public void setLevelProg(int l)
	{
		this.levelProg = l;
	}
	
	public int getLevelProg()
	{
		return levelProg;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;

	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	public static boolean exists(String name)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}

}
