package td6.data;

import td6.data.Case;
import java.util.ArrayList;

/**
*
* implementation of level which allows to have and modify data about a level
*
* @inv for ( Case c : boardCases ) { c.typeof(Wall) || c.typeof(Corridor) || c.typeof(GhostDoor);}
*/
public class LevelImpl implements Level{
	//Coordinates x and y of spawn pacman
	private int xSpawnPacMan;
	private int ySpawnPacMan;
	//The respawn time
	private int time;
	//The maze
	private CaseImpl tableau[][];
	//List of ghost in the level
	private ArrayList<GhostImpl> listeGhosts;

	/**
	 * Constructor of a new level
	 * @param xSpawnPacMan the x coordinate of spawn pacman
	 * @param ySpawnPacMan the y coordinate of spawn pacman
	 * @param time the respawn time
	 * @param tableau the maze
	 * @param listeGhosts the list of ghosts in the level
	 */
	public LevelImpl(int xSpawnGhost, int ySpawnGhost, int xSpawnPacMan, int ySpawnPacMan, int time, CaseImpl[][] tableau, ArrayList<GhostImpl> listeGhosts){
		this.xSpawnPacMan = xSpawnPacMan;
		this.ySpawnPacMan = ySpawnPacMan;
		this.time = time;
		this.tableau = tableau;
		this.listeGhosts = listeGhosts;
	} 

	/**
	 *  returns the x position of the spawn of pacman on the board
	 *  
	 *  @return the x position of the spawn of pacman on the board
	 */
	public int getXSpawnPacMan(){
		return this.xSpawnPacMan;
	}

	/**
	 *  returns the y position of the spawn of pacman on the board
	 *  
	 *  @return the y position of the spawn of pacman on the board
	 */
	public int getYSpawnPacMan(){
		return this.ySpawnPacMan;
	}

	/**
	 *  returns the time of spawn of ghosts on the board
	 *  
	 *  @return the time of spawn of ghosts on the board
	 */
	public int getSpawnTime(){
		return this.time;
	}

	/**
	 *  returns the maze
	 *  
	 *  @return tableau of Case representing our maze
	 */ 
	public CaseImpl[][] getMaze(){
		invariant();
		return this.tableau;
	}

	/**
	 *  Getter for a case
	 *
	 *  @return tableau[x][y] containing our case
	 */
	public CaseImpl getCase(int x, int y){
		invariant();
		if (x < 0 || y < 0 || x >= this.tableau.length || y >= this.tableau[0].length) {
			return null;
		} else {
			return this.tableau[x][y];
		}
	}

	/**
	 * The invariant of the class level
	 */
	private void invariant(){
		int N = this.tableau.length;
		int M = this.tableau[0].length;
		for (int i = 0; i<N; i++){
    		for (int j = 0; j<M; j++){
    			CaseImpl c = this.tableau[i][j];
        		assert c.getX() == i && c.getY() == j:"Erreur invariant";
			}
		}
	}

	/**
	 * Getter to have a list of ghosts
	 * @return a liste of ghost
	 */
	public ArrayList<GhostImpl> getGhosts(){
		return this.listeGhosts;
	}

	/**
     * toString method 
     * @return It will repsent the differents objects with their color on the level on the terminal for the debug.
     * It will be usefull to enderstand how work our programme.
     */
	public String toString()
	{
		String ret = "";
		int N = this.tableau.length;
		int M = this.tableau[0].length;
		for (int i = 0; i<N; i++){
    		for (int j = 0; j<M; j++){
    			ret += this.tableau[i][j].toString();
			}
			ret += "\n";
		}
		return ret;
	}
}
