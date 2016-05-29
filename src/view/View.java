package view;

import java.io.File;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Command;


public interface View {
	void displayMessage(String message);
	void displayMaze(Maze3d maze);
	void start();
	void sendCommands(HashMap<String, Command> commands);
	void fileSize(String fileName);
	void dirPath(String path);
}
