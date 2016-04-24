package algorithms.mazeGenerators;

public interface Maze3dGenerator {

	public Maze3d generate(int sizeHeight, int sizeWidth, int sizeDepth);
	
	public String measureAlgorithmTime(int sizeHeight, int sizeWidth, int sizeDepth);
	
}
