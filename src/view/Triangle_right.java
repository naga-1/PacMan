package td6.view;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

/**
 * A triangle that can be manipulated and drawn on a canvas.
 *
 * @author naga-1
 * 
 * @inv getWidth() >= 0 && getHeight() >= 0
 * @inv getColor() != null
 */
public class Triangle_right extends SimpleFigure
{

    /**
     * Create a new triangle.
     *
     * @param width the triangle initial width
     * @param height the triangle initial height
     * @param x the triangle initial x location
     * @param y the triangle initial y location
     * @param color the triangle initial color.
     *
     * @pre width >= 0 && height >= 0 && color != null
     */
    public Triangle_right(int width, int height, int x, int y, Color color)
    {
        super(width, height, x, y, color);
    }

    //------------------------------------------------------------------------
    // Draw
    //------------------------------------------------------------------------
    /**
     * {@inheritDoc }
     */
    @Override
    protected Shape makeShape()
    {
        int[] xpoints = {getX(), getX() + (getWidth() / 2), getX() + (getWidth() / 2)};
        int[] ypoints = {getY(), getY() + getHeight(), getY() - getHeight()};
    	
        return new Polygon(xpoints, ypoints, 3);
    }
}
