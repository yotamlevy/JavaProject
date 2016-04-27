package algorithms.mazeGenerators;

import java.util.Random;

public class Position {

	private int x;
	private int y;
	private int z;
	private static Random rand = new Random();
	
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position() {
		super();
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public void updateCell(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Position getRandomPosition(Maze3d maze) {
		return new Position(rand.nextInt(maze.getHeight()), rand.nextInt(maze.getWidth()),
				rand.nextInt(maze.getDepth()));
	}
	
	public boolean isPositionAlike(Position p1){
		return p1.getX() == getX() || p1.getY() == getY() || p1.getZ() == getZ();
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * checks if current position equals to another position by its coordinates
	 */
	@Override
	public boolean equals(Object p)
	{
		if(!(p instanceof Position))
		{
			return false;
		}
		return ((this.x==((Position)p).x)&&(this.y==((Position)p).y)&&(this.z==((Position)p).z));
	}
	
	@Override
	public String toString() {
		String str = "{" + getX() + "," + getY() + "," + getZ() + "}";
		return str;
	}

}
