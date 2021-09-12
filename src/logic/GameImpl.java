package td6.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Implementation of Game interface
 */
public class GameImpl implements Game {
    /**
     * Store current game data (levels, entities positions, ...)
     */
    private td6.data.GameImpl gameData;

    /**
     * Level interface implementation to do logic on current level
     */
    private LevelImpl levelLogic;

    /**
     * indicate if a level was passed during this turn
     * 0 if no level was passed
     * > 0 if level was passed during this turn
     */
    private int levelPassed = 0;

    /**
     * true if the game is won
     */
    private boolean gameWon = false;
    
    /**
     * Initialize all attributes to play a game
     * 
     * Steps :
     *  - Read content of levelsFilePath
     *  - Split the read content into chunks, each chunk containing infos about a level (ordered)
     *  - Create the levels with the attributes
     *  - Create the other important attributes to create/fill Data/GameImpl (PacMan, ...)
     *  - Read content of highestScoreFilePath
     *  - Extract highest score information from the read file
     *  - Initialize logic/GameImpl with required attributes
     * 
     * @param levelsFilePath path (absolute or relative) to a file containing levels (see data/ for structure)
     * @param highestScoreFilePath path (absolute or relative) to a file containing highestScore (positive integer on the first line)
     */
    public GameImpl (String levelsFilePath, String highestScoreFilePath)
    {
        // Read file containing data (levels informations)
        BufferedReader br;
        ArrayList<String> lines = new ArrayList<String>(30);
        try {
            InputStream in = getClass().getResourceAsStream(levelsFilePath);
            br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Create one ArrayList of String for each level
        // Get the data intervals for each level
        ArrayList<Integer> separatorsIndexes = this.getLevelSeparatorsIndexes(lines);
        ArrayList<ArrayList<String>> levelsLines = new ArrayList<ArrayList<String>>(separatorsIndexes.size());

        // split main arraylist of String to one sub list for each level
        separatorsIndexes.add(0, -1);
        separatorsIndexes.add(lines.size());
        for (int i = 0; i < separatorsIndexes.size()-1; i++) {
            levelsLines.add(new ArrayList<String>(lines.subList(separatorsIndexes.get(i)+1, separatorsIndexes.get(i+1))));
        }

        // Create Level (data) objects
        ArrayList<td6.data.LevelImpl> levels = new ArrayList<td6.data.LevelImpl>();
        int cursorLevel = 0;
        for (ArrayList<String> levelLine : levelsLines) {
            // Get PacMan spawn infos
            int xSpawnPacMan = Integer.parseInt(levelLine.get(0).split(";")[0]);
            int ySpawnPacMan = Integer.parseInt(levelLine.get(0).split(";")[1]);
            
            // Get Ghost respawn infos
            int xSpawnGhost = Integer.parseInt(levelLine.get(1).split(";")[0]);
            int ySpawnGhost = Integer.parseInt(levelLine.get(1).split(";")[1]);
            int spawnTime = Integer.parseInt(levelLine.get(2));
            
            // Create Cases array (maze)
            int height = levelLine.size()-3; // remove three lines of header
            int width = levelLine.get(3).split(" ").length;
            td6.data.CaseImpl[][] casesArray = new td6.data.CaseImpl[height][width];

            ArrayList<td6.data.GhostImpl> levelGhosts = new ArrayList<td6.data.GhostImpl>();

            // Read each line and create the right Case (with the right Entity if needed)
            int timeToSpawnGhosts = spawnTime*1;
            for (int i = 3; i < levelLine.size(); i++) {

                // For a given line
                String[] lineSplit = levelLine.get(i).split(" ");
                int j = 0;

                // For each "info" (group of two letters, like "WW" or "SG" for example)
                for (String s : lineSplit) {
                    if (s.equals("WW")) {  // Wall
                        casesArray[i-3][j] = new td6.data.WallImpl(i-3, j);

                    } else if (s.equals("CG")) { // Corridor WITH a Gomme
                        casesArray[i-3][j] = new td6.data.CorridorImpl(i-3, j, false);
                        td6.data.GommeImpl gomme = new td6.data.GommeImpl(i-3, j, 100);
                        ((td6.data.CorridorImpl)(casesArray[i-3][j])).addEntity(gomme);

                    } else if (s.equals("SP") || s.equals("CV")) { // Empty Corridor
                        casesArray[i-3][j] = new td6.data.CorridorImpl(i-3, j, false);

                    } else if (s.equals("SG")) { // Corridor with Ghost
                        casesArray[i-3][j] = new td6.data.CorridorImpl(i-3, j, false);
                        td6.data.GhostImpl ghost = new td6.data.GhostImpl(i-3, j, 100, timeToSpawnGhosts, new Color(255, 127, 127));
                        ((td6.data.CorridorImpl)casesArray[i-3][j]).addEntity(ghost);
                        levelGhosts.add(ghost);
                        timeToSpawnGhosts += 5;

                    } else if (s.equals("C1") || s.equals("C2") || s.equals("C3")) { // Corridor with Fruit
                        casesArray[i-3][j] = new td6.data.CorridorImpl(i-3, j, false);

                        int fruitScore = -1;
                        if (cursorLevel == 0) {
                            fruitScore = 100;
                        } else if (cursorLevel == 1) {
                            fruitScore = 300;
                        } else if (cursorLevel == 2 || cursorLevel == 3) {
                            fruitScore = 500;
                        } else if (cursorLevel == 4 || cursorLevel == 5) {
                            fruitScore = 700;
                        } else if (cursorLevel == 6 || cursorLevel == 7) {
                            fruitScore = 1000;
                        } else if (cursorLevel == 8 || cursorLevel == 9) {
                            fruitScore = 2000;
                        } else if (cursorLevel == 10 || cursorLevel == 11) {
                            fruitScore = 3000;
                        } else if (cursorLevel >= 12) {
                            fruitScore = 5000;
                        }

                        td6.data.FruitImpl fruit = new td6.data.FruitImpl(i-3, j, s, fruitScore);
                        ((td6.data.CorridorImpl)casesArray[i-3][j]).addEntity(fruit);

                    } else if (s.equals("GD")) { // GhostDoor
                        casesArray[i-3][j] = new td6.data.CorridorImpl(i-3, j, true);
                        
                    } else {
                        System.err.println("[ERROR - levels.txt] x = "+(i-3)+" ; y = "+j);
                    }
                    j++;
                }
            }

            // Create Level (data) object with all informations
            td6.data.LevelImpl oneLevel = new td6.data.LevelImpl(xSpawnGhost, ySpawnGhost, xSpawnPacMan, ySpawnPacMan, spawnTime, casesArray, levelGhosts);
            
            // Add created Level (data) to list of Levels (data)
            levels.add(oneLevel);

            cursorLevel++;
        }

        td6.data.PacManImpl pacman = new td6.data.PacManImpl(levels.get(0).getXSpawnPacMan(), levels.get(0).getYSpawnPacMan(), 3, 0);
        // int x, int y, int hp, int timeToEat, boolean canEat
        this.gameData = new td6.data.GameImpl(-1, pacman, levels);
        // int score, PacMan pm, ArrayList<Level> levels

        // Init first level : setLevel + add PacMan on its Corridor
        td6.data.LevelImpl level0 = levels.get(0);
        this.gameData.setLevel(level0);
        this.gameData.getPacMan().setX(level0.getXSpawnPacMan());
        this.gameData.getPacMan().setY(level0.getYSpawnPacMan());

        this.levelLogic = new LevelImpl(level0);

        // Add PacMan on its Corridor
        td6.data.CorridorImpl pmCorridor = (td6.data.CorridorImpl)this.gameData.getLevel().getCase(level0.getXSpawnPacMan(), level0.getYSpawnPacMan());
        pmCorridor.addEntity(pacman);



        // Read file containing highest score data [HS = HighestScore]
        BufferedReader brHS;
        ArrayList<String> linesHS = new ArrayList<String>(30);
        try {
            InputStream in = getClass().getResourceAsStream(highestScoreFilePath);
            brHS = new BufferedReader(new InputStreamReader(in));
            String lineHS = brHS.readLine();
            while (lineHS != null) {
                linesHS.add(lineHS);
                lineHS = brHS.readLine();
            }
            brHS.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        this.gameData.setHighestScore(Integer.parseInt(linesHS.get(0)));
    }

    /**
     * Help to process the file containing levels by finding where each level informations
     * starts/ends
     * 
     * @param lines ArrayList of String, contains the content of the read file containing levels
     * @return ArrayList<Integer> contains where the "-----" lines are
     */
    private ArrayList<Integer> getLevelSeparatorsIndexes(ArrayList<String> lines)
    {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("-----")) {
                ret.add(i);
            }
        }
        return ret;
    }


     /**
     * Move PacMan in the right direction
     * Check
     * Move Ghosts
     * Check
     * Change PacMan coords
     * 
     * @param direction 1 = UP ; 2 = RIGHT ; 3 = DOWN ; 4 = LEFT
     *                  default to 0, if zero pacman doesn't move
     */
    public void compute (int direction)
    {
        if (direction == 0
            || direction == 1 && this.gameData.getLevel().getCase(this.getXPacMan() -1, this.getYPacMan()) instanceof td6.data.WallImpl
            || direction == 2 && this.gameData.getLevel().getCase(this.getXPacMan(), this.getYPacMan() + 1) instanceof td6.data.WallImpl
            || direction == 3 && this.gameData.getLevel().getCase(this.getXPacMan() + 1, this.getYPacMan()) instanceof td6.data.WallImpl
            || direction == 4 && this.gameData.getLevel().getCase(this.getXPacMan(),this.getYPacMan() - 1) instanceof td6.data.WallImpl) {
                direction = this.gameData.getDirection();
        }
    	

    	// Move PacMan (check if it is not a wall (also checked before for a wrong new direction) )
        // 1 = UP ; 2 = RIGHT ; 3 = DOWN ; 4 = LEFT
    	if( direction == 1 && this.gameData.getLevel().getCase(this.getXPacMan()-1, this.getYPacMan()) != null && !(this.gameData.getLevel().getCase(this.getXPacMan()-1, this.getYPacMan()) instanceof td6.data.WallImpl)
            && !((td6.data.CorridorImpl)this.gameData.getLevel().getCase(this.getXPacMan()-1, this.getYPacMan())).isGhostDoor()) {

			this.moveUP(); 
            this.gameData.setDirection(1); 
		} else if(direction == 2 && this.gameData.getLevel().getCase(this.getXPacMan(), this.getYPacMan()+1) != null && ! (this.gameData.getLevel().getCase(this.getXPacMan(), this.getYPacMan()+1) instanceof td6.data.WallImpl)
            && !((td6.data.CorridorImpl)this.gameData.getLevel().getCase(this.getXPacMan(), this.getYPacMan()+1)).isGhostDoor()) {

    		this.moveRIGHT();
    		this.gameData.setDirection(2);  
    	} else if(direction == 3 && this.gameData.getLevel().getCase(this.getXPacMan()+1, this.getYPacMan()) != null && ! (this.gameData.getLevel().getCase(this.getXPacMan()+1, this.getYPacMan()) instanceof td6.data.WallImpl)
            && !((td6.data.CorridorImpl)this.gameData.getLevel().getCase(this.getXPacMan()+1, this.getYPacMan())).isGhostDoor()) {

    		this.moveDOWN();
    	    this.gameData.setDirection(3); 
    	} else if(direction == 4 && this.gameData.getLevel().getCase(this.getXPacMan(), this.getYPacMan()-1) != null && ! (this.gameData.getLevel().getCase(this.getXPacMan(),this.getYPacMan()-1) instanceof td6.data.WallImpl)
            && !((td6.data.CorridorImpl)this.gameData.getLevel().getCase(this.getXPacMan(), this.getYPacMan()-1)).isGhostDoor()) {

    		this.moveLEFT();
    		this.gameData.setDirection(4);
    	}

        // First check
        this.check();

        // Move Ghosts
        this.levelLogic.moveGhosts(this.gameData.getPacMan().getX(), this.gameData.getPacMan().getY(), this.gameData.getPacMan().getCanEat());

        // Second check (does a ghost eat PacMan)
        this.check();

        if (this.isLevelPassed()) {
            this.levelLogic = new LevelImpl(this.gameData.getLevel());
        }        
    }

    /**
     * Check if PacMan hits any entities on a Corridor
     * Also check if the level is finished, if true then call nextLevel()
     * And also check if the game is lost or won
     */
    private void check ()
    {
        if (this.levelPassed > 0) this.levelPassed--;

        td6.data.PacMan pacman = this.gameData.getPacMan();
        td6.data.LevelImpl level = this.gameData.getLevel();
        td6.data.CorridorImpl pacmanCorridor = (td6.data.CorridorImpl)level.getCase(pacman.getX(), pacman.getY());

        ArrayList<td6.data.Entity> entities = pacmanCorridor.getEntities();

        ArrayList<td6.data.Entity> entitiesCopy = new ArrayList<td6.data.Entity>(entities);

        for (td6.data.Entity entity : entitiesCopy) {
            if (entity instanceof td6.data.FruitImpl) { // eat Fruit
                td6.data.FruitImpl e = (td6.data.FruitImpl)entity;
                this.gameData.setScore(this.gameData.getScore() + e.getPoints());
                entities.remove(e);
                pacman.setTimeToEat(20*2); // invincible during 6 ticks
            } else if (entity instanceof td6.data.GommeImpl) { // eat Gomme
                td6.data.GommeImpl e = (td6.data.GommeImpl)entity;
                this.gameData.setScore(this.gameData.getScore() + e.getPoints());
                entities.remove(e);

                if (this.levelLogic.isLevelFinished() && this.levelPassed == 0) {
                    this.nextLevel();
                    this.levelPassed = 2;
                }

            } else if (entity instanceof td6.data.GhostImpl) {
                td6.data.GhostImpl e = (td6.data.GhostImpl)entity;
                if (pacman.getTimeToEat() > 0) { // PacMan invincible
                    this.levelLogic.respawnGhost(e);
                } else { 
                    if (pacman.getHP() > 0) {
                        pacman.setHP(pacman.getHP()-1);
                        this.levelLogic.respawnPacMan(pacman); // also respawn ghosts
                    }
                }
            }
        }

        // Change HighestScore to current score if second one is higher
        if (this.gameData.getScore() > this.gameData.getHighestScore()) {
            this.gameData.setHighestScore(this.gameData.getScore());
        }

        if (pacman.getTimeToEat() > 0) pacman.setTimeToEat(pacman.getTimeToEat()-1);
    }

    /**
     * Apply a movement to PacMan
     *  - Changes PacMan x and y attributes values
     *  - Manage the behavior of Corridors to keep the game in a coherent state
     * 
     * @param dx x movement
     * @param dy y movement
     */
    private void movePacMan (int dx, int dy)
    {
        td6.data.PacMan pacman = this.gameData.getPacMan();
        td6.data.LevelImpl level = this.gameData.getLevel();
        td6.data.CaseImpl destCase = level.getCase(pacman.getX()+dx, pacman.getY()+dy);
        
        if (destCase instanceof td6.data.CorridorImpl) {
            td6.data.CorridorImpl oldCorridor = (td6.data.CorridorImpl)level.getCase(pacman.getX(), pacman.getY());
            
            // have to get the Case (Corridor actually) again because of static type change
            td6.data.CorridorImpl destCorridor = (td6.data.CorridorImpl)level.getCase(pacman.getX()+dx, pacman.getY()+dy); 

            pacman.setX(pacman.getX()+dx);
            pacman.setY(pacman.getY()+dy);

            oldCorridor.removeEntity(pacman);
            destCorridor.addEntity(pacman);
        }
    }

    /**
     * Move PacMan UP one case
     */
    public void moveUP ()
    {
        this.movePacMan(-1, 0);
    }

    /**
     * Move PacMan DOWN one case
     */
    public void moveDOWN ()
    {
        this.movePacMan(1, 0);
    }

    /**
     * Move PacMan LEFT one case
     */
    public void moveLEFT ()
    {
        this.movePacMan(0, -1);
    }

    /**
     * Move PacMan RIGHT one case
     */
    public void moveRIGHT ()
    {
        this.movePacMan(0, 1);
    }

    /**
     * Get the current level index
     * 0 if first level, 1 if second, ...
     */
    private int getCurrentLevelIndex() {
        td6.data.LevelImpl currentLevel = this.gameData.getLevel();
        ArrayList<td6.data.LevelImpl> levels = this.gameData.getLevels();
        return levels.indexOf(currentLevel);
    }

    /**
     * Changes the level to next one
     * Manage the attributes of the objects to keep the game in a stable state 
     */
    private void nextLevel ()
    {
        int currentLevelIndex = -1;
        ArrayList<td6.data.LevelImpl> levels = this.gameData.getLevels();

        currentLevelIndex = this.getCurrentLevelIndex();

        if (currentLevelIndex == levels.size()-1) {
            this.gameWon = true;
        } else {
            td6.data.LevelImpl nextLevel = levels.get(currentLevelIndex+1);
            this.gameData.setLevel(nextLevel);
            td6.data.PacMan pacman = this.gameData.getPacMan();
            pacman.setX(nextLevel.getXSpawnPacMan());
            pacman.setY(nextLevel.getYSpawnPacMan());
            td6.data.CorridorImpl pacmanNewSpawn = (td6.data.CorridorImpl)nextLevel.getCase(nextLevel.getXSpawnPacMan(), nextLevel.getYSpawnPacMan());
            pacmanNewSpawn.addEntity(pacman);
            pacman.setTimeToEat(0);
        }
    }

    /**
     * Return true if a level was passed this turn
     * 
     * @return the value of the levelPassed attribute (true if a level was passed)
     */
    public boolean isLevelPassed ()
    {
        return (this.levelPassed > 0);
    }

    /**
     * Getter for gameWon attribute
     * Return true if the game is won
     * 
     * @return value of gameWon attribute (true if the game is won)
     */
    public boolean isGameWon ()
    {
        return this.gameWon;
    }

    /**
     * Return true if PacMan is dead
     * 
     * @return true if PacMan is dead
     */
    public boolean isPacManDead ()
    {
        // If this comment isn't removed, consider that it was checked
        return (this.gameData.getPacMan().getHP() == 0); 
    }

    /**
     * Gets PacMan direction
     * 
     * @return PacMan's direction
     */
    public int getDirection ()
    {
        return this.gameData.getDirection();
    }

    /**
     * Set PacMan direction
     * 
     * @param direction new PacMan direction
     */
    public void setDirection(int direction)
    {
        this.gameData.setDirection(direction);
    }

    /**
     * Get highestScore of the player
     * 
     * @return highestScore of the player
     */
    public int getHighestScore ()
    {
        return this.gameData.getHighestScore();
    }

    /**
     * Get current score
     * 
     * @return current score
     */
    public int getScore ()
    {
        return this.gameData.getScore();
    }

    /**
     * Get PacMan's x position
     * 
     * @return PacMan's x position
     */
    public int getXPacMan ()
    {
        return this.gameData.getPacMan().getX();
    }

    /**
     * Get PacMan's y position
     * 
     * @return PacMan's y position
     */
    public int getYPacMan ()
    {
        return this.gameData.getPacMan().getY();
    }
    
    /**
     * Get PacMan's HP value
     * 
     * @return PacMan's HP
     */
    public int getHpPacMan ()
    {
        return this.gameData.getPacMan().getHP();
    }

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
    public int[][] getMaze()
    {
    	td6.data.CaseImpl[][] mazeCase = this.gameData.getLevel().getMaze();
       	int[][] mazeInt = new int[mazeCase.length][mazeCase[0].length];
    	
    	for(int j = 0; j < mazeCase.length; j++)
    	{
       		for(int i = 0; i < mazeCase[j].length; i++)
       		{
       			if(mazeCase[j][i] instanceof td6.data.WallImpl) // wall
       			{ 
       	   			mazeInt[j][i] = 0; 
				}
       			else if(mazeCase[j][i] instanceof td6.data.CorridorImpl && ((td6.data.CorridorImpl)mazeCase[j][i]).isGhostDoor()) // ghost door
				{
       				mazeInt[j][i] = 3;
			    } 
       			else if(mazeCase[j][i] instanceof td6.data.CorridorImpl) // case containing nothing/fruit/gomme
       			{
       				td6.data.CorridorImpl corridor = (td6.data.CorridorImpl) mazeCase[j][i];
       	   			if(corridor.getEntities().size() == 0)  // nothing
       	   			{
       	   				mazeInt[j][i] = 4;
       	   			}
       	   			else
       	   			{
	       	   			for(td6.data.Entity entity : corridor.getEntities()) // fruit
	       	   			{
		       	   			if(entity instanceof td6.data.FruitImpl)
		       	   			{ 
       	   					mazeInt[j][i] = 2;
		   	   				}
		       	   			else if(entity instanceof td6.data.GommeImpl) // gomme
		       	   		    {
		       	   				mazeInt[j][i] = 1; 
	       	   				}
		       	   			else // nothing
		       	   			{
	       	   					mazeInt[j][i] = 4; 
							}
	       	   			}
					}
       	   		}
       			else // nothing
       			{
       	   			mazeInt[j][i] = 4; 
       	   		}
       		}
       	}
    	
    	return mazeInt;
    }
    
    /**
     * Get all ghosts positions in a 2D array
     * int[i] contains the position of a Ghost
     * int[i][0] is the x position
     * int[i][1] is the y position
     * 
     * @return 2D array containing ghosts positions
     */
    public int[][] getGhostsPosition()
    {
        ArrayList<td6.data.GhostImpl> ghosts = this.gameData.getLevel().getGhosts();    
        int[][] ret = new int[ghosts.size()][2];

        for (int i = 0; i < ret.length; i++) {
            ret[i][0] = ghosts.get(i).getX();
            ret[i][1] = ghosts.get(i).getY();
        }

        return ret;
    }

    /**
     * Return true if ghosts can be eaten by PacMan
     * 
     * @return true if ghosts can be eaten by PacMan
     */
    public boolean areGhostsEatable()
    {
        return this.gameData.getPacMan().getCanEat();
    }
}
