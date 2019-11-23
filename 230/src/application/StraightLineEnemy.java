package application;

/**
 * Straight line enemy
 * moves in a straight line
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class StraightLineEnemy extends Enemy {
    public StraightLineEnemy(int currentX, int currentY, boolean HV, String movDirection) {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.direction = HV;
        this.movDirection = movDirection;
    }

    //TODO write code for this 
    private boolean checkCorner()
    {
        return false;
    }
    
    String getDirection()
    {
    	return movDirection;
    }

}