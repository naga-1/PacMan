package td6.data;

/**
 * Fruit interface determines a fruit
 *
 */
public class FruitImpl extends GommeImpl implements Fruit{

	private String name;

	/**
	 * Constructor for a new fruit
	 * @param x the x coordinate of the fruit
	 * @param y the y coordinate of the fruit
	 * @param name the name of the fruit
	 * @param pts the points of the fruit
	 */
	public FruitImpl(int x, int y, String name, int pts){
		super(x,y,pts);
		this.name = name;
	}
    /**
     * Getter for the name of fruit
     * @return String name the name of the fruit
     */
    public String getName(){
    	return this.name;
    }

    /**
     * toString method 
     * @return It will put a green background behind the name of our fruit on the terminal for the debug
     */
	public String toString ()
	{
		return "\u001B[42m"+this.name+"\u001B[0m"; // Fruit
	}
}