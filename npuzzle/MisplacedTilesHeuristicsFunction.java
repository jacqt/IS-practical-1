package npuzzle;
import search.*;

public class MisplacedTilesHeuristicsFunction implements NodeFunction {
    public MisplacedTilesHeuristicsFunction() {
    }

    public int scoreNode(Node node) {
        int error = 0;
        Tiles tiles = (Tiles) node.state;
        int lastTileIndex = tiles.width * tiles.width - 1;
        for (int index = lastTileIndex - 1; index >=0; --index) {
            if (tiles.tiles[index] != index + 1) {
                error += 1;
            }
        }
        return error;
    }
}
