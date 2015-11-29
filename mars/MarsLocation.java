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
