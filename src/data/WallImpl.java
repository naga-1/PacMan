package td6.data;

/**
 * Implementation of wall represents a wall
 */
public class WallImpl extends CaseImpl implements Wall {

	/**
	 * Constructor of a new wall
	 * @param x the x position
	 * @param y the y position
	 */
    public WallImpl(int x, int y){
        super(x, y);
    }

    /**
     * toString method 
     * @return It will put a black background behind the object wall on the terminal for the debug
     */
    public String toString ()
    {
        return "\u001B[40m  \u001B[0m"; // black
    }
}
