package td6.data;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Game imlementation 
 */
public class GameImpl{
	private int highestScore;
	private PacMan pacman;
    //Variable to know the curent level
    private LevelImpl currentLevel;
    //Array list containing all levels
    private ArrayList<LevelImpl> levels;
    private int direction;
    private int score;

    /**
     * Constructor of a new game
     * @param score the highest score saved
     * @param pm the pacman of the game
     * @param levels a list of levels
     */
    public GameImpl(int score, PacMan pm, ArrayList<LevelImpl> levels){
        this.highestScore = score;
        this.pacman = pm;
        this.levels = levels;
        this.score = 0;
        this.direction = 1;
    }

    /**
     * Getter for highest score
     * @return value of highest score
     */
    public int getHighestScore(){
        return this.highestScore;
    }

    /**
     * Setter for the higest score
     * @param score the new highest score
     */
    public void setHighestScore(int score){
        this.highestScore=score;

    }

    /**
     * Getter for the score
     * @return score the score of pacman in the current game
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Getter for the direction
     * @return direction the last direction saved
     */
    public int getDirection (){
        return this.direction;
    }

    /**
     * Setter for the last direction of pacman
     * @param direction the direction we want to save
     */
    public void setDirection (int direction){
        this.direction = direction;
    }

    /**
     * Getter to have the list of levels
     * @return levels the list of levels
     */
    public ArrayList<LevelImpl> getLevels(){
        return this.levels;
    }

    /**
     * Getter for pacman
     * @return pacman the pacman of the game
     */
    public PacMan getPacMan(){
    	return this.pacman;
    }

    /**
     * Setter for the current level
     * @param level the current level
     */
    public void setLevel (LevelImpl level) {
        this.currentLevel = level;
    }

    /**
     * Getter for the current level
     * @return currentLevel the current level
     */
    public LevelImpl getLevel ()
    {
        return this.currentLevel;
    }

    /**
     * Setter for the score of the game
     * @param score the new score of the game
     */
    public void setScore(int score){
        this.score = score;
    }
}