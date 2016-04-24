package algorithms.mazeGenerators;

import java.util.Random;


public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {


	
	@Override
	public Maze3d generate(int sizeHeight, int sizeWidth, int sizeDepth) {
		
		Maze3d maze = new Maze3d(sizeHeight, sizeWidth, sizeDepth);
		Random rand = new Random();
		int x,y,z;
		
		for(x=0; x < sizeHeight; x++){
			
			for (y = 0; y < sizeWidth; y++) {
				
				for(z=0; z < sizeDepth ; z++){
					
					maze.setValueInPosition(new Position(x, y, z), rand.nextInt(2)); // randomly initializing the values inside the maze to zero or one 
					
				}
			}
		}
		
		
		//building path,in order to provide at least one path
				x=0;
				y=0;
				z=0;
				Direction direction;
				
				maze.setStartPosition(new Position(0,0,0));
				
				
				
				while ((x<sizeHeight) && (y<sizeWidth) && (z<sizeDepth) && (x>=0) && (y>=0) && (z>=0))
				{
			
					maze.setHoleAt(x, y, z);
					
					direction=Direction.values()[rand.nextInt(Direction.values().length)];
					
					switch (direction) 
					{
					case UP:
						x++;
						break;
					case RIGHT:
						y++;
						break;
					case FORWARD:
						z++;
						break;
					default:
						x++;
						break;
					}
					
				}
				//initializing the exit of the maze
				if(x == sizeHeight)
				{
					maze.setGoalPosition(new Position(x-1, y, z));
				}
				else if(y==sizeWidth)
				{
					maze.setGoalPosition(new Position(x, y-1, z));
				}
				else if(z==sizeDepth)
				{
					maze.setGoalPosition(new Position(x, y, z-1));
				}
				
				return maze;
	
	}
	

}
