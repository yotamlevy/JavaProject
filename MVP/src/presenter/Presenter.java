package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

public class Presenter implements Observer {
	
	private Model model;
	private View view;
	private HashMap<String, Command> viewCommands;
	private HashMap<String, Command> modelCommands;
	private Properties prop;
	
	public Presenter(Model model, View view, Properties prop) {
		this.model = model;
		this.view = view;
		this.prop = prop;
		buildCommands();
	}
	
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	private void buildCommands() {
		viewCommands = new HashMap<String, Command>();
		viewCommands.put("generate_maze_3d", new GenerateMazeCommand(model, view, prop));
		viewCommands.put("display" , new DisplayMazeCommand(model, view));
		viewCommands.put("file_size" , new FileSizeCommand(view));
		viewCommands.put("dir", new DirCommand(view));
		viewCommands.put("save_maze", new SaveMazeCommand(model, view));
		viewCommands.put("load_maze", new LoadMazeCommand(model, view));
		viewCommands.put("solve", new SolveMaze(model, view, prop));
		viewCommands.put("cross_section_x", new DisplayCrossSectionByXCommand(view, model));
		viewCommands.put("cross_section_y", new DisplayCrossSectionByYCommand(view, model));
		viewCommands.put("help", new HelpCommand(view));
		viewCommands.put("save_zip_map", new SaveZipMap(model, view));
		viewCommands.put("load_zip_map", new LoadZipMap(model, view));
		viewCommands.put("exit", new ExitCommnd(model));
		
		modelCommands = new HashMap<String, Command>();
		modelCommands.put("display_message", new DisplayMessageCommand(model, view));
		modelCommands.put("display_maze2d", new DisplayMaze2d(model, view));
		modelCommands.put("display_solution", new DisplaySolution(view, model));

		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == model) {
	
			String commandName = (String)arg;
			Command command = modelCommands.get(commandName);
			if (command == null) {
				System.out.println("Command not found\n");
				return;
				}
			command.doCommand(null);
		}
		else if (o == view) {
			String commandLine = (String)arg;
			String[] arr = commandLine.split(" ");
			String commandName = arr[0];
			String[] args = new String[arr.length - 1];
			System.arraycopy(arr, 1, args, 0, arr.length - 1);
			
			Command command = viewCommands.get(commandName);
			if (command == null) {
				System.out.println("Command not found\n");
				return;
				}
			command.doCommand(args);
		}
	}
}