package td6.data;

/**
 * Fruit interface determines a fruit
 *
 */
public interface Fruit extends Gomme {
    /**
     * Getter for the name of fruit
     * @return String name the name of the fruit
     */
    String getName();

    /**
     * toString method 
     * @return It will put a green background behind the name of our fruit on the terminal for the debug
     */
    String toString ();
}