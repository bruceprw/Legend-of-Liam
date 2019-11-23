package application;

/**
 * Smart targeting enemies
 * uses BFS to find player and chase
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class SmartTargettingEnemy extends Enemy
{
    public SmartTargettingEnemy(int currentX,int currentY, boolean HV)
    {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.direction = HV;
    }

    @Override
    protected void findNewPosition() {
        //will need to apply BFS
        //move to nearest tile to player
        //see spaces as nodes to traverse
    }
    
    public String getString()
    {
    	return"SMART";
    }
}