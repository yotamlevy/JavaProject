package presenter;

import java.io.Serializable;


/**
 * a class that holds the properties of the maze creation
 * @author Yotam
 *
 */
public class Properties implements Serializable{

	private int numberOfThreads;
	private String solveMazeAlgorithm;
	private String algorithmToGenerateMaze;
	private String typeOfUserInterfece;
	/**
	 * constructor using fields
	 * @param numberOfThreads number of threads allowed to run in the model
	 * @param solveMazeAlgorithm which algorithm to search the maze:dfs/bfs
	 * @param algorithmToGenerateMaze which algorithm to generate the maze:prim/simple
	 * @param typeOfUserInterfece which user interface to use:cli/gui
	 */
	
	public Properties(int numberOfThreads, String algorithmToSearch, String algorithmToGenerateMaze,String typeOfUserInterfece) 
	{
		super();
		this.numberOfThreads = numberOfThreads;
		this.solveMazeAlgorithm = algorithmToSearch;
		this.algorithmToGenerateMaze = algorithmToGenerateMaze;

		this.typeOfUserInterfece=typeOfUserInterfece;
	}

	public Properties() 
	{
		super();

	}
	/**
	 * copy constructor
	 * @param p properties object(containing the properties of the project)
	 */
	public Properties(Properties p)
	{
		this.numberOfThreads=p.numberOfThreads;
		this.algorithmToGenerateMaze=p.algorithmToGenerateMaze;
		this.solveMazeAlgorithm=p.solveMazeAlgorithm;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public String getSolveMazeAlgorithm() {
		return solveMazeAlgorithm;
	}

	public void setSolveMazeAlgorithm(String solveMazeAlgorithm) {
		this.solveMazeAlgorithm = solveMazeAlgorithm;
	}

	public String getAlgorithmToGenerateMaze() {
		return algorithmToGenerateMaze;
	}

	public void setAlgorithmToGenerateMaze(String algorithmToGenerateMaze) {
		this.algorithmToGenerateMaze = algorithmToGenerateMaze;
	}

	public String getTypeOfUserInterfece() {
		return typeOfUserInterfece;
	}

	public void setTypeOfUserInterfece(String typeOfUserInterfece) {
		this.typeOfUserInterfece = typeOfUserInterfece;
	}
	
	
	
}
