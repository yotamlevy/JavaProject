package algorithms.search;

import java.util.PriorityQueue;

import algorithms.domains.Searchable;
import algorithms.domains.State;

public abstract class CommonSearcherWithPQueue extends CommonSearcher {
	protected PriorityQueue<State> openList;
	protected PriorityQueue<State> closedList;
	
	public CommonSearcherWithPQueue(){
		openList = new PriorityQueue<State>();
		closedList = new PriorityQueue<State>();
	}
	
}
