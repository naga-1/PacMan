package td6.data;

public abstract class EntityAbstract implements Entity {

	//Coordinates x and y 
    protected int x;
    protected int y;

    /**
     * Constructor for an entity
     * @param x the x position
     * @param y the y position
     */
	public EntityAbstract (int x, int y)
	{
		this.x = x;
		this.y = y;
	}

    /**
     * Getter for x position of an entity
     * @return x the x coordinate
     */
    public int getX(){
		return this.x;
	}
	
	/**
     * Getter for y position of an entity
     * @return y the y coordinate
     */
	public int getY(){
		return this.y;
	}

	/**
	* sets x position of the ghost on the board
	*
	* @param xGhost  x position of the ghost on the board
	*
	* @pre xGhost >= 0
	* @post x == xGhost
	*/
  	public void setX(int xGhost){
  		assert xGhost >= 0 :"Precondition violated";
  		this.x = xGhost;
  		assert this.x == xGhost:"Postcondition violated";
  	}

  	/**
	* sets y position of the ghost on the board
	*
	* @param yGhost  y position of the ghost on the board
	*
	* @pre yGhost >= 0
	* @post y == yGhost
	*/
  	public void setY(int yGhost){
  		assert yGhost >= 0 :"Precondition violated";
  		this.y=yGhost;
  		assert this.y == yGhost:"Postcondition violated";
  	}

}