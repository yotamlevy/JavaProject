package algorithms.demo;

import java.util.HashMap;

import algorithms.domains.Action;
import algorithms.domains.Searchable;
import algorithms.domains.State;
import maze3d.domains.Maze3dState;
import algorithms.mazeGenerators.Direction;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class Maze3dAdapter implements Searchable {
	
	private Maze3d maze;
	private static final int MOVEMENT_COST = 1;
	
	public Maze3dAdapter(Maze3d maze) {
		this.maze = maze;
	}

	@Override
	public State getStartState() {
		Maze3dState startState = new Maze3dState(maze.getStartPosition());
		return startState;
	}

	@Override
	public State getGoalState() {
		Maze3dState goalState = new Maze3dState(maze.getGoalPosition());
		return goalState;
	}
	
	private Position getNextPosition(Position currPos, String d) {
		switch (d) {
		case "Up":
			return new Position(currPos.getX() + 1, currPos.getY(), currPos.getZ());
		case "Down":
			return new Position(currPos.getX() - 1, currPos.getY(), currPos.getZ());
		case "Right":
			return new Position(currPos.getX(), currPos.getY() + 1, currPos.getZ());			
		case "Left":
			return new Position(currPos.getX(), currPos.getY() - 1, currPos.getZ());
		case "Forward":
			return new Position(currPos.getX(), currPos.getY(), currPos.getZ() + 1);	
		case "Backward":
			return new Position(currPos.getX(), currPos.getY(), currPos.getZ() - 1);
		}
		return null;
	}

	@Override
	public HashMap<Action, State> getAllPossibleActions(State state) {
		Maze3dState mazeState = (Maze3dState)state;
		Position pos = mazeState.getCurrPlayerPosition();
		String[] directions = maze.getPossibleMoves(pos);
		
		HashMap<Action, State> actions = new HashMap<Action, State>();
		for (String d: directions) {
			Action action = new Action(d, MOVEMENT_COST);
			Maze3dState newState = new Maze3dState(getNextPosition(pos, d));
			
			actions.put(action, newState);
		}
		
		return actions;
	}

}
