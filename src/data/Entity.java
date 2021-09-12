package td6.data;

/**
 * Interface entity represents an entity
 */
public interface Entity {
	/**
	 * Setter for x position
	 * @param x the x position
	 */
	void setX(int x);
	/**
	 * Setter for y position
	 * @param y the y position
	 */
	void setY(int y);
	/**
	 * Getter for the x position
	 * @return the x position
	 */
	int getX();
	/**
	 * Getter for the y position
	 * @return the y position
	 */
	int getY();
}