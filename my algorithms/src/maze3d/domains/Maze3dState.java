package maze3d.domains;

import algorithms.domains.State;

import algorithms.mazeGenerators.Position;

public class Maze3dState extends State {
	private Position currPlayerPosition;

	public Position getCurrPlayerPosition() {
		return currPlayerPosition;
	}

	public void setCurrPlayerPosition(Position currPlayerPosition) {
		this.currPlayerPosition = currPlayerPosition;
	}
	
	public Maze3dState(Position pos) {
		this.currPlayerPosition = pos;
		this.setDescription(pos.toString());
	}
	

	
	
}
