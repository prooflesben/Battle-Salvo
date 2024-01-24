package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Abstract class to represent a player in a two player game with common methods ManPlayer
 * and AIPlayer have
 */
public abstract class A2Player implements Player {
  protected final Random random;
  protected Iboard myBoard;
  protected Iboard oppBoard;
  protected ArrayList<AgamePiece> ships;
  protected int width;
  protected int height;
  protected int shotLeft;

  /**
   * Constructor with a given random for testing
   *
   * @param random testing random
   */
  public A2Player(Random random) {
    this.random = random;
  }

  /**
   * Constructor that creates random for random needs
   */
  public A2Player() {
    this(new Random());
  }

  /**
   * Get the player's name.
   * NOTE: This may not be important to your implementation for PA03, but it will be later
   *
   * @return the player's name
   */
  @Override
  public abstract String name();

  /**
   * Given the specifications for a BattleSalvo ABoard, return a list of ships with their locations
   * on the ABoard.
   *
   * @param height         the height of the ABoard, range: [6, 15] inclusive
   * @param width          the width of the ABoard, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the ABoard
   * @return the placements of each ship on the ABoard
   */
  @Override
  public List<AgamePiece> setup(int height, int width, Map<ShipType, Integer> specifications) {
    ArrayList<AgamePiece> ships = generateShips(specifications);
    this.myBoard = new MyBoard(height, width, ships, random);
    this.oppBoard = new OpponentBoard(height, width);
    this.height = height;
    this.width = width;
    this.ships = ships;
    this.shotLeft = width * height;
    return ships;
  }

  /**
   * Returns this player's shots on the opponent's ABoard. The number of shots returned should
   * equal the number of ships on this player's ABoard that have not sunk.
   *
   * @return the locations of shots on the opponent's ABoard
   */
  @Override
  public abstract List<Coord> takeShots();

  /**
   * Given the list of shots the opponent has fired on this player's ABoard, report which
   * shots hit a ship on this player's ABoard.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's ABoard
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *         ship on this ABoard
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return myBoard.reportDamage(opponentShotsOnBoard);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    oppBoard.takeDamage(shotsThatHitOpponentShips, Status.HIT);
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public abstract void endGame(GameResult result, String reason);

  /**
   * Method to generate the ships given the specifications
   *
   * @param specifications map of ship type to how many ships
   * @return returns array list of all the ships
   */
  private ArrayList<AgamePiece> generateShips(Map<ShipType, Integer> specifications) {
    ArrayList<ShipType> types = new ArrayList<>();
    types.add(ShipType.CARRIER);
    types.add(ShipType.BATTLESHIP);
    types.add(ShipType.SUBMARINE);
    types.add(ShipType.DESTROYER);

    ArrayList<AgamePiece> ships = new ArrayList<>();


    for (ShipType type : types) {
      for (int i = 0; i < specifications.get(type); i++) {
        if (type.equals(ShipType.CARRIER)) {
          ships.add(new Carrier());
        }

        if (type.equals(ShipType.BATTLESHIP)) {
          ships.add(new Battleship());
        }

        if (type.equals(ShipType.DESTROYER)) {
          ships.add(new Destroyer());
        }

        if (type.equals(ShipType.SUBMARINE)) {
          ships.add(new Submarine());
        }
      }
    }
    return ships;
  }

  /**
   * Method to get the number of shots the user has
   *
   * @return returns the number of shots as an int
   */
  protected int numOfShots() {
    int count = 0;
    for (AgamePiece ship : ships) {
      if (!ship.isDestroyed()) {
        count += 1;
      }
    }
    return Math.min(shotLeft, count);
  }


}
