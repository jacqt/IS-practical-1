package search;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstFrontier implements Frontier {
  private Queue<Node> queue;
  private int maxNodes;

  public BreadthFirstFrontier() {
    this.queue = new LinkedList<Node>();
    this.maxNodes = 0;
  }

  public void add(Node newNode) {
    this.queue.add(newNode);
    this.maxNodes = Math.max(this.maxNodes, this.queue.size());
  }

  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  public void clear() {
    this.queue.clear();
    this.maxNodes = 0;
  }

  public Node pop() {
    return this.queue.remove();
  }

  public int maxNodesStored() {
    return this.maxNodes;
  }
}
