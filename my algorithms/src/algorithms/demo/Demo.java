package algorithms.demo;

import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;

/**
 * Demo class
 * @author yotam levy 
 */

public class Demo {
	/**
	 * This method runs a demo
	 * 
	 */
	
	public void run() {
		MyMaze3dGenerator gen = new MyMaze3dGenerator();
		Maze3d maze = gen.generate(11, 11, 11);
		
		System.out.println(maze);
		Maze3dAdapter mAdapter = new Maze3dAdapter(maze);
		System.out.println("########### this is the BSF Solution ############\n");
		BFS bfs = new BFS();
		Solution solution1 = bfs.search(mAdapter);
		System.out.println(solution1);
		System.out.println("number of states evaluated in BFS:" + solution1.StatesEvaluated() + "\n");
		
		System.out.println("########### this is the DSF Solution ############\n");
		DFS dfs = new DFS();
		Solution solution2 = dfs.search(mAdapter);
		System.out.println(solution2);
		System.out.println("number of states evaluated in DFS:" + solution2.StatesEvaluated() + "\n");
	}
}
