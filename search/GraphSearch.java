package search;

import java.util.Set;
import java.util.HashSet;

public class GraphSearch implements Search {
  private Frontier frontier;
  private int generated;

  public GraphSearch(Frontier frontier) {
    this.frontier = frontier;
    this.generated = 0;
  }

	public Node findSolution(State initialConfiguration, GoalTest goalTest) {
    this.generated = 0;
    Set<State> seenStates = new HashSet<State>();
    this.frontier.clear();
		this.frontier.add(new Node(null, null, initialConfiguration, 0));
    while (!this.frontier.isEmpty()) {
			Node node = this.frontier.pop();
			if (goalTest.isGoal(node.state))
				return node;
			else {
				for (Action action : node.state.getApplicableActions()) {
					State newState = node.state.getActionResult(action);
          if (!seenStates.contains(newState)) {
            seenStates.add(newState);
            this.frontier.add(new Node(node, action, newState, node.depth + action.cost()));
            this.generated += 1;
          }
				}
			}
    }
		return null;
	}

  public int numberOfNodesGenerated() {
    return this.generated;
  }

  public int maxNodesStored() {
    return this.frontier.maxNodesStored();
  }
}
