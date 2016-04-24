package algorithms.search;
import java.util.ArrayList;
import java.util.PriorityQueue;

import algorithms.domains.Searchable;
import algorithms.domains.State;

public abstract class CommonSearcher implements Searcher {
	
	
	public CommonSearcher() {
		
	}
	
	protected Solution backtrace(State state) {
		State s = state;
		ArrayList<State> states = new ArrayList<State>();
		while (s != null) {
			states.add(0, s);	
			s = s.getCameFrom();
		}
		Solution solution = new Solution();
		solution.setStates(states);
		return solution;
	}
}
