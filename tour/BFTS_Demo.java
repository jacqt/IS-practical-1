package tour;

import search.*;

public class BFTS_Demo {
	public static void main(String[] args) {
		//Cities romania = SetUpRomania.getRomaniaMapSmall();
		Cities romania = SetUpRomania.getRomaniaMap();
		City startCity = romania.getState("Bucharest");

    // find the city farthest away
    romania.computeShortestDistances();
    int maxDist = 0;
    City farthestCity = null;
    for (City city : romania.getAllCities()) {
        int s = startCity.getShortestDistanceTo(city);
        if (s > maxDist) {
            farthestCity = city;
            maxDist = s;
        }
    }
    NodeFunction h = new TourHeuristic(startCity, romania);
    NodeFunction a_star = new AStarFunction(h);
		
		GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
    //tryAlgo("Graph - bfs", new GraphSearch(new BreadthFirstFrontier()), new TourState(startCity), goalTest);
    //tryAlgo("Graph - dfs", new GraphSearch(new DepthFirstFrontier()), new TourState(startCity), goalTest);
    //tryAlgo("Tree - bfs", new TreeSearch(new BreadthFirstFrontier()), new TourState(startCity), goalTest);
    //tryAlgo("Tree - dfs", new TreeSearch(new DepthFirstFrontier()), new TourState(startCity), goalTest); TOO SLOW
    //tryAlgo("Iterative deepening", new IterativeDeepeningTreeSearch(), new TourState(startCity), goalTest);


    tryAlgo("A* Graph search", new GraphSearch(new BestFirstFrontier(a_star)), new TourState(startCity), goalTest);
    tryAlgo("A* Tree search", new TreeSearch(new BestFirstFrontier(a_star)), new TourState(startCity), goalTest);
	}

  public static void tryAlgo(String name, Search algo, TourState initialConfiguration, GoalTest goalTest) {
    Node solution = algo.findSolution(initialConfiguration, goalTest);
    System.out.printf("============================\n%s\n\nNumber of nodes generated: %d\nMax number of nodes stored: %d\n\n",
                      name,
                      algo.numberOfNodesGenerated(),
                      algo.maxNodesStored()); 
  }
}
