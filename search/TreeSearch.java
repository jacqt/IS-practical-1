package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TreeSearch implements Search{
  private Frontier frontier;

  public TreeSearch(Frontier frontier) {
    this.frontier = frontier;
  }

	public Node findSolution(State initialConfiguration, GoalTest goalTest) {
    this.frontier.clear();
		this.frontier.add(new Node(null, null, initialConfiguration));
    while (!this.frontier.isEmpty()) {
			Node node = this.frontier.pop();
			if (goalTest.isGoal(node.state))
				return node;
			else {
				for (Action action : node.state.getApplicableActions()) {
					State newState = node.state.getActionResult(action);
					this.frontier.add(new Node(node, action, newState));
				}
			}
    }
		return null;
	}
}
