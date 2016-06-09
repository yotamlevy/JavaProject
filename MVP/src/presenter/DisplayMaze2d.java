package presenter;

import model.Model;
import view.View;

public class DisplayMaze2d implements Command {
	
	View view;
	Model model;

	public DisplayMaze2d(Model model, View view) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void doCommand(String[] args) {

		
		view.displayMaze2d(model.getMaze2d());

	}

}
