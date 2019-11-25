package application;

public class UserProfile
{
	private String name;
	private String password;
	
	
	public UserProfile(String name, String password)
	{
		this.name = name;
		this.password = password;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
		
	}
	
	public void setPassword()
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	
}
