package td6.data;

import td6.data.Case;
import java.util.ArrayList;

/**
*
* interface of level which allows to have and modify data about a level
*
* @inv for ( Case c : boardCases ) { c.typeof(Wall) || c.typeof(Corridor) || c.typeof(GhostDoor);}
*/
public interface Level {

	/**
	 *  returns the x position of the spawn of pacman on the board
	 *  
	 *  @return the x position of the spawn of pacman on the board
	 */
	int getXSpawnPacMan();

	/**
	 *  returns the y position of the spawn of pacman on the board
	 *  
	 *  @return the y position of the spawn of pacman on the board
	 */
	int getYSpawnPacMan();

	/**
	 *  returns the time of spawn of ghosts on the board
	 *  
	 *  @return the time of spawn of ghosts on the board
	 */
	int getSpawnTime();

	/**
	 *  returns the maze
	 *  
	 *  @return tableau of Case representing our maze
	 */ 
	Case[][] getMaze();

	/**
	 *  Getter for a case
	 *
	 *  @return tableau[x][y] containing our case
	 */
	Case getCase(int x, int y);

	/**
	 * Getter to have a list of ghosts
	 * @return a liste of ghost
	 */
	ArrayList<GhostImpl> getGhosts();

	/**
     * toString method 
     * @return It will repsent the differents objects with their color on the level on the terminal for the debug.
     * It will be usefull to enderstand how work our programme.
     */
	String toString();
}
