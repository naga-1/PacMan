package td6.data;


/**
*
* Implemenation of Pacman, represents pacman
*
* @inv corridor.x == x and corridor.y == y
*/
public class PacManImpl extends EntityAbstract implements PacMan {
	//Coordinates x and y of pacman
	private int x;
	private int y;
	private int hp;
	//Time remaining to eat a ghost
	private int timeToEat;

	/**
	 * Contructor of a new Pacman
	 * @param x the x coordinate of pacman
	 * @param y the y coordinate of pacman
	 * @param hp the hp of pacman
	 * @param timeToEat the time during pacman will be able to eat ghost
	 */
	public PacManImpl(int x, int y, int hp, int timeToEat){
		super(x, y);
		this.hp = hp;
		this.timeToEat = timeToEat;
	}
	

	/**
	 * returns health points of pacman
	 *  
	 * @return hp of pacman
	 */
	public int getHP(){
		return this.hp;
	}

	/**
	* returns boolean value to know if pacman can eat a ghost
	*
	* @return boolean value to know if pacman can eat a ghost
	*/
	public boolean getCanEat(){
		return (this.timeToEat > 0);
	}

	/**
	* returns value of time that pacman can eat a ghost
	*
	* @return boolean value of time that pacman can eat a ghost
	*/
	public int getTimeToEat(){
		return this.timeToEat;
	}

	/**
	* sets number of HP of pacman
	*
	* @param healthPoints number of hp of pacman
	*
	* @pre hp >= 0
	* @post hp == healthPoints
	*/
	public void setHP(int healthPoints){
		//Precondition
		assert healthPoints >= 0:"Precondition violated";
		this.hp=healthPoints;
		//Postcondition
		assert this.hp == healthPoints:"Postcondition violated";

	}

	/**
	* sets value of time that pacman can eat a ghos
	*
	* @param time   value of time that pacman can eat a ghost
	*
	* @pre time >= 0
	* @post timeToEat == time
	*/
	public void setTimeToEat(int time){
		//Precondition
		assert time >= 0:"Precondition violated";
		this.timeToEat=time;
		//Postcondition
		assert this.timeToEat == time:"Postcondition violated";
	}

	/**
     * toString method 
     * @return It will put a yellow background behind the object pacman on the terminal for the debug
     */
	public String toString ()
	{
		return "\u001B[43m  \u001B[0m";
	}
}
