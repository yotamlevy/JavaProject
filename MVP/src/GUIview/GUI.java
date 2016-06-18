package GUIview;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.*;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Presenter;
import view.View;

public class GUI extends BasicWindow implements View{
	
	private MessageBox messageBox;
	private boolean isStartedGame;
	private String n;
	private View view;
	public int[][] mazeToPaint;
	private Maze3d maze3d;
	MenuItem solve;
	private KeyListener canvasKeyListener;
	private Boolean keyListenerActivator = true;
	private Position characterPos;
	
	public GUI(String title, int width, int height) {
		super(title, width, height);
	}
	
	/**
	 * This method sends message to the user
	 * @param s - the message 
	 */
	public void sendMessage(String s)
	{	
		messageBox.setMessage(s);
		messageBox.open();
	}
	
	@Override
	public void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		
		Composite buttonsGroup = new Composite(shell, SWT.BORDER);
		buttonsGroup.setLayout(new FillLayout(SWT.VERTICAL));
		//buttonsGroup.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));
		
		Button btnGenerateMaze = new Button(buttonsGroup, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");
		
		Button btnSolveMaze = new Button(buttonsGroup, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		
		Button btnDisplayMaze = new Button(buttonsGroup, SWT.PUSH);
		btnDisplayMaze.setText("Display maze");
		
		Button btnSaveMaze = new Button(buttonsGroup, SWT.PUSH);
		btnSaveMaze.setText("Save maze");
		
		Button btnLoadMaze = new Button(buttonsGroup, SWT.PUSH);
		btnLoadMaze.setText("Load maze");
		
//		maze=new Maze3D(shell, SWT.BORDER);
//		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,5));
//		createMenuBar();
		
		//MazeDisplayer maze=new Maze2D(shell, SWT.BORDER);		
		MazeDisplayer maze=new Maze3D(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		
		Button exitButton=new Button(shell, SWT.PUSH);
		exitButton.setText("exit");
		exitButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell selections = new Shell(display,SWT.APPLICATION_MODAL | SWT.SHELL_TRIM);
				selections.setLayout(new GridLayout(2, false));
				selections.setText("Maze Prefferances");
				
				GridData labels = new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1);
				GridData texts = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
				
				Label name = new Label(selections, SWT.READ_ONLY);
				name.setLayoutData(labels);
				name.setText("name:");
				Text mazeName = new Text(selections, SWT.BORDER);
				mazeName.setLayoutData(texts);
				
				
				Label floorsText = new Label(selections, SWT.READ_ONLY);
				floorsText.setText("Floors:");
				floorsText.setLayoutData(labels);
				Text floors = new Text(selections, SWT.BORDER);
				floors.setLayoutData(texts);
				
				Label rowsTesxt = new Label(selections, SWT.READ_ONLY);
				rowsTesxt.setText("Rows:");
				rowsTesxt.setLayoutData(labels);
				Text rows = new Text(selections, SWT.BORDER);
				rows.setLayoutData(texts);
				
				Label colsText = new Label(selections, SWT.READ_ONLY);
				colsText.setText("Columns:");
				colsText.setLayoutData(labels);
				Text cols = new Text(selections, SWT.BORDER);
				cols.setLayoutData(texts);
				
				Button generate = new Button(selections, SWT.PUSH);
				generate.setText("Generate!");
				generate.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 2, 1));
				
				
				generate.addSelectionListener(new SelectionListener()
				{
					
					@Override
					public void widgetSelected(SelectionEvent arg0)
					{
						String command = "generate_maze_3d ";
						n = mazeName.getText();
						command +=  n + ' ' + floors.getText() + ' ' + rows.getText() + ' ' + cols.getText();
						setChanged();
						notifyObservers(command);
						selections.close();
						
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {}
				});
			
				selections.pack();
				selections.open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)	{}
		});
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				if (n == null)
				{
					sendMessage("you must generate or load a maze first");
					isStartedGame = false;
					return;
				}
//				solve.setEnabled(true);

				String command = "display " + n;
				setChanged();
				notifyObservers(command);
				mazeToPaint = maze3d.getCrossSectionByX(maze3d.getStartPosition().getX());
				maze.setMazeData(mazeToPaint);
				
				maze.setCurrMaze(maze3d);         
//				maze.setCharacterPosition(maze3d.getStartPosition().getY(), maze3d.getStartPosition().getZ());
				maze.redraw();
				isStartedGame = true;
				maze.forceFocus();
				

				maze.addKeyListener(new KeyListener() {
					
					@Override
					public void keyReleased(KeyEvent arg0) {
							
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						
						switch (e.keyCode) {
						case SWT.ARROW_LEFT:
							maze.move(Maze3D.LEFT);
							break;
						case SWT.ARROW_RIGHT:
							maze.move(Maze3D.RIGHT);
							break;
						case SWT.ARROW_UP:
							maze.move(Maze3D.UP);
							break;
						case SWT.ARROW_DOWN:
							maze.move(Maze3D.DOWN);
							break;
						case SWT.PAGE_UP:
							maze.move(Maze3D.UPWARDS);
							break;
						case SWT.PAGE_DOWN:
							maze.move(Maze3D.DOWNWARDS);
							break;
						
					}
				}
			});
		}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		exitButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String command = "exit";
				setChanged();
				notifyObservers(command);
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
				
  }

	
	@Override
	public void run() {
		this.start();
		
	}


	
	public void setMaze3d(Maze3d maze)
	{
		this.maze3d = maze;
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze2d(int[][] maze2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution sol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileSize(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dirPath(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHelp() {
		// TODO Auto-generated method stub
		
	}

}
