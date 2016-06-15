package bootGUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Observable;


import GUIview.MazeDisplayer;
import GUIview.MazeWindow;
import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import presenter.PropertiesXMLCreator;
import view.MyView;
import view.View;


public class RunWithGUI {

//	public static void main(String[] args) {
//		MyModel model = new MyModel();
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter writer = new PrintWriter(System.out);
//		MyView view = new MyView(reader, writer);
//		
//		Presenter presenter = new Presenter(model, view);
//		view.addObserver(presenter);
//		model.addObserver(presenter);
//		
//		view.start();		
//	}
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		PropertiesXMLCreator pCraetor = new PropertiesXMLCreator("Properties", 300, 400);
		pCraetor.run();
		prop = pCraetor.getproperties();
		
		MyModel model = new MyModel();
		
		String ui = prop.getTypeOfUserInterfece();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);
		View view = null;
		
		if (ui.equals("GUI"))
		{
			MazeWindow mazeWindow = new MazeWindow("The Maze", 800, 600);
//			GUI view = new GUI();
//			Presenter presenter = new Presenter(model, view, prop);
//			mazeWindow.addObserver(presenter);
//			model.addObserver(presenter);
			mazeWindow.run();	
		}
		else if (ui.equals("CLI"))
		{
			
			view  = new MyView(in, out);
			Presenter presenter = new Presenter(model, view, prop);
			((Observable) view).addObserver(presenter);
			model.addObserver(presenter);
			
			view.start();
			
			
		}
			
	}

}
