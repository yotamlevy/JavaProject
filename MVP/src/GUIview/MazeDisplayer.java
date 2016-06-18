package GUIview;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import view.View;

import org.eclipse.swt.events.KeyListener;

// this is (1) the common type, and (2) a type of widget
// (1) we can switch among different MazeDisplayers
// (2) other programmers can use it naturally
public abstract class MazeDisplayer extends Canvas{
	private View view;
	
	// just as a stub...
	int[][] mazeData={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};

	
	public MazeDisplayer(Composite parent, int style) {
		super(parent, style);
	}

	public void setMazeData(int[][] mazeData){
		this.mazeData=mazeData;
	}
	
	public abstract  void setCharacterPosition(int row,int col);
	
	/**
	 * sets the current maze
	 */
	public abstract void setCurrMaze(Maze3d m);
	
	/**
	 * get the current maze
	 */
	public abstract Maze3d getCurrMaze();

	
	public abstract void setCharacterInPlace(int x,int y,int z);
	
	/**
	 * Return the position of the character.
	 * @return
	 */
	public abstract Position getCharacterPosition();
	
	public abstract void moveUp();

	public abstract  void moveDown();

	public abstract  void moveLeft();

	public  abstract void moveRight();
	
	public abstract void moveUpstairs();
	
	public abstract void moveDownstairs();
	
	public abstract void moveCharacter(int x, int y, int z);

	public abstract void setCharacterPosition(int x, int y, int z);

}