package application;

public class LevelTime {
	private int rank;
	private String username;
	private String time;
	
	public LevelTime(int r, String u, String t) {
		rank = r;
		username = u;
		time = t;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getTime() {
		return time;
	}
}