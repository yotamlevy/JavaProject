package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Presenter;


public class MyView extends Observable implements View, Observer {

	private BufferedReader in;
	private Writer out;
	private CLI cli;
	private Presenter presenter;
	private HashMap<String, Command> commands;
	
	public MyView(BufferedReader in, Writer out)
	{		
		this.in = in;
		this.out = out;		
		cli = new CLI(in, out);
		cli.addObserver(this);
	}
			
	@Override
	public void displayMessage(String message) {
		try {
			out.write(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {				
				cli.start();
			}
			
		});	
		thread.start();
	}


	@Override
	public void displayMaze(Maze3d maze) {
		try {			
			out.write(maze.toString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Override
	public void fileSize(String fileName){
		
		File file = new File(fileName);
		long theSize = file.length();
		String str = String.valueOf(theSize);
		if (str.contentEquals("0")){
			try {
				out.write("file '"+ fileName + "' does not exist\n");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out.write("the file '" + fileName + "' size is: " + str + " Bytes\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void dirPath(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        try {
					out.write("File: " + listOfFiles[i].getName()+ "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
		      } else if (listOfFiles[i].isDirectory()) {
		        try {
					out.write("Directory: " + listOfFiles[i].getName()+ "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
		      }
		    }
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == cli) {
			this.setChanged();
			this.notifyObservers(arg);			
		}
		
	}

	@Override
	public void displayMaze2d(int[][] maze2d) {
		
			for (int i = 0; i < maze2d.length; i++) {
				for (int j = 0; j < maze2d[0].length; j++) {
					System.out.print(maze2d[i][j] + ", ");
				}
				System.out.print("\n");
			}
		}

	@Override
	public void displaySolution(Solution sol) {
		System.out.println(sol.toString());
		
	}

	@Override
	public void displayHelp() {
	System.out.println("******** The commands are: ******** \n");
	System.out.println("\"dir\" <path>\n");
	System.out.println("\"generate_maze_3d\" <name_of_maze> <height> <width> <depth> \n");
	System.out.println("\"display\" <name_of_maze> \n");
	System.out.println("\"solve\" <name_of_maze> <algorithm> \n");
	System.out.println("\"save_maze\" <name_of_maze> <name_of_file.txt>\n");
	System.out.println("\"load_maze\" <name_of_maze> <name_of_file.txt>\n");
	System.out.println("\"file_size\" <name_of_file.txt>\n");
	System.out.println("\"cross_section_y\" <row> <name> \n");
	System.out.println("\"cross_section_x\" <floor> <name> \n");
	System.out.println("\"save_zip_map\" <file_name>\n");
	System.out.println("\"load_zip_map\" <file_name>\n");
	System.out.println("\"exit\" \n");
		
	}
		
	
	
}
