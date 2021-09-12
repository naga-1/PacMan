package td6.view;
import java.awt.Color;

/**
 * A gomme
 *
 * @author naga-1
 *
 * @inv getWidth() >= 0 && getHeight() >= 0
 * @inv getHeight() == getSize() && getWidth() == getSize()
 */
public class Gomme extends Circle
{
	// size of a case	
	private static int size = 30;
	// color of a gomme
	private static Color color = Color.WHITE;
	
    /**
     * Create a new gomme in a center of a square of lenght "size", with coordinates multiplied by "size".
     *
     * @param x the top x*size coordinate of the square containing the gomme
     * @param y the top y*size coordinate of the square containing the gomme
     *
     * @pre x >=0 && y >=0
     */
    public Gomme(int x, int y)
    {
        super(size/6, x*size + (size - (size/6))/2, y*size + (size - (size/6))/2, color); // center the gomme in the middle of a square of lenght "size"
    }
    
}
