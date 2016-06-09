package model;

import algorithms.mazeGenerators.Maze3d;


public interface Model {
	void generateMaze(String name, int height, int width, int depth);
	void saveMaze(String name, String fileName);
	void loadMaze(String fileName, String name);
	void SolveMaze(String name, String algorithm);
	Maze3d getMaze(String name);

}
