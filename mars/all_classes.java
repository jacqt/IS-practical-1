/*
Practical 3 Report:

For this practical I used the A* search algorithm.
My heuristic was 20 - # of unique locations visited

Here are the results:

============================
A* Graph search

Number of nodes generated: 1162
Max number of nodes stored: 599
Number of visited locations: 18

============================
A* Tree search

Number of nodes generated: 1404
Max number of nodes stored: 796
Number of visited locations: 18
*/

// MarsGoalTest.java
package mars;

import search.GoalTest;
import search.State;

public class MarsGoalTest implements GoalTest {
	public boolean isGoal(State state) {
		MarsState marsState = (MarsState)state;
    return marsState.batteryLife == 0;
	}
}



// MarsHeuristic.java
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

// MarsLocation.java
package mars;

public class MarsLocation {
  public final int x;
  public final int y;

  public MarsLocation(int x, int y){
   this.x = x; this.y = y;
  }

  public int hashCode() {
    return this.x * 10000 + this.y;
  }

  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }
    if (this.getClass() != otherObject.getClass()) {
      return false;
    }

    MarsLocation otherLoc = (MarsLocation) otherObject;
    return (this.x == otherLoc.x && this.y == otherLoc.y);
  }
}

// MarsState.java
package mars;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import search.Action;
import search.State;

public class MarsState implements State {
  private Planet planet;
  public final Set<MarsLocation> visitedLocations;
  public final MarsLocation currentLocation;
  public final int batteryLife;

	public MarsState(Planet planet, int x, int y, int batteryLife ) {
    this.planet = planet;
    this.currentLocation = new MarsLocation(x, y);
    this.batteryLife = batteryLife;
    this.visitedLocations = Collections.emptySet();
	}
	public MarsState(Planet planet, Set<MarsLocation> visitedLocations, int x, int y, int batteryLife ) {
    this.planet = planet;
    this.currentLocation = new MarsLocation(x, y);
    this.batteryLife = batteryLife;
    this.visitedLocations = visitedLocations;
	}

	public Set<Action> getApplicableActions() {
		Set<Action> actions = new LinkedHashSet<Action>();
    if (this.batteryLife <= 0) {
      return actions;
    }
		for (Movement movement : Movement.values()) {
			int newX = this.currentLocation.x + movement.deltaRow;
			int newY = this.currentLocation.y + movement.deltaColumn;
      if (this.planet.isAccessible(newX, newY)) {
				actions.add(movement);
      }
		}
		return actions;
	}
	public State getActionResult(Action action) {
		Movement movement=(Movement)action;
    int newX = this.currentLocation.x + movement.deltaRow;
    int newY = this.currentLocation.y + movement.deltaColumn;
		Set<MarsLocation> newVisitedLocations = new LinkedHashSet<MarsLocation>(this.visitedLocations);
		newVisitedLocations.add(this.currentLocation);

		return new MarsState(this.planet, newVisitedLocations, newX, newY, this.batteryLife - 1);
	}

  public int hashCode() {
    int hash = 0;
    for (MarsLocation m: this.visitedLocations) {
      hash += m.hashCode();
    }
    return hash + this.currentLocation.hashCode() + this.batteryLife;
  }

  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }
    if (this.getClass() != otherObject.getClass()) {
      return false;
    }

    MarsState otherState = (MarsState) otherObject;
    for (MarsLocation m: this.visitedLocations) {
      if (!otherState.visitedLocations.contains(m)) {
        return false;
      }
    }
    return this.currentLocation.equals(otherState.currentLocation) &&
      this.batteryLife == otherState.batteryLife;
  }
}
  
// Movement.java
package mars;

import search.Action;

public enum Movement implements Action {
	UP(-1, 0), LEFT(0, -1), DOWN(1, 0), RIGHT(0, 1);

	public final int deltaRow;
	public final int deltaColumn;
	private Movement(int deltaRow, int deltaColumn) {
		this.deltaRow = deltaRow;
		this.deltaColumn = deltaColumn;
	}

  public int cost(){
      return 1;
  }
}


// Solution.java
package mars;

import search.*;

public class Solution {
  public static void main(String[] args) {
    MarsState initialConfiguration = new MarsState(new Planet(), 4, 4, 20);

    GoalTest goalTest = new MarsGoalTest();
    NodeFunction h = new MarsHeuristic();
    NodeFunction a_star = new AStarFunction(h);

    tryAlgo("A* Graph search", new GraphSearch(new BestFirstFrontier(a_star)), initialConfiguration, goalTest);
    tryAlgo("A* Tree search", new TreeSearch(new BestFirstFrontier(a_star)), initialConfiguration, goalTest);
  }

  public static void tryAlgo(String name, Search algo, State initialConfiguration, GoalTest goalTest) {
    Node solution = algo.findSolution(initialConfiguration, goalTest);
    MarsState finalState = (MarsState) solution.state;
    System.out.printf(
        "============================\n%s\n\nNumber of nodes generated: %d\nMax number of nodes stored: %d\nNumber of visited locations: %d\n\n",
        name,
        algo.numberOfNodesGenerated(),
        algo.maxNodesStored(),
        finalState.visitedLocations.size());
  }

