package presenter;

import model.Model;
import view.View;

public class DisplaySolution implements Command {

	private View view;
	private Model model;
	
	
	public DisplaySolution(View view, Model model) {
		this.view = view;
		this.model = model;
	}


	@Override
	public void doCommand(String[] args) {
		
		view.displaySolution(model.getSolution());

	}

}
