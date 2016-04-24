package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import algorithms.domains.Action;
import algorithms.domains.Searchable;
import algorithms.domains.State;

public class BFS extends CommonSearcherWithPQueue {

	@Override
	public Solution search(Searchable s) {
		openList.add(s.getStartState());
		
		while (!openList.isEmpty()) {
			State state = openList.poll();
			closedList.add(state);
			
			if (state.equals(s.getGoalState())) 
				return backtrace(state);
			
			HashMap<Action, State> actions = s.getAllPossibleActions(state);
			for (Entry<Action, State> entry: actions.entrySet()) {
				Action action = entry.getKey();
				State successor = entry.getValue();
				
				if (!openList.contains(successor) && !closedList.contains(successor)) {
					successor.setCameFrom(state);
					successor.setCost(state.getCost() + action.getCost());
					openList.add(successor);
				}
				else if (state.getCost() + action.getCost() < successor.getCost()) {
					
					successor.setCameFrom(state);
					successor.setCost(state.getCost() + action.getCost());
					
					// update priority in queue by removing and adding the state
					openList.remove(successor);
					openList.add(successor);					
				}
			}
		}
		return null; // won't reach this line never
	}

	

}