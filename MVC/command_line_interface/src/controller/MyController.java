package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class MyController implements Controller {
	private Model model;
	private View view;
	private HashMap<String, Command> commands;
	
	@Override
	public void displayMessage(String message) {
		view.displayMessage(message);
	}

	@Override
	public void setModel(Model model) {
		this.model = model;		
	}

	@Override
	public void setView(View view) {		
		this.view = view;	
	}	
	
	
	public void generateCommands() {
		commands = new HashMap<String, Command>();
		commands.put("generate_maze_3d", new GenerateMazeCommand(model));
		commands.put("save_maze", new SaveMazeCommand(model, view));
		commands.put("display", new DisplayMazeCommand(model, view));
		commands.put("solve", new SolveMaze(model, view));
		commands.put("load_maze", new LoadMazeCommand(model, view));
		commands.put("file_size", new FileSizeCommand(view));
		commands.put("dir", new DirCommand(view));
		view.sendCommands(commands);
	}

}
