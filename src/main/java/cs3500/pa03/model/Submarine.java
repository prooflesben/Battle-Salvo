package cs3500.pa03.model;

/**
 * Class to represent a sub as a piece
 */
public class Submarine extends AgamePiece {
  /**
   * Constructor to set the length of the piece ot 3
   */
  public Submarine() {
    super(3);
  }

  /**
   * Method to determine if the sub is water
   *
   * @return returns false
   */
  @Override
  public boolean isWater() {
    return false;
  }

  /**
   * Method to return the sub as a string before it was hit
   *
   * @return returns "S"
   */
  @Override
  public String toString() {
    return "S";
  }


}
