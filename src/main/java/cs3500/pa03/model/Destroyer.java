package cs3500.pa03.model;

/**
 * Class to represent Destroyer as a piece
 */
public class Destroyer extends AgamePiece {
  /**
   * Constructor to initialize the length of 4
   */
  public Destroyer() {
    super(4);
  }

  /**
   * Method to determine if a destroyer is water
   *
   * @return returns false
   */
  @Override
  public boolean isWater() {
    return false;
  }

  /**
   * Method to return a destroyer before it is hit
   *
   * @return returns "D" as a string
   */
  @Override
  public String toString() {
    return "D";
  }


}
