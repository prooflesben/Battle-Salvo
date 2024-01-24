package cs3500.pa03.model;

/**
 * Class to represent a carrier piece
 */
public class Carrier extends AgamePiece {
  /**
   * Constructor to initialize the length of 6
   */
  public Carrier() {
    super(6);
  }


  /**
   * Method to determine if a carrier is water
   *
   * @return returns false
   */
  @Override
  public boolean isWater() {
    return false;
  }

  /**
   * returns a string of what the carrier is before it is hit
   *
   * @return returns a string of "C"
   */
  @Override
  public String toString() {
    return "C";
  }


}
