package td6.data;

/**
 * Implementation of  gomme, give the points of gomme
 *
 */
public class GommeImpl extends EntityAbstract implements Gomme {

	//Points of a gomme
	private int points;

	/**
	 * Constructor of a new gomme
	 * @param x the x coordinate of the gomme
	 * @param y the y coordinate of the gomme
	 * @param pts the value in points of the gomme if she is eaten
	 */
	public GommeImpl(int x, int y, int pts){
		super(x, y);
		this.points = pts;

	}
    
    /**
     * Getter for points of gomme
     * @return value of point of the gomme
     */
    public int getPoints(){
    	return this.points;
    }

	/**
     * toString method 
     * @return It will put a white background behind the object Gomme on the terminal for the debug
     */
	public String toString ()
	{
		return "\u001B[47m  \u001B[0m"; // white
	}
}