package cs3500.pa03.model;

/**
 * Class to represent a piece that is a battleship
 */
public class Battleship extends AgamePiece {
  /**
   * Constructor to initialize the length of 5
   */
  public Battleship() {
    super(5);
  }

  /**
   * Method to determine if a battleship is water
   *
   * @return returns false
   */
  @Override
  public boolean isWater() {
    return false;
  }

  /**
   * returns the Battleship as a string before it was hit
   *
   * @return return a string of "B"
   */
  @Override
  public String toString() {
    return "B";
  }


}
