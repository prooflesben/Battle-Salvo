package cs3500.pa03.model;


import cs3500.pa04.json.ShipJson;
import java.util.HashMap;

/**
 * Abstract class to represent a game piece
 */
public abstract class AgamePiece implements Jsonable {
  private final HashMap<Coord, Status> shipStatus = new HashMap<>();
  private final int length;
  private Coord[] coordinates;
  private Direction direction;

  /**
   * Constructor to set up each piece for how long it is
   *
   * @param length length of the piece
   */
  AgamePiece(int length) {
    this.length = length;
  }

  /**
   * Method to give the coordinates of where the ship would be placed
   *
   * @param cord            starting coordinate
   * @param placeHorizontal boolean if the ship is placed horizontal
   * @return returns an array of the placed coordinates
   */
  public Coord[] placeCoordinates(Coord cord, boolean placeHorizontal) {
    int x = cord.x;
    int y = cord.y;
    Coord[] points = new Coord[length];
    for (int i = 0; i < length; i++) {
      if (placeHorizontal) {
        points[i] = new Coord(x + i, y);
        direction = Direction.HORIZONTAL;
      } else {
        points[i] = new Coord(x, y + i);
        direction = Direction.VERTICAL;

      }
    }

    updateCordStatus(points);
    return points;
  }

  /**
   * Method to update the current points of the piece and its status of if it is hit or not
   */
  private void updateCordStatus(Coord[] points) {
    coordinates = points;

    for (Coord coord : points) {
      shipStatus.put(coord, Status.NOHIT);
    }
  }



  /**
   * Method to give back the ships string symbol at the given Coord
   *
   * @param cord Coord the symbol is being asked about
   * @return returns a string of what the piece looks like at that Coord
   */
  public String symbol(Coord cord) {
    if (!shipStatus.containsKey(cord)) {
      throw new IllegalArgumentException("This coordinate doesn't contain this piece");
    }
    if (shipStatus.get(cord).equals(Status.HIT)) {
      return "X";
    } else {
      return toString();
    }
  }

  /**
   * Method to determine if the piece is water
   *
   * @return returns a boolean if the piece is water
   */
  public abstract boolean isWater();

  /**
   * To string method to get what the piece would look like before a hit
   *
   * @return returns a string
   */
  public abstract String toString();

  /**
   * Hits the given Coord on the piece
   *
   * @param coord Coord on the piece getting hit
   */
  public void hit(Coord coord) {
    if (shipStatus.containsKey(coord)) {
      shipStatus.put(coord, Status.HIT);
    }
  }

  /**
   * Determines if the piece is destroyed or not
   *
   * @return boolean of if the piece is destroyed or not
   */
  public boolean isDestroyed() {
    for (Coord cord : coordinates) {
      if (shipStatus.get(cord).equals(Status.NOHIT)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Converts a ship to a ShipJson
   *
   * @return a ShipJson that represents this ship
   */
  public ShipJson toJson() {
    Coord coord = coordinates[0];
    return new ShipJson(coord, length, direction);
  }

}
