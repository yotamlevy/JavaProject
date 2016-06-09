package controller;

import view.View;

public class DirCommand implements Command {

	private View view;
	
	public DirCommand(View view) {
		super();
		this.view = view;
	}


	@Override
	public void doCommand(String[] args) {
		String path = args[0];
		view.dirPath(path);

	}

}
