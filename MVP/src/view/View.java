package view;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import presenter.Command;


public interface View {
	void displayMessage(String message);
	void displayMaze(Maze3d maze);
	void displayMaze2d(int[][] maze2d);
	void displaySolution(Solution sol);
	void start();
	void fileSize(String fileName);
	void dirPath(String path);
	void displayHelp();
}
