package td6.data;

/**
*
* Interface Pacman, represent pacman
*
* @inv corridor.x == x and corridor.y == y
*/
public interface PacMan extends Entity {
	
	/**
	 * returns health points of pacman
	 *  
	 * @return hp of pacman
	 */
	int getHP();

	/**
	* returns boolean value to know if pacman can eat a ghost
	*
	* @return boolean value to know if pacman can eat a ghost
	*/
	boolean getCanEat();

	/**
	* returns value of time that pacman can eat a ghost
	*
	* @return boolean value of time that pacman can eat a ghost
	*/
	int getTimeToEat();

	/**
	* sets number of HP of pacman
	*
	* @param healthPoints number of hp of pacman
	*
	* @pre hp >= 0
	* @post hp == healthPoints
	*/
	void setHP(int healthPoints);

	/**
	* sets value of time that pacman can eat a ghos
	*
	* @param time   value of time that pacman can eat a ghost
	*
	* @pre time >= 0
	* @post timeToEat == time
	*/
	void setTimeToEat(int time);

	/**
     * toString method 
     * @return It will put a yellow background behind the object pacman on the terminal for the debug
     */
	String toString ();
}
