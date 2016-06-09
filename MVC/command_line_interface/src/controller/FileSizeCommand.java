package controller;

import view.View;

public class FileSizeCommand implements Command {

	private View view;

	public FileSizeCommand(View view) {
		this.view = view;
	}

	@Override
	public void doCommand(String[] args) {
		String fileName = args[0];
		view.fileSize(fileName);

	}

}
