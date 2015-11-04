package search;

public class AStarFunction implements NodeFunction {
    private NodeFunction scorer;

    public AStarFunction (NodeFunction scorer) {
        this.scorer = scorer;
    }

    public int scoreNode(Node node) {
        return this.scorer.scoreNode(node) + node.depth;
    }
}
