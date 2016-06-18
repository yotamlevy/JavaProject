package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import presenter.Properties;


public interface Model {
	void generateMaze(String name, int height, int width, int depth, String algorithm);
	void saveMaze(String name, String fileName);
	void loadMaze(String fileName, String name);
	void SolveMaze(String name, String algorithm);
	void displayCrossSectionByX(String section, String mazeName);
	void displayCrossSectionByY(String section, String mazeName);
	void saveToZip(String filename);
	void LoadFromZip(String fileName);
	Maze3d getMaze(String name);
	Solution getSolution();
	int[][] getMaze2d();
	String getMessage();
	void exit();

}
