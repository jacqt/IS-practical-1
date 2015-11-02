package tour;

import search.*;

public class BFTS_Demo {
	public static void main(String[] args) {
		Cities romania = SetUpRomania.getRomaniaMapSmall();
		City startCity = romania.getState("Bucharest");
		
		GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
		//Node solution = BreadthFirstTreeSearch.findSolution(new TourState(startCity), goalTest);
		//new TourPrinting().printSolution(solution);
    tryAlgo("Graph - bfs", new GraphSearch(new BreadthFirstFrontier()), new TourState(startCity), goalTest);
    tryAlgo("Graph - dfs", new GraphSearch(new DepthFirstFrontier()), new TourState(startCity), goalTest);
    tryAlgo("Tree - bfs", new GraphSearch(new BreadthFirstFrontier()), new TourState(startCity), goalTest);
    //tryAlgo("Tree - dfs", new TreeSearch(new DepthFirstFrontier()), new TourState(startCity), goalTest); TOO SLOW
    tryAlgo("Iterative deepening", new IterativeDeepeningTreeSearch(), new TourState(startCity), goalTest);
	}

  public static void tryAlgo(String name, Search algo, TourState initialConfiguration, GoalTest goalTest) {
    algo.findSolution(initialConfiguration, goalTest);
    System.out.printf("============================\n%s\n\nNumber of nodes generated: %d\nMax number of nodes stored: %d\n\n",
                      name,
                      algo.numberOfNodesGenerated(),
                      algo.maxNodesStored()); 
  }
}
