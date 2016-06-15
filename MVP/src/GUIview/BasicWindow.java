package GUIview;

import java.util.HashMap;
import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import presenter.Properties;

public abstract class BasicWindow extends Observable implements Runnable {
	protected Display display;
	protected Shell shell;
	private HashMap<String, Listener> listenerCollection;

	public BasicWindow() {
		display = new Display();
		shell = new Shell(display);
	}

	public BasicWindow(String title, int width, int height) {
		this.display = new Display();
		this.shell = new Shell(display);
		shell.setBounds(400, 0, width, height);
		shell.setText(title);
	}

	public BasicWindow(String string, int width, int height, HashMap<String, Listener> listenerCollection) {
		this.display = new Display();
		this.shell = new Shell(display);
		this.shell.setSize(width,height);
		shell.setText(string);
		this.listenerCollection=listenerCollection;
	}

	public abstract void initWidgets();

	public void start() {
		
		initWidgets();
		shell.open();
		
		// main event loop
		while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components
	}

	public Properties getObj() {
		// TODO Auto-generated method stub
		return null;
	}	
}
