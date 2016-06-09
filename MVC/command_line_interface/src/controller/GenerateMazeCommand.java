package controller;

import model.Model;

public class GenerateMazeCommand implements Command {
	private Model model;
	public GenerateMazeCommand(Model model) {
		this.model = model;
	}	
	
	@Override
	public void doCommand(String[] args) {
		String name = args[0];
		int height = Integer.parseInt(args[1]);
		int width = Integer.parseInt(args[2]);
		int depth = Integer.parseInt(args[3]);
		
		model.generateMaze(name, height, width, depth);
	}

}
