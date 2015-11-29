package mars;
import search.*;

public class MarsHeuristic implements NodeFunction {
    public MarsHeuristic() {
    }

    public int scoreNode(Node node) {
        int error = 0;
        MarsState state = (MarsState) node.state;
        return 20 - state.visitedLocations.size();
    }
}
