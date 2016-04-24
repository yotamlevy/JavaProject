package algorithms.search;

import java.util.ArrayList;

import algorithms.domains.State;

public class Solution {
	private ArrayList<State> states;

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State s: states) {
			sb.append(s).append("\n");
		}
		return sb.toString();
	}
	
	public int StatesEvaluated(){
		return states.size();
	}
	
}