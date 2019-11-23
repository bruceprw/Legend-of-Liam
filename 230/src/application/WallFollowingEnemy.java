package application;

/**
 * Wall Following Enemies 
 * follows wall 
 * @author Miles Singleton
 *
 */
public class WallFollowingEnemy extends Enemy {
    public WallFollowingEnemy(int currentX, int currentY,String movDirection) {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;
        this.movDirection=movDirection;
    }
    
    String getDirection()
    {
    	return movDirection;
    }
    public String getString()
    {
    	return"WALLHUG";
    }
}