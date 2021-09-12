
package td6.logic;

/**
 * Game interface to move pacman and play in a level
 */
public interface Game {
    /**
     * move the pacman up
     * @post pacman.y = pacman.y_old and (pacman.x = pacman.x_old or pacman.x = pacman.x_old - 1)
     */
    void moveUP();

    /**
     * move the pacman right
     * @post pacman.x = pacman.x_old and (pacman.y = pacman.y_old or pacman.y = pacman.y_old + 1)
     */
    void moveRIGHT();

    /**
     * move the pacman down
     * @post pacman.y = pacman.y_old and (pacman.x = pacman.x_old or pacman.x = pacman.x_old + 1)
     */
    void moveDOWN();

    /**
     * move the pacman left
     * @post pacman.x = pacman.x_old and (pacman.y = pacman.y_old or pacman.y = pacman.y_old - 1)
     */
    void moveLEFT();

    /**
     * Getter for score of pacman
     * @return value[|address] of score
     */
    int getScore();

    /**
     * Getter for highest score
     * @return value[|address] of highest score
     */
    int getHighestScore();
    
    /**
     * Getter for direction
     * @return value of the direction
     */
    int getDirection();

    /**
     * Setter for direction attribute
     * @param direction new direction (1 = UP / 2 = RIGHT / 3 = DOWN / 4 = LEFT)
     */
    void setDirection(int direction);
    
    /**
     * Get a 2D array representing the maze (the current level)
     * Values contained :
     *  - 0 WallImpl
     *  - 1 Gomme on CorridorImpl
     *  - 2 Fruit on CorridorImpl
     *  - 3 GhostDoorImpl
     *  - 4 Empty CorridorImpl
     *  - 4 (default)
     * 
     * @return 2D array containing int values corresponding to case type/entity.ies
     */
    int[][] getMaze();

    /**
     * Called by view to do one "turn" :
     * - Move PacMan in the required direction
     * - Check if PacMan hit any other entity
     * - Move Ghosts
     * - Check if PacMan hit any other entity again
     * 
     * @param direction direction in which to move PacMan
     */
    void compute(int direction);
    
    /**
     * Get x coordinate of PacMan
     * @return x coordinate of PacMan
     */
    int getXPacMan ();
   
    /**
     * Get y coordinate of PacMan
     * @return y coordinate of PacMan
     */
    int getYPacMan ();
    
    /**
     * Get PacMan's HP
     * @return PacMan's HP
     */
    int getHpPacMan ();
    
    /**
     * Return true if a level was passed last turn
     * @return true if a level was passed last turn
     */
    boolean isLevelPassed ();
   
    /**
     * Return true if the game is won
     * @return true if the game is won
     */
    boolean isGameWon ();
  
    /**
     * Return true if PacMan has no HP left (PacMan.hp = 0)
     * @return true if PacMan has no HP left (PacMan.hp = 0)
     */
    boolean isPacManDead ();
}
