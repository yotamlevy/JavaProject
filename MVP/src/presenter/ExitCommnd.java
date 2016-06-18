package presenter;

import model.Model;

public class ExitCommnd implements Command {

	private Model model;
	
	public ExitCommnd(Model model) {
		super();
		this.model = model;
	}

	@Override
	public void doCommand(String[] args) {
		
		model.exit();
	}

}
