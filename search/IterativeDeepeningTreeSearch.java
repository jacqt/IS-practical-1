package search;

import java.util.Set;

public class IterativeDeepeningTreeSearch implements Search {
  protected int generated = 0;
  private Frontier frontier;
  public IterativeDeepeningTreeSearch() {
  }

	public Node findSolution(State initialConfiguration, GoalTest goalTest) {
    this.generated = 0;
    this.frontier = new DepthFirstFrontier();
    this.frontier.add(new Node(null, null, initialConfiguration, 0));

    int currentDepth = 0;
    while (currentDepth < 200) {
      while (!frontier.isEmpty()) {
        Node node = frontier.pop();
        if (goalTest.isGoal(node.state))
          return node;
        else {
          if (node.depth == currentDepth) {
            continue;
          }
          for (Action action : node.state.getApplicableActions()) {
            State newState = node.state.getActionResult(action);
            this.frontier.add(new Node(node, action, newState, node.depth + 1));
            this.generated += 1;
          }
        }
      }

      currentDepth += 1;
      this.frontier = new DepthFirstFrontier();
      this.frontier.add(new Node(null, null, initialConfiguration, 0));
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
