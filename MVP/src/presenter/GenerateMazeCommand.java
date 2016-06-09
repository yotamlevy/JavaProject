package presenter;

import model.Model;
import view.View;

public class GenerateMazeCommand implements Command {
	private Model model;
	private View view; 
	
	public GenerateMazeCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}	
	
	@Override
	public void doCommand(String[] args) {
		if (args==null||args.length !=4){
			
			view.displayMessage("Incorrect number of args, please type a valid command.\n");
			return;
		}
		String name = args[0];
		int height = Integer.parseInt(args[1]);
		int width = Integer.parseInt(args[2]);
		int depth = Integer.parseInt(args[3]);
		
		model.generateMaze(name, height, width, depth);
	}

}
