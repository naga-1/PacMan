package td6.view;
import java.awt.Color;

/**
 * A PacMan composed of a circle and a triangle
 *
 * @author naga-1
 * 
 */
public class PacMan extends CompoundFigure
{
	// size of a case	
	private static int size = 30;
	
	 /**
	 * Create a new PacMan looking at the right (by default)
	 *
	 * @param x the top x*size coordinate of the square containing the PacMan
     * @param y the top y*size coordinate of the square containing the PacMan
	 *
	 * @pre  x >= 0 && y >= 0
	 */
    public PacMan(int x, int y)
    {
	super(new Figure[]{
		new Square(size, x*size, y*size, Color.BLACK), // case
		new Circle(size,x*size ,y*size , Color.YELLOW), // body
		new Triangle_right(size, size/2, x*size + size/2, y*size + size/2, Color.BLACK), // mouth
		});
	
    }
    
        
    
    /**
     * modify PacMan's mouth and draw it according to its direction
     * 
     * @param direction
     * 
     */
    public void draw(int direction) {
    	
		
    	int x = this.getX();
    	int y = this.getY();
    	
    	switch(direction)
    	{
    		case 1 : this.getFigures()[2].erase();
					this.setFigure(2, new Triangle_up(size, size/2, x + size/2, y + size/2, Color.BLACK)); //up
					break;
								
    		case 2 : this.getFigures()[2].erase();
					this.setFigure(2, new Triangle_right(size, size/2, x + size/2, y + size/2, Color.BLACK)); //right
					break;
    		
    		case 3 : this.getFigures()[2].erase();
    				this.setFigure(2,  new Triangle_down(size, size/2, x + size/2, y + size/2, Color.BLACK)); // down
    				break;
    		
    		case 4 : this.getFigures()[2].erase();
    				this.setFigure(2,  new Triangle_left(size, size/2, x + size/2, y + size/2, Color.BLACK)); // left
    				break;
    		
    		default : this.getFigures()[2].erase();
    				this.setFigure(2,  new Triangle_right(size, size/2, x + size/2, y + size/2, Color.BLACK)); // right
    				break;
    	}
    	
    	this.draw();
    		
    	
    }
    
    /**
     *  move PacMan
     *  
     * @param x x the next top x*size coordinate of the square containing the PacMan
     * @param y the next top y*size coordinate of the square containing the PacMan
     */
    @Override
    public void move(int x, int y)
    {
    	for (Figure figure : this.getFigures())
    	{
            figure.move(x*size - figure.getX(), y*size - figure.getY()); // move of figure, use delta x and delta y
    	}
    }
}
