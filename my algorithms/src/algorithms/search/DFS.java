package algorithms.search;

import java.util.HashMap;
import java.util.HashSet;

import algorithms.domains.Action;
import algorithms.domains.Searchable;
import algorithms.domains.State;

public class DFS extends CommonSearcher {
	private HashSet<State> visitedStates = new HashSet<State>();
	private Solution solution;
	
	/**
	 * This function finds a solution to a searchable problem
	 * @param s the problem
	 * @return solution
	 */
	@Override	
	public Solution search(Searchable s) {
		
		dfs(s, s.getStartState());		
		return solution;
	}
	
	private void dfs(Searchable s, State state) {
		if (state.equals(s.getGoalState())) {
			solution = backtrace(state);
		}
		
		visitedStates.add(state);
		
		HashMap<Action,State> actions = s.getAllPossibleActions(state);
		for(State neighbor: actions.values())
		{
			//todo: IF (neighbor is not in the array bounds)
			
			if (!visitedStates.contains(neighbor)) {
				neighbor.setCameFrom(state);
				dfs(s, neighbor);					
			} 			
		}
	}

}
