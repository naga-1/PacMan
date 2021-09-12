package td6.data;

/**
 * Interface gomme, give the points of gomme
 *
 */
public interface Gomme extends Entity {
    
    /**
     * Getter for points of gomme
     * @return value of point of the gomme
     */
    int getPoints();

    /**
     * toString method 
     * @return It will put a white background behind the object Gomme on the terminal for the debug
     */
    String toString ();
}