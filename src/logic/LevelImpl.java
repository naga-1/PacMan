package td6.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Level interface implementation
 * 
 * Handles the logic on a level
 */
public class LevelImpl implements Level {
    /**
     * Handled level data
     */
    private td6.data.Level levelData;

    /**
     * Initialize attribute
     * 
     * @param levelData levelData to manage
     */
    public LevelImpl (td6.data.Level levelData)
    {
        this.levelData = levelData;
    }

    /**
     * move all ghosts in the best direction to eat PacMan
     * 
     * @param xPacMan PacMan's x position
     * @param yPacMan PacMan's y position
     */
    public void moveGhosts (int xPacMan, int yPacMan, boolean pmCanEat)
    {
        for (td6.data.Ghost g : this.levelData.getGhosts()) {
            this.moveGhost(g, xPacMan, yPacMan, pmCanEat);
        }
    }

    /**
     * move one ghost in the best direction to eat PacMan
     * 
     * @param ghost ghost to move
     * @param xPacMan PacMan's x position
     * @param yPacMan PacMan's y position
     */
    public void moveGhost (td6.data.Ghost ghost, int xPacMan, int yPacMan, boolean pmCanEat)
    {
        if (ghost.getTimeToSpawn() > 0) {
            ghost.setTimeToSpawn(ghost.getTimeToSpawn()-1);
            return;
        }
        int dx = 0;
        int dy = 0;

        int dxMax = 0;
        int dyMax = 0;

        int min = (ghost.getX() - xPacMan)*(ghost.getX() - xPacMan) + (ghost.getY() - yPacMan)*(ghost.getY() - yPacMan);
        int max = (ghost.getX() - xPacMan)*(ghost.getX() - xPacMan) + (ghost.getY() - yPacMan)*(ghost.getY() - yPacMan);
        
        int d;

        // up
        if (this.levelData.getCase(ghost.getX()+1, ghost.getY()) instanceof td6.data.CorridorImpl || this.levelData.getCase(ghost.getX()+1, ghost.getY()) instanceof td6.data.GhostDoorImpl) {
            d = (ghost.getX() + 1 - xPacMan)*(ghost.getX() + 1 - xPacMan) + (ghost.getY() - yPacMan)*(ghost.getY() - yPacMan);
            if  (d < min) {
                dx = 1;
                dy = 0; 
                min = d;
            }
            if (d > max) {
                dxMax = 1;
                dyMax = 0;
                max = d;
            }
        }

        // down
        if (this.levelData.getCase(ghost.getX()-1, ghost.getY()) instanceof td6.data.CorridorImpl || this.levelData.getCase(ghost.getX()-1, ghost.getY()) instanceof td6.data.GhostDoorImpl) {
            d = (ghost.getX() - 1 - xPacMan)*(ghost.getX() - 1 - xPacMan) + (ghost.getY() - yPacMan)*(ghost.getY() - yPacMan);
            if  (d < min) {
                dx = -1;
                dy = 0;
                min = (ghost.getX() - 1 - xPacMan)*(ghost.getX() - 1 - xPacMan) + (ghost.getY() - yPacMan)*(ghost.getY() - yPacMan);
            }
            if (d > max) {
                dxMax = -1;
                dyMax = 0;
                max = d;
            }
        }

        // right
        if (this.levelData.getCase(ghost.getX(), ghost.getY()+1) instanceof td6.data.CorridorImpl || this.levelData.getCase(ghost.getX(), ghost.getY()+1) instanceof td6.data.GhostDoorImpl) {
            d = (ghost.getX() - xPacMan)*(ghost.getX() - xPacMan) + (ghost.getY() + 1 - yPacMan)*(ghost.getY() + 1 - yPacMan);
            if (d < min) {
                dx = 0;
                dy = 1;
                min = d;
            }
            if (d > max) {
                dxMax = 0;
                dyMax = 1;
                max = d;
            }
        }

        // left
        if (this.levelData.getCase(ghost.getX(), ghost.getY()-1) instanceof td6.data.CorridorImpl || this.levelData.getCase(ghost.getX(), ghost.getY()-1) instanceof td6.data.GhostDoorImpl) {
            d = (ghost.getX() - xPacMan)*(ghost.getX() - xPacMan) + (ghost.getY() - 1 - yPacMan)*(ghost.getY() - 1 - yPacMan);
            if (d < min) {
                dx = 0;
                dy = -1;
                min = d;
            }
            if (d > max) {
                dxMax = 0;
                dyMax = -1;
                max = d;
            }
        }

        int oldX = ghost.getX();
        int oldY = ghost.getY();

        td6.data.CorridorImpl newCase;

        if (pmCanEat) {  // pacman can eat <=> ghost need to be distant
            newCase = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX()+dxMax, ghost.getY()+dyMax);
            if (!corridorContainsAGhost(newCase)) {
                ghost.setX(ghost.getX()+dxMax);
                ghost.setY(ghost.getY()+dyMax);

                td6.data.CorridorImpl oldCase = (td6.data.CorridorImpl)this.levelData.getCase(oldX, oldY);
                
                // Remove ghost from old corridor and add to new one
                // oldCase and newCase can be the same
                oldCase.removeEntity(ghost);
                newCase.addEntity(ghost);
            }
        } else {  // pacman is eatable <=> ghost need to get near to pacman
            if (min <= 4) { // if distance between ghost and pacman <= 4
                newCase = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX()+dx, ghost.getY()+dy);
                if (!corridorContainsAGhost(newCase)) {
                    ghost.setX(ghost.getX()+dx);
                    ghost.setY(ghost.getY()+dy);
                    
                    td6.data.CorridorImpl oldCase = (td6.data.CorridorImpl)this.levelData.getCase(oldX, oldY);
                    
                    // Remove ghost from old corridor and add to new one
                    // oldCase and newCase can be the same
                    oldCase.removeEntity(ghost);
                    newCase.addEntity(ghost);
                }
            } else { // random ghost movement
                this.randomGhostMovement((td6.data.GhostImpl)ghost);
            }
        }
    }

    private void randomGhostMovement(td6.data.GhostImpl ghost)
    {
        ArrayList<td6.data.CorridorImpl> candidates = new ArrayList<td6.data.CorridorImpl>(3);
        // UP
        if (this.levelData.getCase(ghost.getX()-1, ghost.getY()) instanceof td6.data.CorridorImpl) {
            td6.data.CorridorImpl corridor = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX()-1, ghost.getY());
            if (!corridorContainsAGhost(corridor)) {
                candidates.add(corridor);
            }
        }

        // DOWN
        if (this.levelData.getCase(ghost.getX()+1, ghost.getY()) instanceof td6.data.CorridorImpl) {
            td6.data.CorridorImpl corridor = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX()+1, ghost.getY());
            if (!corridorContainsAGhost(corridor)) {
                candidates.add(corridor);
            }
        }

        // LEFT
        if (this.levelData.getCase(ghost.getX(), ghost.getY()-1) instanceof td6.data.CorridorImpl) {
            td6.data.CorridorImpl corridor = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX(), ghost.getY()-1);
            if (!corridorContainsAGhost(corridor)) {
                candidates.add(corridor);
            }
        }

        // RIGHT
        if (this.levelData.getCase(ghost.getX(), ghost.getY()+1) instanceof td6.data.CorridorImpl) {
            td6.data.CorridorImpl corridor = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX(), ghost.getY()+1);
            if (!corridorContainsAGhost(corridor)) {
                candidates.add(corridor);
            }
        }

        if (candidates.size() > 0) {
            Random rand = new Random();
            td6.data.CorridorImpl newCase = candidates.get(rand.nextInt(candidates.size()));
            
            int oldX = ghost.getX();
            int oldY = ghost.getY();

            ghost.setX(newCase.getX());
            ghost.setY(newCase.getY());

            td6.data.CorridorImpl oldCase = (td6.data.CorridorImpl)this.levelData.getCase(oldX, oldY);

            // Remove ghost from old corridor and add to new one
            // oldCase and newCase can be the same
            oldCase.removeEntity(ghost);
            newCase.addEntity(ghost);
        } // else do not move
    }

    private boolean corridorContainsAGhost(td6.data.CorridorImpl corridor)
    {
        for (td6.data.Entity e : corridor.getEntities()) {
            if (e instanceof td6.data.GhostImpl) return true;
        }
        return false;
    }

    /**
     * Get the distance between two coordinates
     * 
     * @param xGhost first position's x
     * @param yGhost first position's y
     * @param xPacMan second position's x
     * @param yPacMan second position's y
     * 
     * @return distance between two coordinates
     */
    private double distance (int xGhost, int yGhost, int xPacMan, int yPacMan)
    {
        double ret = (xGhost - xPacMan)*(xGhost - xPacMan) + (yGhost - yPacMan)*(yGhost - yPacMan);

        return ret;
    }

    /**
     * Return true if a Case is a Corridor at given coordinates
     * 
     * @param x x position of the case to test
     * @param y y position of the case to test
     * 
     * @return true if the case at (x, y) is a Corridor
     */
    public boolean isCorridor(int x, int y)
    {
        td6.data.Case[][] cases = this.levelData.getMaze();
        return (cases[x][y] instanceof td6.data.CorridorImpl);
    }

    /**
     * Respawn a ghost on the level
     * 
     * @param ghost ghost to respawn
     */
    public void respawnGhost (td6.data.Ghost ghost)
    {
        // if the ghost is not on the spawn case => need to move
        if (ghost.getX() != ghost.getXSpawn()
            && ghost.getY() != ghost.getYSpawn()) {
                int oldX = ghost.getX();
                int oldY = ghost.getY();

                ghost.setX(ghost.getXSpawn());
                ghost.setY(ghost.getYSpawn());

                td6.data.CorridorImpl oldCase = (td6.data.CorridorImpl)this.levelData.getCase(oldX, oldY);
                td6.data.CorridorImpl newCase = (td6.data.CorridorImpl)this.levelData.getCase(ghost.getX(), ghost.getY());
                
                // We already know that oldCase and newCase are of type Corridor
                oldCase.removeEntity(ghost);
                newCase.addEntity(ghost);
            }
    }

    /**
     * Respawn PacMan on the level
     * 
     * @param pacman PacMan object to respawn
     */
    public void respawnPacMan (td6.data.PacMan pacman)
    {
        // if the ghost is not on the spawn case => need to move
        if (pacman.getX() != this.levelData.getXSpawnPacMan()
        && pacman.getY() != this.levelData.getYSpawnPacMan()) {
            int oldX = pacman.getX();
            int oldY = pacman.getY();

            pacman.setX(this.levelData.getXSpawnPacMan());
            pacman.setY(this.levelData.getYSpawnPacMan());

            td6.data.CorridorImpl oldCase = (td6.data.CorridorImpl)this.levelData.getCase(oldX, oldY);
            td6.data.CorridorImpl newCase = (td6.data.CorridorImpl)this.levelData.getCase(pacman.getX(), pacman.getY());

            // We already know that oldCase and newCase are of type Corridor
            // remove all entities from oldCase (sometimes a desync problem occur and a ghost stay on the case for no reason)
            ArrayList<td6.data.Entity> oldCaseEntities = new ArrayList<td6.data.Entity>(oldCase.getEntities());
            for (td6.data.Entity e : oldCaseEntities) {
                oldCase.removeEntity(e);
            }
            newCase.addEntity(pacman);
        }

        // multiplier for delay of ghost spawn
        int cnt = 0;
        // respawn all ghosts
        for (td6.data.GhostImpl g : this.levelData.getGhosts()) {
            this.respawnGhost(g);
            g.setTimeToSpawn(cnt*this.levelData.getSpawnTime());
            cnt++;
        }
    }

    /**
     * Return true if there is no Gomme left in the level
     * 
     * @return true if there is no Gomme left in the level
     */
    public boolean isLevelFinished()
    {
        td6.data.Case[][] cases = this.levelData.getMaze();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                if (cases[i][j] instanceof td6.data.CorridorImpl) {
                    for (td6.data.Entity e : ((td6.data.CorridorImpl)cases[i][j]).getEntities()) {
                        if (e instanceof td6.data.GommeImpl) return false;
                    }
                }
            }
        }

        return true;
    }
}
