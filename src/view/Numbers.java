package td6.view;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A Number with multiple digits
 *
 * @author naga-1
 * 
 */
public class Numbers extends CompoundFigure
{
	// size of a case	
	private static int size = 30;
		
	/**
     * Create a new number with coordinates multiplied by "size".
     *
     * @param x the top x*size coordinate of the square containing the first number
     * @param y the top y*size coordinate of the square containing the first number
     * @param value value of the number
     *
     * @pre x >= 0 && y <= 0 &&  value >= 0  && value <= 999999
     */
    public Numbers(int x, int y, int value)
    {
    	super(init(x,y,value));
    }
    
    
    private static Figure[] init(int x, int y, int value)
    {
    	ArrayList<Figure> figures = new ArrayList<Figure>();
    	int i = 0;
     	
    	char[] charValue = String.valueOf(value).toCharArray();
    	//System.out.println(charValue);

    	
    	for(char val : charValue)
    	{
        	figures.add(new Number(x + i, y, Character.getNumericValue(val), Color.WHITE));
    		i=i+1;
    	}
    	return figures.toArray(new Figure[0]);
    }
    
    /**
     *  move Number
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
