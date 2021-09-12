package td6.view;

import java.awt.Color;

/**
 * A ghostDoor
 *
 * @author naga-1
 *
 */
public class GhostDoor extends CompoundFigure
{
	// size of a case	
	private static int size = 30;
	
	private static Color color = Color.PINK;
	
    /**
     * Create a ghost door with coordinates multiplied by "size".
     *
     * @param x the top initial x*size location in pixel of the case
     * @param y the top initial y*size location in pixel of the case
     *
     * @pre x >= 0 && y >= 0
     */
	public GhostDoor(int x, int y)
    {
	super(new Figure[]{
		new Square(size, x*size, y*size, Color.BLACK), // case
		new Rectangle(size, size/5, x*size, y*size + size/5*2, color), // door
	});
    }
	
}
