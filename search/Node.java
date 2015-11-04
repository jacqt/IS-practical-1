package search;

public class Node implements Comparable<Node> {
    public final Node parent;
    public final Action action;
    public final State state;
    public final int depth;
    public int score;

    public Node(Node parent, Action action, State state) {
        this.parent = parent;
        this.action = action;
        this.state = state;
        this.depth = -1;
        this.score = -1;
    }

    public Node(Node parent, Action action, State state, int depth) {
        this.parent = parent;
        this.action = action;
        this.state = state;
        this.depth = depth;
        this.score = -1;
    }

    public int compareTo(Node other) {
        return this.score - other.score;
    }
}
