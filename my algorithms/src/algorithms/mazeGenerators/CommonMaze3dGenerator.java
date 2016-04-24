package algorithms.mazeGenerators;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	
	int x;
	int y;
	int z;
	abstract public Maze3d generate(int sizeHeight, int sizeWidth, int sizeDepth);
	
	public String measureAlgorithmTime(int sizeHeight, int sizeWidth, int sizeDepth){
		long startTime = System.currentTimeMillis();
		generate(sizeHeight,sizeWidth,sizeDepth);
		long endTime = System.currentTimeMillis();
		return "The Algorithm time is:" + (endTime - startTime)/1000.0 + " seconds.\n";
		
	}
	
	protected boolean isAShell(Position currPos, Maze3d maze) {

		// checks if this is a shell
		if ((currPos.getX() == 0) || (currPos.getY() == 0) || (currPos.getZ() == 0)
				|| (currPos.getX() == maze.getHeight() - 1) || (currPos.getY() == maze.getWidth() - 1)
				|| (currPos.getZ() == maze.getDepth() - 1)) {
			return true;
		} else {
			return false;
		}
	}

}
