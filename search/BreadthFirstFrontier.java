package search;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstFrontier implements Frontier {
  private Queue<Node> queue;

  public BreadthFirstFrontier() {
    this.queue = new LinkedList<Node>();
  }

  public void add(Node newNode) {
    this.queue.add(newNode);
  }

  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  public void clear() {
    this.queue.clear();
  }

  public Node pop() {
    return this.queue.remove();
  }
}
