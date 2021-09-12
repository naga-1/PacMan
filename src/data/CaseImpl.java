package td6.data;

/**
 * Case implementation represents each case of the level 
 *
 */
public class CaseImpl implements Case {
    //Coordinates x and y
    private int x;
    private int y;

    /**
     * Constructor of a new case
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public CaseImpl(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Getter for X position
     * @return value x
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Getter for Y position
     * @return value y
     */
    public int getY(){
        return this.y;
    } 

}