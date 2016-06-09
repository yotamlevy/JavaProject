package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;


public class MyView implements View {

	private BufferedReader in;
	private Writer out;
	private CLI cli;
	private Controller controller;
	private HashMap<String, Command> commands;
	
	public MyView(Controller controller, BufferedReader in, Writer out)
	{		
		this.in = in;
		this.out = out;
		
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
	public void sendCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		cli = new CLI(in, out, commands);
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
	
}
