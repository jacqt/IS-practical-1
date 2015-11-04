package search;

import java.util.Set;

public class TreeSearch implements Search{
  private Frontier frontier;
  private int generated;

  public TreeSearch(Frontier frontier) {
    this.frontier = frontier;
    this.generated = 0;
  }

	public Node findSolution(State initialConfiguration, GoalTest goalTest) {
    this.generated = 0;
    this.frontier.clear();
		this.frontier.add(new Node(null, null, initialConfiguration, 0));
    while (!this.frontier.isEmpty()) {
			Node node = this.frontier.pop();
			if (goalTest.isGoal(node.state))
				return node;
			else {
				for (Action action : node.state.getApplicableActions()) {
					State newState = node.state.getActionResult(action);
					this.frontier.add(new Node(node, action, newState, node.depth + action.cost() ));
          this.generated += 1;
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
