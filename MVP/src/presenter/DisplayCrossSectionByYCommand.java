package presenter;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class DisplayCrossSectionByYCommand implements Command {

	private View view;
	private Model model; 
	
	public DisplayCrossSectionByYCommand(View view, Model model) {
		super();
		this.view = view;
		this.model = model;
	}
	

	@Override
	public void doCommand(String[] args) {
		
		String section = args[0];
		String mazeName = args[1];
		Maze3d maze = model.getMaze(mazeName);
		if (maze == null){
			view.displayMessage("Maze " + "'"+ mazeName +"'" + " not found\n");
			return;
		}
		if(args.length != 2){
			view.displayMessage("invalid number of arguments\n");
			return;
		}
		else
			model.displayCrossSectionByY(section, mazeName);
		
		
	}
	

}