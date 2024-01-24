package cs3500.pa03.model;



/**
 * Class to represent water as a piece
 */
public class Water extends AgamePiece {
  private boolean isHit;

  /**
   * Constructor to set the piece length to 1
   */
  public Water() {
    super(1);
    isHit = false;
  }

  /**
   * Method to represent the water as a symbol
   *
   * @param cord Coord the symbol is being asked about
   * @return returns a string as the symbol
   */
  @Override
  public String symbol(Coord cord) {
    if (isHit) {
      return "M";
    } else {
      return toString();
    }
  }

  /**
   * Method to see if water is water
   *
   * @return returns true
   */
  @Override
  public boolean isWater() {
    return true;
  }

  /**
   * Returns the representation of water before it was hit
   *
   * @return returns "~"
   */
  @Override
  public String toString() {
    return "~";
  }

  /**
   * Method to hit water
   *
   * @param coord Coord on the piece getting hit
   */
  @Override
  public void hit(Coord coord) {
    this.isHit = true;
  }


}
