package npuzzle;

import search.*;

public class BFTS_Demo {
  public static void main(String[] args) {
    Tiles initialConfiguration = new Tiles(new int[][] {
      { 7, 4, 2 },
        { 8, 1, 3 },
        { 5, 0, 6 }
    });

    GoalTest goalTest = new TilesGoalTest();

    NodeFunction h = new MisplacedTilesHeuristicsFunction();
    NodeFunction a_star = new AStarFunction(h);

    //tryAlgo("Graph - bfs", new GraphSearch(new BreadthFirstFrontier()), initialConfiguration, goalTest);
    //tryAlgo("Graph - dfs", new GraphSearch(new DepthFirstFrontier()), initialConfiguration, goalTest);
    //tryAlgo("Tree - bfs", new TreeSearch(new BreadthFirstFrontier()), initialConfiguration, goalTest);
    // tryAlgo("Tree - dfs", new TreeSearch(new DepthFirstFrontier()), initialConfiguration, goalTest); //- this causes an outOfMemoryError...
    //tryAlgo("Iterative deepening", new IterativeDeepeningTreeSearch(), initialConfiguration, goalTest);

    tryAlgo("A* Graph search", new GraphSearch(new BestFirstFrontier(a_star)), initialConfiguration, goalTest);
    tryAlgo("A* Tree search", new TreeSearch(new BestFirstFrontier(a_star)), initialConfiguration, goalTest);
  }

  public static void tryAlgo(String name, Search algo, Tiles initialConfiguration, GoalTest goalTest) {
    Node solution = algo.findSolution(initialConfiguration, goalTest);
    System.out.printf("============================\n%s\n\nNumber of nodes generated: %d\nMax number of nodes stored: %d\n\n",
        name,
        algo.numberOfNodesGenerated(),
        algo.maxNodesStored()); 
  }
}
