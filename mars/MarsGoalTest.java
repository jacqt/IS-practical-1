package mars;

import search.GoalTest;
import search.State;

public class MarsGoalTest implements GoalTest {
	public boolean isGoal(State state) {
		MarsState marsState = (MarsState)state;
    return marsState.batteryLife == 0;
	}
}
