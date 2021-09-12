
package td6.data;

/**
 * Implementation ghostdoor represents a ghostdoor
 */
public class GhostDoorImpl extends CaseImpl {
    
	/**
	 * Constructor of a new ghost door
	 * @param x the x position
	 * @param y the y position
	 */
    public GhostDoorImpl(int x, int y){
        super(x, y);
    }

    /**
     * toString method 
     * @return It will put a blue background behind the object ghostDoor on the terminal for the debug
     */
    public String toString ()
    {
        return "\u001B[44m  \u001B[0m"; // blue
    }
}
