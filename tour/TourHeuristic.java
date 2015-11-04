package tour;
import search.*;

public class TourHeuristic implements NodeFunction {
    private final City startCity;
    private final Cities allCities;
    public TourHeuristic(City startCity, Cities allCities) {
        this.startCity = startCity;
        this.allCities = allCities;
    }

    public int scoreNode(Node node) {
        TourState state = (TourState) node.state;

        int maxDist = 0;
        City farthestCity = null;
        for (City city : allCities.getAllCities()) {
            if (state.visitedCities.contains(city)) {
                continue;
            }
            int s = state.currentCity.getShortestDistanceTo(city);
            if (s > maxDist) {
                farthestCity = city;
                maxDist = s;
            }
        }
        if (maxDist == 0) {
            return 0;
        }
        return maxDist + startCity.getShortestDistanceTo(farthestCity);
    }
}
