package td6.logic;

/**
 * Interface representing a Level
 */
public interface Level {
    /**
     * moves all ghosts on the level (calls moveGhost on each one)
     * @param xPacMan x position of PacMan on the board
     * @param yPacMan y position of PacMan on the board
     */
    void moveGhosts(int xPacMan, int yPacMan, boolean pmCanEat);

    /**
     * Move a ghost on the board searching for the best move to do 
     * (calculate distance between the coordinates between the Ghost and PacMan position)
     * @param ghost ghost to move
     * @param xPacMan x position of PacMan on the board
     * @param yPacMan y position of PacMan on the board
     * 
     * @post (ghost.x@pre - xPacMan)*(ghost.x@pre - xPacMan) + (ghost.y@pre - yPacMan)*(ghost.y@pre - yPacMan) >= (ghost.x - xPacMan)*(ghost.x - xPacMan) + (ghost.y - yPacMan)*(ghost.y - yPacMan)
     */
    void moveGhost(td6.data.Ghost ghost, int xPacMan, int yPacMan, boolean pmCanEat);

    /**
     * Check if a Case at (x, y) coordinates on the board is of type Corridor
     * @param x x position of the Case on the Board
     * @param y y position of the Case on the Board
     * @return true if the Case is of Corridor type
     * 
     * @pre x >= 0 and y >= 0
     * @post (result = true or result = false)
     */
    boolean isCorridor(int x, int y);

    /**
     * respawn a Ghost at the defined position by the level (xSpawnGhost, ySpawnGhost)
     * Can also initialize the timeToSpawn of the Ghost if different from zero
     * @param ghost ghost to respawn
     * 
     * @post ghost.x = self.xSpawnGhost and ghost.y = self.ySpawnGhost
     */
    void respawnGhost(td6.data.Ghost ghost);

    /**
     * respawn PacMan at the defined position by the level (xSpawnPacman, ySpawnPacman)
     * Can also respawn all the ghost
     * @param pacman pacman to respawn
     * 
     * @post pacman.x = self.xSpawnPacMan and pacman.y = self.ySpawnPacMan
     */
    void respawnPacMan(td6.data.PacMan pacman);

    boolean isLevelFinished();
}
