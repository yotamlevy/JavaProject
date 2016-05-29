package controller;

import view.View;

import java.util.HashMap;

import model.Model;

public interface Controller {
	void setModel(Model model);
	void setView(View view);
	void displayMessage(String message);	
	void generateCommands();
}
