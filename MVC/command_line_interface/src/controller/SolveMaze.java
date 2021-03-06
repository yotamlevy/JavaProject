package controller;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class SolveMaze implements Command {

	
	private Model model;
	private View view;

	public SolveMaze(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void doCommand(String[] args) {
		if (args==null||args.length !=2)
		{
			view.displayMessage("Incorrect number of args, please type a valid command.\n");
			return;
		}
		String name = args[0];
		String algorithm = args[1];
		Maze3d maze = model.getMaze(name);
		if (maze == null){
			view.displayMessage("Maze " + "'"+ name +"'" + " not found\n");
		}else
			model.SolveMaze(name, algorithm);

	}

}
