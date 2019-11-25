package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserProfile
{
	private String name;
	private String password;
	private int levelProg;
	private String filePath = "UserProfiles\\profiles.txt";
	
	
	public UserProfile(String name, String password, int levelProg)
	{
		this.name = name;
		this.password = password;
		this.levelProg=levelProg;
	}
	
	public void createUserProfile() throws FileNotFoundException
	{
		File file = new File(filePath);
		Scanner in = new Scanner(file);
		String temp =  "";
		String output = name+","+password+","+levelProg+"\r\n";
		while (in.hasNext())
		{
			temp+=in.nextLine();
			temp+="\r\n";
		}
		System.out.println(temp);
		temp+=output;
		PrintWriter out = new PrintWriter(file);
		out.print(temp);
		out.close();
		in.close();
		
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
	
	
}
