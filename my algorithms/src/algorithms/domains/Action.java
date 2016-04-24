package algorithms.domains;

public class Action {
	private String description;
	private double cost;
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
	public Action() {}
	public Action(String description, double cost) {		
		this.description = description;
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return description;
	}	
}
