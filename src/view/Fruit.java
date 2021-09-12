package td6.view;
import java.awt.Color;

/**
 * A fruit
 *
 * @author naga-1
 *
 * @inv getWidth() >= 0 && getHeight() >= 0
 * @inv getHeight() == getSize() && getWidth() == getSize()
 * 
 */
public class Fruit extends Circle
{

	// size of a case	
	private static int size = 30;
	
    /**
     * Create a new fruit in a center of a square of lenght "size", with coordinates multiplied by "size".
     *
     * @param x the top x*size coordinate of the square containing the fruit
     * @param y the top y*size coordinate of the square containing the fruit
     * @param color the color of the fruit
     *
     * @pre color != null && x >= 0 && y >= 0
     */
    public Fruit(int x, int y, Color color)
    {
        super(size/3, x*size + (size - (size/3))/2, y*size + (size - (size/3))/2, color); // center the fruit in the middle of a square of lenght "size"
    }
    
}
