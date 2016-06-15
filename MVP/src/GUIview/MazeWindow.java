package GUIview;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MazeWindow extends BasicWindow{
	
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
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
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String command = "display aaa";
				setChanged();
				notifyObservers(command);				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//MazeDisplayer maze=new Maze2D(shell, SWT.BORDER);		
		MazeDisplayer maze=new Maze3D(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		
		Button exitButton=new Button(shell, SWT.PUSH);
		exitButton.setText("exit");
		exitButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));
		exitButton.setEnabled(false);
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {


			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		exitButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				btnGenerateMaze.setEnabled(true);
				exitButton.setEnabled(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	}

	@Override
	public void run() {
		this.start();
		
	}

}
