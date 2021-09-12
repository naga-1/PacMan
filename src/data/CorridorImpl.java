package td6.data;

import java.util.ArrayList;

/**
 * Corridor implementation represents the differents cases  of type corridor
 */
public class CorridorImpl extends CaseImpl {
    
    //Array list of entities
    private ArrayList<Entity> liste; 
    //Boolean to know if a corridor is a ghost door or not
    private boolean ghostdoor;

    /**
     * Constructor of a corridor
     * @param x the x coordinate 
     * @param y the y coordinate 
     * @param ghostdoor the boolean who say if the corridor is a ghost door or not
     */
    public CorridorImpl(int x, int y, boolean ghostdoor){
    	super(x,y);
    	ArrayList<Entity> liste = new ArrayList<Entity>();
        this.liste = liste;
        this.ghostdoor = ghostdoor;
    }

    /**
     * Getter for a list of entities
     * @return arrayList of entity
     */
    public ArrayList<Entity> getEntities(){
    	return this.liste;
    }

    /**
     * Void to remove an entity from the corridor
     * @param e the entity that we want to remove
     */
    public void removeEntity(Entity e){
    	this.liste.remove(e);
    }

    /**
     * Void to add an entity from the corridor
     * @param e the entity that we want to add
     */
    public void addEntity(Entity e){
    	this.liste.add(e);
        invariant();
    }

    /**
     * Getter to know if a corridor is a ghost door
     * @return ghostdoor the boolean
     */
    public boolean isGhostDoor(){
        return this.ghostdoor;
    }

    /**
     * toString method 
     * @return It will put a different background according to the object present in the corridor
     */
    public String toString ()
    {
        if (this.ghostdoor && this.liste.size() == 0) {
            return "\u001B[44m  \u001B[0m";
        }
        if (this.liste.size() > 0) {
            return this.liste.get(this.liste.size()-1).toString(); // Color of last added entity (like superposition in view)
        } else {
            return "  "; // empty Corridor
        }
    }

    /**
     * Invariant of class Corridor
     * Check if every entity is at her correct coordinate
     */
    private void invariant(){
        int N = this.liste.size();
        for (int i = 0; i<N; i++){
            Entity e = this.liste.get(i);
            assert e.getX() == super.getX() && e.getY() == super.getY():"Erreur invariant";    
        }
    }
}