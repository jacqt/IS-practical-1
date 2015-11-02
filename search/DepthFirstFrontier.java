package search;

import java.util.Stack;

public class DepthFirstFrontier implements Frontier {
  private Stack<Node> stack;

  public DepthFirstFrontier() {
    this.stack = new Stack<Node>();
  }

  public void add(Node newNode) {
    this.stack.push(newNode);
  }

  public boolean isEmpty() {
    return this.stack.empty();
  }

  public void clear() {
    this.stack.clear();
  }
  public Node pop() {
    return this.stack.pop();
  }
}
