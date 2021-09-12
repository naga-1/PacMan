package td6.data;
import java.awt.Color;
/**
*
* Implementation of ghost which allows to have and modify data about a ghost
*
* @inv corridor.x == x and corridor.y == y
*/
public class GhostImpl extends EntityAbstract implements Ghost {
	//Points of the ghost if he is eaten
	private int points;
	//Time to spawn of the ghost
	private int timeToSpawn;
	//The color of the ghost
	private Color color;
	//The coordinates where the ghost has to respawn
	private int xSpawn;
	private int ySpawn;

	/**
	 * Constructor of new ghost
	 * @param x the x coordinate of the ghost
	 * @param y the y coordinate of the ghost
	 * @param points the value in points of the ghost if he is eaten
	 * @param timeToSpawn the time wich the ghost has to wait before spawn
	 */
	public GhostImpl(int x, int y, int points, int timeToSpawn, Color color){
		super(x, y);
		this.points = points;
		this.timeToSpawn = timeToSpawn;
		this.color = color;
		this.xSpawn = x;
		this.ySpawn = y;
	}

  	/**
	* returns the amount of points that we add to the score when a ghost is eaten
	*
	* @return the amount of points of a ghost
	*
	*/
  	public int getPoints(){
  		return this.points;
  	}

  	/**
	* returns the time remaining to spawn a ghost
	*
	* @return the time remaining to spawn a ghost
	*
	*/
  	public int getTimeToSpawn(){
  		return this.timeToSpawn;
  	}

  	/**
	* sets remaining time to spawn of the ghost on the board
	*
	* @param timeToSpawnGhost  time remaining before spawn of the ghost
	*
	* @pre timeToSpawnGhost >= 0
	* @post timeToSpawn == timeToSpawnGhost
	*/
  	public void setTimeToSpawn(int timeToSpawnGhost){
  		assert timeToSpawnGhost >=0:"Precondition violated";
  		this.timeToSpawn=timeToSpawnGhost;
  		assert timeToSpawn == timeToSpawnGhost:"Postcondition violated";
  	}

  	/**
  	 * Setter for the golor of the ghost
  	 * @param color the color of the ghost
  	 */
  	public void setColor(Color color){
  		this.color = color;
  	}

  	/**
  	 * Getter for the color of the ghost
  	 * @return color the color of the ghost
  	 */
  	public Color getColor(){
  		return this.color;
  	}

  	/**
  	 * Getter for the coordinates of ghost respawn
  	 * @return xSpawn the x coordinate
  	 */
  	public int getXSpawn(){
  		return this.xSpawn;
  	}

  	/**
  	 * Getter for the coordinates of ghost respawn
  	 * @return ySpawn the y coordinate
  	 */
  	public int getYSpawn(){
  		return this.ySpawn;
  	}

  	/**
     * toString method 
     * @return It will put a purple background behind the object ghost on the terminal for the debug
     */
	public String toString ()
	{
		return "\u001B[45m  \u001B[0m"; // purple
	}

}
