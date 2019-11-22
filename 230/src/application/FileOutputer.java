package application;

public class FileOutputer
{
	private GameBoard lvl;
	private String output = "";
	private Element[][] background;
	private Element[][] board;
	
	public FileOutputer(GameBoard lvl, UserProfile profile)
	{
		this.lvl = lvl;
		
		background = lvl.getBackground();
		board = lvl.getBoard();
		
		String temp = ""; 
		
		for(int y=0;y<board.length;y++)
		{
			for(int x=0;x<board[y].length;x++)
			{
				switch(board[y][x].getString())
				{
				case "Token":
					background[y][x]=board[y][x];
					break;
				case "RedKey":
					break;
				case "GreenKey":
					break;
				case "BlueKey":
					break;
				case "YellowKey":
					break;
				case "Flipper":
					break;
				case "FireBoot":
					break;
				case "Player":
					break;
				case "StraightLineEnemy":
					break;
				case "WallFollowingEnemy":
					break;
				case "DumbTargettingEnemy":
					break;
				case "SmartTargettingEnemy":
					break;
				}
			}
		}
		
		for(int y=0;y<background.length;y++)
		{
			for(int x=0; x<background[y].length;x++)
			{
				switch (background[y][x].getString())
				{
				case "Wall":
					output+="#";
					break;
				case "Space":
					output+=" ";
					break;
				case "Door":
					output+="D";
					break;
				case "Goal":
					output+="G";
					break;
				case "Lava":
					output+="L";
					break;
				case "Water":
					output+="W";
					break;
				case "Teleporter":
					output+="@";
					break;
				case "RedDoor":
					temp = "" + x + "," + y+ ",REDDOOR\n";
					break;
				case "GreenDoor":
					temp = "" + x + "," + y+ ",GREENDOOR\n";
					break;
				case "BlueDoor":
					temp = "" + x + "," + y+ ",BLUEDOOR\n";
					break;
				case "YellowDoor":
					temp = "" + x + "," + y+ ",YELLOWDOOR\n";
					break;	
				}
				output +="\n";
			}
		}
		
	}
}
