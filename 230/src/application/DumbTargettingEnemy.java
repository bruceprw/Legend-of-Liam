package application;

/**
 * DumbTargettingEnemy.java
 * Class for dumb targeting enemy
 * Hunts the player using DFS
 * when not in range move in random directions
 * 
 * @author Miles Singleton
 * @version 0.0
 */
public class DumbTargettingEnemy extends Enemy
{
    public DumbTargettingEnemy(int currentX,int currentY, boolean HV)
    {
        this.currentPositionX = currentX;
        this.currentPositionY = currentY;

        this.direction = HV;
    }

    @Override
    protected void findNewPosition() {
        //find player location
        //move in said direction 
            //if wall stationary
        
        //if gone

        //random direction
    }
}