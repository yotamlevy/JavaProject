package presenter;

import view.View;

public class FileSizeCommand implements Command {

	private View view;

	public FileSizeCommand(View view) {
		this.view = view;
	}

	@Override
	public void doCommand(String[] args) {
		if (args==null||args.length !=1){
			
			view.displayMessage("Incorrect number of args, please type a valid command.\n");
			return;
		}
		String fileName = args[0];
		view.fileSize(fileName);

	}

}
