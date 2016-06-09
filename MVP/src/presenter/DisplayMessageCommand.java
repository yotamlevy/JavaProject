package presenter;

import model.Model;
import view.View;

public class DisplayMessageCommand implements Command {

	private View view;
	private Model model;
	
	public DisplayMessageCommand(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void doCommand(String[] args) {
		String msg = model.getMessage();
		view.displayMessage(msg);
	}

}
