package presenter;

import view.View;

public class DirCommand implements Command {

	private View view;
	
	public DirCommand(View view) {
		super();
		this.view = view;
	}


	@Override
	public void doCommand(String[] args) {
		if (args==null||args.length !=1){
			
			view.displayMessage("Incorrect number of args, please type a valid command.\n");
			return;
		}
		String path = args[0];
		view.dirPath(path);

	}

}
