package presenter;

import model.Model;
import view.View;

public class SaveZipMap implements Command {

	private Model model;
	private View view;
	
	public SaveZipMap(Model model, View view) {

		this.model = model;
		this.view = view;
	}

	@Override
	public void doCommand(String[] args) {
		if (args==null||args.length !=1)
		{
			view.displayMessage("Incorrect number of args, please type a valid command.\n");
			return;
		}
		String fileName = args[0];
			model.saveToZip(fileName);
		
	}

}
