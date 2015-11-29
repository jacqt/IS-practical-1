package mars;

import search.*;

public class Solution {
  public static void main(String[] args) {
    MarsState initialConfiguration = new MarsState(new Planet(), 4, 4, 20);

    GoalTest goalTest = new MarsGoalTest();
    NodeFunction h = new MarsHeuristic();
    NodeFunction a_star = new AStarFunction(h);

    //tryAlgo("Graph - bfs", new GraphSearch(new BreadthFirstFrontier()), initialConfiguration, goalTest);
    //tryAlgo("Graph - dfs", new GraphSearch(new DepthFirstFrontier()), initialConfiguration, goalTest);
    //tryAlgo("Tree - bfs", new TreeSearch(new BreadthFirstFrontier()), initialConfiguration, goalTest);
    // tryAlgo("Tree - dfs", new TreeSearch(new DepthFirstFrontier()), initialConfiguration, goalTest); //- this causes an outOfMemoryError...
    //tryAlgo("Iterative deepening", new IterativeDeepeningTreeSearch(), initialConfiguration, goalTest);

    tryAlgo("A* Graph search", new GraphSearch(new BestFirstFrontier(a_star)), initialConfiguration, goalTest);
    tryAlgo("A* Tree search", new TreeSearch(new BestFirstFrontier(a_star)), initialConfiguration, goalTest);
  }

  public static void tryAlgo(String name, Search algo, State initialConfiguration, GoalTest goalTest) {
    Node solution = algo.findSolution(initialConfiguration, goalTest);
    MarsState finalState = (MarsState) solution.state;
    System.out.printf(
        "============================\n%s\n\nNumber of nodes generated: %d\nMax number of nodes stored: %d\nNumber of visited locations: %d\n\n",
        name,
        algo.numberOfNodesGenerated(),
        algo.maxNodesStored(),
        finalState.visitedLocations.size());
  }
}
