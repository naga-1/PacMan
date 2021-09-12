package td6.view;

import java.awt.Color;

/**
 *  A Heart
 *
 * @author naga-1
 * 
 */
public class Heart extends CompoundFigure
{
	// size of a case	
	private static int size = 30;
		
	/**
     * Create a Heart with coordinates multiplied by "size".
     *
     * @param x the top x*size coordinate of the square containing the heart
     * @param y the top y*size coordinate of the square containing the heart
     * @param color colr of the heart
     *
     * @pre color != null && x >= 0 && y >= 0
     */
    public Heart(int x, int y, Color color)
    {
	super(new Figure[]{
		new Square(size, x*size, y*size, Color.BLACK), // case
		new Triangle_up(size, size/3*2 , x*size+size/2, y*size + size, color), // bottom of hearth
		new Circle( size/2, x*size, y*size, color), // top left 
		new Circle( size/2, x*size + size/2, y*size, color), // top right
		});
    }
    
    /**
     *  move heath
     *  
     * @param x x the next top x*size coordinate of the square containing the Ghost
     * @param y the next top y*size coordinate of the square containing the Ghost
     */
    @Override
    public void move(int x,int y)
    {
    	for (Figure figure : this.getFigures())
    	{
            figure.move(x*size - figure.getX(), y*size - figure.getY()); // move of figure, use delta x and delta y
        }
    }
}
