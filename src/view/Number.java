package td6.view;
import java.awt.Color;
import java.util.ArrayList;

/**
 *  A Number (one digit)
 *
 * @author naga-1
 * 
 */
public class Number extends CompoundFigure
{
	// size of a case	
	private static int size = 30;
		
	/**
     * Create a new digit according to value given in input, with coordinates multiplied by "size".
     *
     * @param x the top x*size coordinate of the square containing the number
     * @param y the top y*size coordinate of the square containing the number
     * @param value value of the digit
     * @param color colr of the digit
     * 
     * @pre x >= 0 && y >= 0 value >= 0 && value <= 9 && color != null
     */
    public Number(int x, int y, Integer value,Color color)
    {
    	super(init(x, y, value, color));
    }
    
    /**
     *  private init for Number, returns an array of figures reprenting the digit
     *  
     * @param x the top x*size coordinate of the square containing the ghost
     * @param y the top y*size coordinate of the square containing the ghost
     * @param value value of the digit
     * @param color colr of the digit
     *
     * @return an array of figures
     */
    private static Figure[] init(Integer x, Integer y, int value, Color c)
    {
    	ArrayList<Figure> figures = new ArrayList<Figure>();
    	Color[] liste;
    	Color b = Color.BLACK;
    	
    	figures.add(new Square(size, x*size, y*size, Color.BLACK)); // case
    	
    	switch (value)
    	{
			case 0: liste = new Color[]{c,c,c
									   ,c  ,c,
									    c,b,c,
									    c,  c,
									    c,c,c};
					break;
			case 1: liste = new Color[]{b,b,c
									   ,b  ,c,
									    b,b,c,
									    b,  c,
									    b,b,c};
			break;
			
			case 2: liste = new Color[]{c,c,c
									   ,b  ,c,
									    c,c,c,
									    c,  b,
									    c,c,c};
			break;
			
			case 3: liste = new Color[]{c,c,c
									   ,b  ,c,
									    c,c,c,
									    b,  c,
									    c,c,c};
			break;
			
			case 4: liste = new Color[]{c,b,c
									   ,c  ,c,
									    c,c,c,
									    b,  c,
									    b,b,c};
			break;
			
			case 5: liste = new Color[]{c,c,c
									   ,c  ,b,
									    c,c,c,
									    b,  c,
									    c,c,c};
			break;
			
			case 6: liste = new Color[]{c,c,c
									   ,c  ,b,
									    c,c,c,
									    c,  c,
									    c,c,c};
			break;
			
			case 7: liste = new Color[]{c,c,c
									   ,b  ,c,
									    b,c,c,
									    b,  c,
									    b,b,c};
			break;
			
			case 8: liste = new Color[]{c,c,c
									   ,c  ,c,
									    c,c,c,
									    c,  c,
									    c,c,c};
			break;
			
			case 9: liste = new Color[]{c,c,c
									   ,c  ,c,
									    c,c,c,
									    b,  c,
									    b,b,c};
			break;

			default:liste = new Color[]{b,b,b
									   ,b  ,b,
									    b,b,b,
									    b,  b,
									    b,b,b};
					break;
		}
    	
    	figures.add(new Square(size/5 , x*size + size/5*1 , y*size + size/5*0 , liste[0]));
    	figures.add(new Square(size/5 , x*size + size/5*2 , y*size + size/5*0 , liste[1]));
    	figures.add(new Square(size/5 , x*size + size/5*3 , y*size + size/5*0 , liste[2]));
    	figures.add(new Square(size/5 , x*size + size/5*1,  y*size + size/5*1 , liste[3]));
    	figures.add(new Square(size/5 , x*size + size/5*3 , y*size + size/5*1 , liste[4]));
    	figures.add(new Square(size/5 , x*size + size/5*1 , y*size + size/5*2 , liste[5]));
    	figures.add(new Square(size/5 , x*size + size/5*2 , y*size + size/5*2 , liste[6]));
    	figures.add(new Square(size/5 , x*size + size/5*3 , y*size + size/5*2 , liste[7]));
    	figures.add(new Square(size/5 , x*size + size/5*1 , y*size + size/5*3 , liste[8]));
    	figures.add(new Square(size/5 , x*size + size/5*3 , y*size + size/5*3 , liste[9]));
    	figures.add(new Square(size/5 , x*size + size/5*1 , y*size + size/5*4 , liste[10]));
    	figures.add(new Square(size/5 , x*size + size/5*2 , y*size + size/5*4 , liste[11]));
    	figures.add(new Square(size/5 , x*size + size/5*3 , y*size + size/5*4 , liste[12]));

    		   	
    	return figures.toArray(new Figure[0]);
    }
    
    /**
     *  move the Number
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
