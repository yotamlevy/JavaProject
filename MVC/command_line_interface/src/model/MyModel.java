package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import algorithms.*;
import algorithms.demo.Maze3dAdapter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;


public class MyModel implements Model {
	private Controller controller;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	private HashMap<String, Solution> solutions = new HashMap<String, Solution>();
	
	
	public MyModel(Controller controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void generateMaze(String name, int height, int width, int depth){
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {				
				MyMaze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(height, width, depth);
				mazes.put(name, maze);
				controller.displayMessage("* Maze " + name + " is ready *\n");				
			}				
		});
		thread.start();	
		threads.add(thread);
	}
	
	public Maze3d getMaze(String name) {
		return mazes.get(name);			
	}

	@Override
	public void saveMaze(String name, String fileName) {

		if (!mazes.containsKey(name)) 
		{
			controller.displayMessage("Maze" + name + "does not exist");
			return;
		}
		Maze3d maze = mazes.get(name);
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			int size = maze.toByteArray().length;
			int count = 0;
			while(size>255)
			{
				size = size-255;
				count++;
			}	
			out.write(size);
			out.write(count);
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			controller.displayMessage("Maze " + name + " is saved succesfully to the file: " + fileName +  "\n");
		} catch (FileNotFoundException e) {
			controller.displayMessage("File '" + fileName + "' Not Found");
			return;
		} catch (IOException e) {
			controller.displayMessage("IOEXception");
			return;
		}
	}

	public void SolveMaze(String mazeName, String algorithm){
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Maze3dAdapter mAdapter = new Maze3dAdapter(getMaze(mazeName));
				switch (algorithm) {
				case "bfs":
					BFS bfs = new BFS();
					Solution solution1 = bfs.search(mAdapter);
					solutions.put(mazeName, solution1);
					System.out.println(solution1);
						break;
				case "dfs":
					DFS dfs = new DFS();
					Solution solution2 = dfs.search(mAdapter);
					System.out.println(solution2);
					break;
				default:
					System.out.println("invalid input");
					break;
				}

			}				
		});
		thread.start();	
		threads.add(thread);
	}
	@Override
	public void loadMaze(String fileName, String name) {

		if (mazes.containsKey(name))
		{
			controller.displayMessage("Maze " + name + " Already exist and loaded succesfully\n");
			return;
		}
		byte[] myarry = null;
		try{

			InputStream in = new MyDecompressorInputStream(new FileInputStream(fileName));
			int size = in.read();
			int count = in.read();
			if(count > 0)
			{
				count = count*255;
				size = size+count;
			}
			myarry = new byte[size];
			in.read(myarry);
			in.close();	
			mazes.put(name, new Maze3d(myarry));
			controller.displayMessage("maze " + name + " has been loaded succesfully\n");
			
		}catch(FileNotFoundException e){
			controller.displayMessage("File Not Found");
			return;
		} catch (IOException e) {
			controller.displayMessage("input or output error");
			return;
		}		
	
	}

}
