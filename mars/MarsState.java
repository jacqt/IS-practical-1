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
