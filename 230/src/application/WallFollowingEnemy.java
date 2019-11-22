package application;

/**
 * Wall Following Enemies 
 * follows wall 
 * @author Miles Singleton
 *
 */
public class WallFollowingEnemy extends Enemy {
    public WallFollowingEnemy(int currentX, int currentY) {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;
    }
}