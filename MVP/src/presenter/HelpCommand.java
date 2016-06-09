package presenter;

import view.View;

public class HelpCommand implements Command {
	
	private View view;
	
	
	
	public HelpCommand(View view) {
		this.view = view;
	}

	@Override
	public void doCommand(String[] args) {
		view.displayHelp();

	}

}
