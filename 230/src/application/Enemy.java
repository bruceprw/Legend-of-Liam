
/**
 * Enemy super class, abstract not be to called
 * 
 * @author Miles Singleton
 * @version 0.0
 */
abstract class Enemy {

    /// the current X and Y position
    protected int currentPositionX;
    protected int currentPositionY;

    /// the next position for the enemy
    protected int nextPositionX;
    protected int nextPositionY;

    protected boolean direction;
    // TODO rename this
    protected boolean bounce;

    // TODO rename this
    final private int ONE = 1;
    final private int TWO = 1;

    /**
     * Works out where the enemy needs to move and 
     * changes current position to where it should be moved to
     * 
     * @return The new position on game board
     */
    public int[][] move() {
        findNewPosition();
        setNewPositions();
        return getCurrentPosition();
    }

    /**
     * Checks if next tile is a space
     * @return x True if can move to new position, else False
     */
    private boolean findNextTile() {
        // get game board
        // find out if x+1,(Y+1 || Y -1) is a wall tile
        // if yes continue

        boolean x = true;
        // else reverse direction
        return x;
    }

    /**
     * checks if next space movable and 
     * calls methods based on said checks
     */
    protected void findNewPosition() {
        // check next tile see if its not valid
        if (!findNextTile()) {
            // if not valid check corner
            if (checkCorner()) // does not apply to straight enemies
            {
                // if corner move to corner
                cornerMoveTo();

            } else {
                // if not corner reverse bounce and move
                setBounce(!getBounce());
                moveTo();
            }
        }
    }

    /**
     * Moves enemy to next space
     */
    private void moveTo() {
        if (getDirection()) {
            if (getBounce()) {
                this.nextPositionX = currentPositionX - ONE;
            } else {
                this.nextPositionX = currentPositionX + ONE;
            }
        } else {
            if (getBounce()) {
                this.nextPositionY = currentPositionY - ONE;
            } else {
                this.nextPositionY = currentPositionY + ONE;
            }
        }
    }

    /**
     * Movies enemy to a corner
     */
    private void cornerMoveTo() {
        if (getDirection()) {
            this.nextPositionX = currentPositionX + ONE;
            this.nextPositionY = currentPositionY + TWO;
        } else {
            this.nextPositionX = currentPositionX + TWO;
            this.nextPositionY = currentPositionY + ONE;
        }

        setDirection(!getDirection());
    }

    /**
     * returns the direction of enemy
     * @return direction
     */
    private boolean getDirection() {
        return this.direction;
    }

    /**
     * returns the bounce of the enemy
     * @return bounce
     */
    private boolean getBounce() {
        return this.bounce;
    }

    /**
     * sets the bounce of the enemy
     * @param hold 
     */
    private void setBounce(boolean hold) {
        this.bounce = hold;
    }

    /**
     * sets the direction of the enemy
     * @param hold 
     */
    private void setDirection(boolean hold) {
        this.bounce = hold;
    }

    /**
     * checks if there is a movable corner
     * @return True if there is a movable corner
     */
    private boolean checkCorner() {
        // else if (x+1,y+2) == wall
        // move x+1,y+1

        return false;
    }

    /**
     * returns 2D int array of current location
     * @return 2D int array of current position
     */
    public int[][] getCurrentPosition() {
        return new int[currentPositionX][currentPositionY];
    }

    /**
     * changes current position to next position variable
     */
    public void setNewPositions() {
        this.currentPositionX = nextPositionX;
        this.currentPositionY = nextPositionY;
    }
}