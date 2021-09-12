package td6.data;

import java.util.ArrayList;

/**
 * Game interface
 */
public interface Game {

    /**
     * Getter for highest score
     * @return value of highest score
     */
    int getHighestScore();

    /**
     * Setter for the higest score
     * @param score the new highest score
     */
    void setHighestScore(int score);

    /**
     * Getter for pacman
     * @return pacman the pacman of the game
     */
    PacMan getPacMan();

    /**
     * get current level
     * @return currentLevel the current level
     */
    Level getLevel();

    /**
     * Getter for the score
     * @return score the score of pacman in the current game
     */
    int getScore();

    /**
     * Setter for the score of the game
     * @param score the new score of the game
     */
    int setScore(int score);

    /**
     * Getter for the direction
     * @return direction the last direction saved
     */
    int getDirection();

    /**
     * Setter for the last direction of pacman
     * @param direction the direction we want to save
     */
    void setDirection (int direction);

    /**
     * Getter to have the list of levels
     * @return levels the list of levels
     */
    ArrayList<LevelImpl> getLevels();

    /**
     * Setter for the current level
     * @param level the current level
     */
    void setLevel (LevelImpl level) ;
}