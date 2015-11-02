package search;

public interface Search {
	public Node findSolution(State initialConfiguration, GoalTest goalTest);

  public int numberOfNodesGenerated();

  public int maxNodesStored();
}
