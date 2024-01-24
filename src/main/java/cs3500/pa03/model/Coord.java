package cs3500.pa03.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * To represent a coordinate on a BattleSalvo board
 */
public class Coord {

  public int x;

  public int y;

  @JsonCreator
  public Coord(@JsonProperty("x")int x, @JsonProperty("y")int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Equals method to see if a coords is equal to another object
   *
   * @param o1 Other object
   * @return boolean of equality
   */
  @Override
  public boolean equals(Object o1) {
    if (o1 instanceof Coord) {
      Coord c1 = (Coord) o1;
      return c1.x == this.x && c1.y == this.y;
    } else {
      return false;
    }
  }

  /**
   * Overridden ToString method
   *
   * @return returns string representation of a coord
   */
  public String toString() {
    return this.x + " " + this.y;
  }

  /**
   * Overridden hashcode method
   *
   * @return returns an int of the hash code of the object
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    return result;
  }
}
