package td6.data;
import java.awt.Color;

/**
*
* interface of ghost which allow to have and modify data about a ghost
*
* @inv corridor.x == x and corridor.y == y
*/
public interface Ghost extends Entity {

  	/**
	* returns the amount of points that we add to the score when a ghost is eaten
	*
	* @return the amount of points of a ghost
	*
	*/
  	int getPoints();

  	/**
	* returns the time remaining to spawn a ghost
	*
	* @return the time remaining to spawn a ghost
	*
	*/
  	int getTimeToSpawn();

  	/**
	* sets remaining time to spawn of the ghost on the board
	*
	* @param timeToSpawnGhost  time remaining before spawn of the ghost
	*
	* @pre timeToSpawnGhost >= 0
	* @post timeToSpawn == timeToSpawnGhost
	*/
  	void setTimeToSpawn(int timeToSpawnGhost);

  	/**
  	 * Setter for the golor of the ghost
  	 * @param color the color of the ghost
  	 */
  	void setColor(Color color);

  	/**
  	 * Getter for the color of the ghost
  	 * @return color the color of the ghost
  	 */
  	Color getColor();

    /**
     * Getter for the coordinates of ghost respawn
     * @return xSpawn the x coordinate
     */
    int getXSpawn();

    /**
     * Getter for the coordinates of ghost respawn
     * @return ySpawn the y coordinate
     */
    int getYSpawn();

  	/**
     * toString method 
     * @return It will put a purple background behind the object ghost on the terminal for the debug
     */
  	String toString();

}
