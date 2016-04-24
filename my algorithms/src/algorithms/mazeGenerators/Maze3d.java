package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Maze3d {
	
	static final int HOLE = 0;
	static final int WALL = 1;
	private int height;   //x   - up & down
	private int width;    //y   - right & left
	private int depth;    //z   - forward & backwards
	private int[][][] maze;      //(x,y,z)
	private Position startPosition;
	private Position goalPosition;
	
	Maze3d(int height, int width, int depth)
	{
	this.height = height;
	this.width = width;
	this.depth = depth;
	maze = new int[height][width][depth];

	}

	public void fillWithWalls()
	{
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				for(int n=0;n<depth;n++)
				{
					maze[i][j][n]=1;
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public int[][][] getMaze() {
		return maze;
	}


	public void setMaze(int[][][] maze) {
		this.maze = maze;
	}
	
	public int setValueAt(int x, int y, int z, int bin) {
		return maze[x][y][z] = bin;
	}
	
	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}

	public int getValueAt(int x, int y, int z) {
		return maze[x][y][z];
	}

	public void setWallAt(int x, int y, int z) {
		maze[x][y][z] = 1;
	}

	public void setHoleAt(int x, int y, int z) {
		maze[x][y][z] = 0;
	}
	
	public void setHoleAt(Position pos) {
		setHoleAt(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public void setValueInPosition(Position p,int bin)
	{
		setValueAt(p.getX(), p.getY(), p.getZ(), bin);
	}
	
	/**
	 * get cross section by x
	 * @param x the x coordinate
	 * @return 2D array
	 * @throws IndexOutOfBoundsException
	 */
	public int [][] getCrossSectionByX(int x)throws IndexOutOfBoundsException
	{
		if((x<0)||(x>= height))
		{
			throw new IndexOutOfBoundsException();
		}
		
		int[][] maze2d=new int[width][depth];
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<depth;j++)
			{
				maze2d[i][j]=maze[x][i][j];
			}
		}
		return maze2d;
	}
	/**
	 * get cross section by y
	 * @param y the y coordinate
	 * @return 2D array
	 * @throws IndexOutOfBoundsException
	 */
	
	public int [][] getCrossSectionByY(int y)throws IndexOutOfBoundsException
	{
		if((y<0)||(y>=width))
		{
			throw new IndexOutOfBoundsException();
		}
		
		int[][] maze2d=new int[height][depth];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<depth;j++)
			{
				maze2d[i][j]=maze[i][y][j];
			}
		}
		return maze2d;
	}
	/**
	 * get cross section by z
	 * @param z the z coordinate
	 * @return 2D array
	 * @throws IndexOutOfBoundsException
	 */
	public int [][] getCrossSectionByZ(int z)throws IndexOutOfBoundsException
	{
		if((z<0)||(z>=depth))
		{
			throw new IndexOutOfBoundsException();
		}
		
		int[][] maze2d=new int[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				maze2d[i][j]=maze[i][j][z];
			}
		}
		return maze2d;
	}
	
	public String[] getPossibleMoves(Position p) {
		ArrayList<String> moves = new ArrayList<String>();

		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		

		if ((x - 1 >= 0) && (maze[x - 1][y][z] == HOLE)) {
			moves.add("Down");

		}
		if ((x + 1 < height) && (maze[x + 1][y][z] == HOLE)) {
			moves.add("Up");

		}
		if ((y - 1 >= 0) && (maze[x][y - 1][z] == HOLE)) {
			moves.add("Left");

		}
		if ((y + 1 < width) && (maze[x][y + 1][z] == HOLE)) {
			moves.add("Right");

		}
		if ((z - 1 >= 0) && (maze[x][y][z - 1] == HOLE)) {
			moves.add("Backward");

		}
		if ((z + 1 < depth) && (maze[x][y][z + 1] == HOLE)) {
			moves.add("Forward");

		}
//		if (p.equals(goalPosition) || p.equals(startPosition)) {
//			if (x + 1 == height) {
//				moves.add("Up");
//			} else if (x - 1 == -1) {
//				moves.add("Down");
//			} else if (y + 1 == width) {
//				moves.add("Right");
//			} else if (y - 1 == -1) {
//				moves.add("Left");
//			} else if (z + 1 == depth) {
//				moves.add("Forward");
//			} else if (z - 1 == -1) {
//				moves.add("Backward");
//			}
//		}

		String[] arr = moves.toArray(new String[moves.size()]);

		return arr;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < maze.length; i++) {
			str.append("floor " + i + ":\n");
			for (int j = 0; j < maze[i].length; j++) {
				str.append("[");
				for (int k = 0; k < maze[i][j].length; k++) {
					str.append(" " + maze[i][j][k] +" ");
				}
				str.append("]\n");
			}
			str.append("\n");
		}
		return str.toString();
	}

}
