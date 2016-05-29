package controller;

import model.Model;
import view.View;

public class LoadMazeCommand implements Command{

	private Model model;
	private View view;
	
	public LoadMazeCommand(Model model,View view)
	{
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void doCommand(String[] args) 
	{		
		if (args==null||args.length !=2)
		{
			view.displayMessage("Incorrect number of args, please type a valid command.\n");
			return;
		}
		String name = args[0];
		String fileName = args[1];
		
		model.loadMaze(fileName, name);
	}


}
