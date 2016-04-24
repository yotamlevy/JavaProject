package algorithms.domains;

import java.util.Comparator;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class State implements Comparable<State> {
	private String description;
	private double cost;
	private State cameFrom;	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public State getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	@Override
	public int compareTo(State s) {
		return (int)(this.cost - s.cost);
	}
	
	@Override
	public boolean equals(Object arg0) {
		State state = (State)arg0;
		return description.equals(state.description);
	}		
	
	@Override
	public int hashCode() {
		return description.hashCode();
	}
	
	@Override
	public String toString() {
		return description;
	}


}
