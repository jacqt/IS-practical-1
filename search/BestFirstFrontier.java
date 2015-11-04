package search;

import java.util.PriorityQueue;

public class BestFirstFrontier implements Frontier {
  private PriorityQueue<Node> queue;
  private int maxNodes;
  private NodeFunction scorer;

  public BestFirstFrontier(NodeFunction scorer) {
    this.queue = new PriorityQueue<Node>();
    this.maxNodes = 0;
    this.scorer = scorer;
  }

  public void add(Node newNode) {
    newNode.score = this.scorer.scoreNode(newNode);
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
