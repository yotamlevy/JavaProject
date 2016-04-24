package maze3d.domains;

import algorithms.domains.Action;
import algorithms.mazeGenerators.Direction;

public class Maze3dAction extends Action {
		
	public static final double mazeMovementCost = 1;
	private Direction move;
	
	public Maze3dAction(Direction move) {
		super(move.toString(), mazeMovementCost);
	}
	
}