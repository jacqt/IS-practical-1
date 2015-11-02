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

    tryAlgo("Graph - bfs", new GraphSearch(new BreadthFirstFrontier()), initialConfiguration, goalTest);
    tryAlgo("Graph - dfs", new GraphSearch(new DepthFirstFrontier()), initialConfiguration, goalTest);
    tryAlgo("Tree - bfs", new GraphSearch(new BreadthFirstFrontier()), initialConfiguration, goalTest);
    // tryAlgo("Tree - dfs", new TreeSearch(new DepthFirstFrontier())); - this takes WAY too long
    tryAlgo("Iterative deepening", new IterativeDeepeningTreeSearch(), initialConfiguration, goalTest);
	}

  public static void tryAlgo(String name, Search algo, Tiles initialConfiguration, GoalTest goalTest) {
    algo.findSolution(initialConfiguration, goalTest);
    System.out.printf("============================\n%s\n\nNumber of nodes generated: %d\nMax number of nodes stored: %d\n\n",
                      name,
                      algo.numberOfNodesGenerated(),
                      algo.maxNodesStored()); 
  }
}
