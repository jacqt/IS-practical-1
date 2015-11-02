package search;

import java.util.Stack;
import java.lang.*;

public class DepthFirstFrontier implements Frontier {
  private Stack<Node> stack;
  private int maxNodes;

  public DepthFirstFrontier() {
    this.stack = new Stack<Node>();
    this.maxNodes = 0;
  }

  public void add(Node newNode) {
    this.stack.push(newNode);
    this.maxNodes = Math.max(this.maxNodes, this.stack.size());
  }

  public boolean isEmpty() {
    return this.stack.empty();
  }

  public void clear() {
    this.maxNodes = 0;
    this.stack.clear();
  }
  public Node pop() {
    return this.stack.pop();
  }

  public int maxNodesStored() {
    return this.maxNodes;
  }
}
