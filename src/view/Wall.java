package td6.view;


import java.awt.Color;

/**
 * A wall
 *
 * @author naga-1
 *
 * @inv getWidth() >= 0 && getHeight() >= 0
 * @inv getHeight() == getSize() && getWidth() == getSize()
 */
public class Wall extends Square
{
	// size of a case	
	private static int size = 30;
	private static Color color = Color.BLUE;
	
    /**
     * Create a wall with coordinates multiplied by "size".
     *
     * @param x the top initial x*size location in pixel of the wall
     * @param y the top initial y*size location in pixel of the wall
     *
     * @pre x >= 0 && y >= 0
     */
    public Wall(int x, int y)
    {
        super(size, x*size, y*size, color); // square
    }
    
  }
