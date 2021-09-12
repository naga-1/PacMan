package td6.view;
import java.awt.Color;

/**
 *  A ghost
 *
 * @author naga-1
 * 
 */
public class Ghost extends CompoundFigure
{
	// size of a case	
	private static int size = 30;
	private static final Color colors[] = new Color[]{Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK,Color.RED};
	private Color color;
		
	/**
     * Create a new Ghost with coordinates multiplied by "size".
     *
     * @param x the top x*size coordinate of the square containing the ghost
     * @param y the top y*size coordinate of the square containing the ghost
     *
     * @pre x >= 0 && y >= 0
     */
    public Ghost(int x, int y)
    {
    	super(new Figure[]{
							new Square(size, x*size, y*size, Color.BLACK), // case
							new Square((int) (size/1.5), x*size + (int)(size/(4*1.5)), y*size + (int)(size/(1.5*2)),Color.BLACK), // body's ghost
							new Circle((int) (size/1.5), x*size + (int)(size/(4*1.5)), y*size, Color.BLACK) // head's ghost
							});
	
    	// random color among 6
		this.color = randColor();
		// apply it
		this.changeColor(false);

    }
    
    /**
     * returns a color for a ghost
     * 
     * @return a color for a ghost
     */
    private static Color randColor()
    {
    	return colors[(int)(Math.random() * colors.length)];
    	
    }
    
    
    /**
     * change color of ghost if pacman can eat them or can't
     * 
     * @param isEatable boolean true if pacman can eat them
     */
    private void changeColor(boolean isEatable)
    {    	 
    	if(isEatable)
    	{
    		SimpleFigure fig = (SimpleFigure) this.getFigures()[1];
    		fig.setColor(Color.BLUE);
    	    fig = (SimpleFigure) this.getFigures()[2];
    		fig.setColor(Color.BLUE);
		}
    	else
    	{
    		SimpleFigure fig = (SimpleFigure) this.getFigures()[1];
    		fig.setColor(color);
    	    fig = (SimpleFigure) this.getFigures()[2];
    		fig.setColor(color);
		}
    }
    
    
    /**
     *  move Ghost
     *  
     * @param x the next top x*size coordinate of the square containing the Ghost
     * @param y the next top y*size coordinate of the square containing the Ghost
     */
    @Override
    public void move(int x,int y)
    {
    	
    	this.getFigures()[0].move(x*size - this.getFigures()[0].getX(), y*size - this.getFigures()[0].getY());
    	this.getFigures()[2].move(x*size + (int)(size/(4*1.5)) - this.getFigures()[2].getX(), y*size - this.getFigures()[2].getY());
    	this.getFigures()[1].move(x*size + (int)(size/(4*1.5))- this.getFigures()[1].getX(), y*size + (int)(size/(1.5*2)) - this.getFigures()[1].getY());
    	
    }
    
    
    /**
     * Modify ghost color if it is eatable or not and move it
     * 
     * @param isEatable boolean which is true if PacMan can eat them
     */
    public void draw(boolean isEatable)
    {
    	this.changeColor(isEatable);
    	for(Figure figure : this.getFigures())
    	{
    		figure.draw();
    	}
     }
}
