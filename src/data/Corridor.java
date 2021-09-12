package td6.data;

import java.util.ArrayList;

/**
 * Corridor interface represents the differents cases of type corridor
 * 
 *
 */
public interface Corridor extends Case {
    
    /**
     * Getter for the entities of the corridor
     * @return List of entities
     */
    ArrayList<Entity> getEntities();

    /**
     * Void to remove an entity from the corridor
     * @param e the entity which we want to remove
     */
    void removeEntity(Entity e);

    /**
     * Void to add an entity from the corridor
     * @param e the entity which we want to add
     */
    void addEntity(Entity e);

    /**
     * Getter to know if a corridor is a ghost door
     * @return ghostdoor boolean
     */
    boolean isGhostDoor();

    /**
     * toString method 
     * @return It will put a different background according to the object present in the corridor
     */
    String toString ();
}