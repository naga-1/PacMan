package td6.view;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import td6.logic.GameImpl;


/**
 * This class represents a PacMan game, composed of different figures
 *
 * @author naga-1
 */
public class PacManGame
{
	/**
	 * interfaces	
	 */
	private td6.logic.GameImpl gameLogicInterface;
	
    /**
     * the canvas used to display the figures
     */
    private final CanvasFrame canvas;

    /**
     * the figures displayed
     */
    private Figure[] maze = null;
    private Figure[] data = null;
    private Figure[] characters = null;
  
    
    /**
     * main where we create a game to play PacMan
     *
     * @param args NONE
     */
    public static void main(String[] args)
    {
    	PacManGame game = new PacManGame("/levels.txt", "/highestScore.txt");
    	game.play();
    }

    
    /**
     * Constructor for objects of class PacManGame
     * 
     * @param pathlevels path of the file containing levels
     * @param pathHighestScore path of the file containing the highscore
     */
    public PacManGame(String pathlevels, String pathHighestScore)
    {
        this.canvas = CanvasFrame.getCanvas();
        this.gameLogicInterface =  new GameImpl(pathlevels, pathHighestScore);

    }
    
    
    /**
     *  play pacman
     */
    public void play()
    {
    	//init variables
    	boolean end = false;
    	int dir;
    	
    	//init figures
    	this.maze = this.getMazeFigure(gameLogicInterface.getMaze());
    	this.characters = this.getCharactersFigure(gameLogicInterface.getXPacMan(), gameLogicInterface.getYPacMan(), gameLogicInterface.getGhostsPosition());
    	this.data = this.getDataFigure(gameLogicInterface.getHpPacMan(), gameLogicInterface.getScore(), gameLogicInterface.getHighestScore());
    	
        while(!end)
        {
        	// read keyboard and give information of direction to compute
        	dir = this.getPacManDirection();
        	this.gameLogicInterface.compute(dir);
        	
        	//this.eraseMaze();
        	this.eraseAll();

        	
        	// get and set new values after computing
        	this.maze = this.getMazeFigure(gameLogicInterface.getMaze());
        	this.data = this.getDataFigure(gameLogicInterface.getHpPacMan(), gameLogicInterface.getScore(), gameLogicInterface.getHighestScore());
        	this.moveCharacters();
        	
        	
        	// draw with new values
        	this.drawMaze();
        	this.drawCharacters(gameLogicInterface.areGhostsEatable());
        	this.drawData();
        	this.canvas.redraw();
        	
        	// wait 200 ms
        	this.canvas.wait(180);
        	
        	// check if end of loop   	
        	if(gameLogicInterface.isGameWon())
        	{
        		ImageIcon icon = new ImageIcon(((new ImageIcon(getClass().getResource("/pc.png"))).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
        		JOptionPane.showMessageDialog(null, "YOU WIN !!!", "PacManGame",JOptionPane.INFORMATION_MESSAGE,icon);
        		end = true;
        	}
        	if(gameLogicInterface.isPacManDead())
        	{
        		ImageIcon icon = new ImageIcon(((new ImageIcon(getClass().getResource("/gh.png")))	.getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
        		JOptionPane.showMessageDialog(null, "YOU LOSE !!!", "PacManGame",JOptionPane.INFORMATION_MESSAGE,icon);
        		end = true;
        	}
        	
    		

        	if(gameLogicInterface.isLevelPassed())
        	{
        		// erase all figures
        		this.eraseAll();
        		
        		// load new level
        		this.maze = this.getMazeFigure(gameLogicInterface.getMaze());
        		this.drawMaze();
            	this.characters = this.getCharactersFigure(gameLogicInterface.getXPacMan(), gameLogicInterface.getYPacMan(), gameLogicInterface.getGhostsPosition());
            	this.data = this.getDataFigure(gameLogicInterface.getHpPacMan(), gameLogicInterface.getScore(), gameLogicInterface.getHighestScore());
            	
        		this.canvas.redraw();
        	}
        }
        
        // erase all figures
        this.eraseAll();
        this.canvas.redraw();
    }
    
        
    /**
     * Draw maze
     */
    private void drawMaze()
    {
        for (Figure figure : this.maze)
        {
        	figure.draw();
        }
    }
    
    
    /**
     * Draw Pacman and Ghosts
     */
    private void drawCharacters(boolean isGhostEatable)
    {
        for (Figure figure : this.characters)
        {
        	if( figure instanceof PacMan)
        	{
        		PacMan pc =(PacMan) figure;
        		pc.draw(gameLogicInterface.getDirection());
        	}
        	else if(figure instanceof Ghost)
        	{
        		Ghost fig = (Ghost)figure;
        		fig.draw(isGhostEatable);
        	}
        }
    }
    
    
    /**
     * Draw score, highest score and hp
     */
    private void drawData()
    {
        for (Figure figure : this.data)
        {
        	figure.draw();
        }
    }
    
    
    /**
     *  Move PacMan and Ghost
     */    
    private void moveCharacters()
    {
    	int xPacMan = gameLogicInterface.getXPacMan();
		int yPacMan = gameLogicInterface.getYPacMan();
    	int [][] ghostCoordinates = gameLogicInterface.getGhostsPosition();
    	int indexGhostCoordinates = 0;
    	
        for (Figure figure : this.characters)
        {
        	if( figure instanceof PacMan)
        	{
        		figure.move(yPacMan,xPacMan); 
        	}
        	else if(figure instanceof Ghost)
        	{	 
        		figure.move(ghostCoordinates[indexGhostCoordinates][1],ghostCoordinates[indexGhostCoordinates][0]);
        		indexGhostCoordinates ++;
        	}
        }
     }
    
    
    /**
     *  erase maze, data and characters figures
     */
    private void eraseAll()
    {
    	for(Figure figure : this.maze)
    	{
    		figure.erase();
    	}
    	for(Figure figure : this.characters)
    	{
    		figure.erase();
    	}
    	for(Figure figure : this.data)
    	{
    		figure.erase();
    	}
    }
    
    
    /**
     * get the direction of PacMan and returns it
     * 
     * @return direction actual direction of PacMan
     */
    private int getPacManDirection()
    {
    	if(this.canvas.isUpPressed())
    	{
    		//System.out.println("UP pressed");
    		return 1;
    	}
    	else if(this.canvas.isRightPressed())
    	{
    		//System.out.println("RIGHT pressed");
    		return 2;
    	}
    	else if(this.canvas.isDownPressed()) 
    	{
    		//System.out.println("DOWN pressed");
    		return 3;
    	}
    	else if(this.canvas.isLeftPressed())
    	{
    		//System.out.println("LEFT pressed");
    		return 4;
    	}
    	return 0;
     }
    

    /**
     * returns an array of figure representing the maze
     * 
     * @param maze an array of int
     * @return an array of figure representing the maze to draw
     * 
     * @pre for value in maze : value >= 0 && value <= 3
     */    
   private Figure[] getMazeFigure(int[][] maze)
   {
   	ArrayList<Figure> figures = new ArrayList<Figure>();
   	
   	for(int j = 0; j < maze.length; j++)
   	{
   		for(int i = 0; i < maze[j].length; i++)
   		{
   			if(maze[j][i] == 0) //wall
   	   		{
   	   			figures.add(new Wall(i, j));
   	   		}
   			else if(maze[j][i] == 1) // gomme
   	   		{
   	   			figures.add(new Gomme(i, j));
   	   		}
   			else if(maze[j][i] == 2) // fruit
   	   		{
   	   			figures.add(new Fruit(i, j, Color.RED));
   	   		}
   	   		else if(maze[j][i] == 3) // ghostdoor
   	   		{
   	   			figures.add(new GhostDoor(i, j));

   	   		}
   		}
   	}
	return figures.toArray(new Figure[0]);
   }
    
   
    /**
     * returns an array of figure representing the characters (pacman and ghosts)
     * 
     * @param xPacMan x position of PacMan
     * @param yPacMan y position of PacMan
     * @param ghostsCoordinates array with arrays of x and y ghosts's positions
     * @return an array of figure representing the maze
     * 
     * @pre xPacMan >= 0 && yPacMan >= 0
     * @pre for value in ghostCoordinates : value >= 0
     */
    private Figure[] getCharactersFigure(int xPacMan, int yPacMan, int[][] ghostsCoordinates)
    {
    	ArrayList<Figure> figures = new ArrayList<Figure>();
    	
    	figures.add(new PacMan(xPacMan, yPacMan));
    	
    	for(int[]  element : ghostsCoordinates)
    	{
    		figures.add(new Ghost(element[0], element[1]));
    	}
    	
    	return figures.toArray(new Figure[0]);
    }
    
    
    /**
     * returns an array of figures representing the data
     * 
     * @param hpPacMan HP of pacman
     * @param scores  score in the game
     * @param highestScore highest score of all games
     * @return an array of figures representing the data
     * 
     * @pre score >= 0 && highestScore >= score
     */
    private Figure[] getDataFigure(int hpPacMan, int score, int highestScore)
    {
    	ArrayList<Figure> figures = new ArrayList<Figure>();
    	
    	int yBottom = (this.maze[this.maze.length-1].getY())/30; // pas beau mais pour l'instant j'ai pas le temps
    	
    	figures.add(new Numbers(0, yBottom + 2, hpPacMan));
    	figures.add(new Heart(2, yBottom + 2, Color.PINK));
    	figures.add(new Numbers(0, yBottom + 4, score));
    	figures.add(new Numbers(0, yBottom + 6, highestScore));
    	
    	return figures.toArray(new Figure[0]);
    }
}


