package search;

public interface Frontier {
	public void add(Node newNode);
  public void clear();
  public boolean isEmpty();
  public Node pop();
  public int maxNodesStored();
}
