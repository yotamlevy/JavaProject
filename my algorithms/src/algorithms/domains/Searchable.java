package algorithms.domains;

import java.util.HashMap;

public interface Searchable {
	State getStartState();
	State getGoalState();
	HashMap<Action, State> getAllPossibleActions(State state);
}
