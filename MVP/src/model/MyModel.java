package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.Maze3dAdapter;
import algorithms.domains.State;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import maze3d.domains.Maze3dState;
import presenter.Presenter;

public class MyModel extends Observable implements Model {
	private Presenter presenter;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private HashMap<String, Maze3d> mazes = new HashMap<String, Maze3d>();
	private HashMap<String, Solution> solutions = new HashMap<String, Solution>();
	private String message;
	private ExecutorService service;
	private int[][] maze2d;
	private Solution solution;
	
	public String getMessage() {
		return message;
	}

	public MyModel() {
		//service = Executors.newFixedThreadPool();
		service = Executors.newCachedThreadPool();
	}

	@Override
	public void generateMaze(String name, int height, int width, int depth) {
		
		service.submit(new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				MyMaze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze = mg.generate(height, width, depth);
				mazes.put(name, maze);

				message = "* Maze " + name + " is ready *\n";

				setChanged();
				notifyObservers("display_message");
				return null;
			}
		});
	}
		
//		Thread thread = new Thread(new Runnable() {

//			public void run() {


//		thread.start();
//		threads.add(thread);
	

	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}

	@Override
	public void saveMaze(String name, String fileName) {

		if (!mazes.containsKey(name)) {
			message = "Maze " + name + " does not exist\n";
			setChanged();
			notifyObservers("display_message");

			return;
		}
		Maze3d maze = mazes.get(name);
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
			int size = maze.toByteArray().length;
			int count = 0;
			while (size > 255) {
				size = size - 255;
				count++;
			}
			out.write(size);
			out.write(count);
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			
			message = "Maze " + name + " is saved succesfully to the file: " + fileName + "\n";
			setChanged();
			notifyObservers("display_message");
		} catch (FileNotFoundException e) {
			message = "File '" + fileName + "' Not Found\n";
			setChanged();
			notifyObservers("display_message");

			return;
		} catch (IOException e) {
			message = "IOEXception\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
	}

	public void SolveMaze(String mazeName, String algorithm) {
//		Thread thread = new Thread(new Runnable() {
//		@Override
//			public void run() {		
		
		service.submit(new Callable<Maze3d>() {
				@Override
				public Maze3d call() throws Exception {
					Maze3dAdapter mAdapter = new Maze3dAdapter(getMaze(mazeName));
				switch (algorithm) {
				case "bfs":
					BFS bfs = new BFS();
					solution = bfs.search(mAdapter);
					solutions.put(mazeName, solution);
					
					setChanged();
					notifyObservers("display_solution");
					break;
				case "dfs":
					DFS dfs = new DFS();
					solution = dfs.search(mAdapter);
					setChanged();
					notifyObservers("display_solution");
//					System.out.println(solution2);
					break;
				default:
					message = "invalid input";
					setChanged();
					notifyObservers("display_message");
					break;
					}
					return null;
				}	
			});
	}
//		thread.start();
//		threads.add(thread);
	

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public HashMap<String, Solution> getSolutions() {
		return solutions;
	}

	@Override
	public void loadMaze(String fileName, String name) {

		if (mazes.containsKey(name)) {
			message = "Maze " + name + " Already exist and loaded succesfully\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		byte[] myarry = null;
		try {

			InputStream in = new MyDecompressorInputStream(new FileInputStream(fileName));
			int size = in.read();
			int count = in.read();
			if (count > 0) {
				count = count * 255;
				size = size + count;
			}
			myarry = new byte[size];
			in.read(myarry);
			in.close();
			mazes.put(name, new Maze3d(myarry));
			message = "maze " + name + " has been loaded succesfully\n";
			setChanged();
			notifyObservers("display_message");

		} catch (FileNotFoundException e) {
			message = "File: '" + fileName + "' Not Found\n";
			setChanged();
			notifyObservers("display_message");

			return;
		} catch (IOException e) {
			message = "input or output error";
			setChanged();
			notifyObservers("display_message");
			return;
		}

	}
	
	@Override
	public void displayCrossSectionByX(String section, String mazeName) {

		message = null;

		Maze3d maze = getMaze(mazeName);
			try 
			{
				//putting the last cross section in its variable
				maze2d = maze.getCrossSectionByX(Integer.parseInt(section));
				setChanged();
				notifyObservers("display_maze2d");
				
				message = "cross section by X^ \n";
				setChanged();
				notifyObservers("display_message");
				return;
			} 
			catch (NumberFormatException e) 
			{
				message ="Invalid parameters\n";
				setChanged();
				notifyObservers("display_message");
				return;
			} 
			catch (IndexOutOfBoundsException e) {
				message="x coordinate out of bounds\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
		
	}
	
	public int[][] getMaze2d() {
		return maze2d;
	}

	public void setMaze2d(int[][] maze2d) {
		this.maze2d = maze2d;
	}

	@Override
	public void displayCrossSectionByY(String section, String mazeName) {

		message = null;

		Maze3d maze = getMaze(mazeName);
			try 
			{
				//putting the last cross section in its variable
				maze2d = maze.getCrossSectionByY(Integer.parseInt(section));
				setChanged();
				notifyObservers("display_maze2d");

				message = "cross section by Y^ \n";
				setChanged();
				notifyObservers("display_message");
				return;
			} 
			catch (NumberFormatException e) 
			{
				message ="Invalid parameters\n";
				setChanged();
				notifyObservers("display_message");
				return;
			} 
			catch (IndexOutOfBoundsException e) {
				message="y coordinate out of bounds\n";
				setChanged();
				notifyObservers("display_message");
				return;
			}
		
	}
	
	public void saveToZip(String filename){
		
		try {
			GZIPOutputStream zip = new GZIPOutputStream(new FileOutputStream(filename));
			zip.write(solutions.size());
			 for(Entry<String, Solution> entry : solutions.entrySet()) 
			 {
				 	
				    String mazeName_key = entry.getKey();
				    Solution solutionMaze_value = entry.getValue();
					 for(Entry<String, Maze3d> names : mazes.entrySet()) 
					 {
						 String nameOfMaze = names.getKey();
						 Maze3d mymaze = names.getValue();

							 byte[] temp =nameOfMaze.getBytes(); 
							 zip.write(temp.length);
							 zip.write(temp);
	
					 }
					 int temp = mazeName_key.length();
					 int count= 0;
					 while(temp>255)
					 {
						 temp=temp-255;
						 count++;
					 }
				    zip.write(temp);
				    zip.write(count);
				    zip.write(mazeName_key.getBytes());

				    ArrayList<State> mySolutionStates = solutionMaze_value.getStates();
				    temp = mySolutionStates.size();
				    count = 0;
					 while(temp>255)
					 {
						 temp=temp-255;
						 count++;
					 }
					 zip.write(temp);
					 zip.write(count);
				    Maze3dState state=null;
				    String pos =null;
				
				    for (int i = 0; i < mySolutionStates.size(); i++)
				    {	
				    	state=(Maze3dState) mySolutionStates.get(i);
				    	zip.write(state.getCurrPlayerPosition().getX());
				    	zip.write(state.getCurrPlayerPosition().getY());
				    	zip.write(state.getCurrPlayerPosition().getZ());
				    	zip.write((int)state.getCost());			
				    	pos=mySolutionStates.get(i).getDescription();
						byte[] mybyte = pos.getBytes();
						zip.write(mybyte.length);
						zip.write(mybyte);
				    }

			 }
		zip.close(); 	 
		message = "Map saved\n";
		setChanged();
		notifyObservers("display_message");
		
		} catch (FileNotFoundException e) {
			this.message="File not found\n";
			setChanged();
			notifyObservers("display_message");
			return;
		} catch (IOException e) {
			this.message="IOEXception\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}
		
	}
	
	@Override
	public void LoadFromZip(String fileName)
	{
		Maze3d myMaze = null;
		Solution sol = null;
		Maze3dState state = null;
		Maze3dState cameFrom =null;
		String mazeName = null;
		byte [] name = null;
		try {
			GZIPInputStream zip = new GZIPInputStream(new FileInputStream(fileName));
			int solvedSize = zip.read();
			for (int j = 0; j < solvedSize; j++) 
			{
				name = new byte[zip.read()];
				zip.read(name);
				mazeName=new String(name);
				int size = zip.read();
				int count = zip.read();
				size = size + (count *255);
				byte[] bytemaze = new byte[size];
				zip.read(bytemaze);
				myMaze = new Maze3d(bytemaze);
				sol = new Solution();

				ArrayList<State> mystates= new ArrayList<State>();
				size = zip.read();
				count= zip.read();
				size = size + (count *255);
 				for (int i = 0; i < size; i++)
				{
					int x = zip.read();
					int y = zip.read();
					int z = zip.read();
					state = new Maze3dState(new Position(x, y, z));
					state.setCost(zip.read());
					state.setCameFrom(cameFrom);
					byte[]temp = new byte[zip.read()];
					zip.read(temp);
					String dis = new String(temp);
					state.setDescription(dis);
					cameFrom= state;
					
					mystates.add(state);
					
				}
				
				sol.setStates(mystates);
				mazes.put(mazeName, myMaze);
				solutions.put(mazeName, sol);
				
			}
			zip.close();
			message = "Map loaded\n";
			setChanged();
			notifyObservers("display_message");
		}  catch (IOException e)
		{
			this.message="IOEXception\n";
			setChanged();
			notifyObservers("display_message");
			return;
		}	
		
	}

}
