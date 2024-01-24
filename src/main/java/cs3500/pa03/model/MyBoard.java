package cs3500.pa03.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to represent a players own board
 */
public class MyBoard implements Iboard {
  private final int height;
  private final int width;
  private AgamePiece[][] board;
  private final Random random;

  /**
   * Constructor to set up the board given the dimensions and ships and a random for testing
   *
   * @param height height of the board
   * @param width  width of the board
   * @param ships  list ships on the board
   * @param rand   random for testing
   */
  public MyBoard(int height, int width, ArrayList<AgamePiece> ships, Random rand) {
    this.random = rand;
    this.height = height;
    this.width = width;
    generateBoard(ships);
  }

  /**
   * Constructor to set up the board
   *
   * @param height height of the board
   * @param width  width of the board
   * @param ships  list of ships on the board
   */
  public MyBoard(int height, int width, ArrayList<AgamePiece> ships) {
    this(height, width, ships, new Random());
  }

  /**
   * Method to report what shots hit the ships on the board
   *
   * @param opponetShots list of shots the opponent sent
   * @return returns the successful shots
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponetShots) {
    ArrayList<Coord> successfulHits = new ArrayList<>();
    takeDamage(opponetShots, Status.HIT);
    for (Coord shot : opponetShots) {
      if (!board[shot.y][shot.x].isWater()) {
        successfulHits.add(shot);
      }
    }

    return successfulHits;
  }

  /**
   * method to generate a board given a list of ships
   *
   * @param ships list of ships to place on the board
   */
  @Override
  public void generateBoard(ArrayList<AgamePiece> ships) {
    board = new AgamePiece[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = new Water();
      }
    }

    placeShips(ships);

  }

  /**
   * Method to show the board as a String
   *
   * @return returns a string of the board
   */
  @Override
  public String showBoard() {
    StringBuilder boardString = new StringBuilder();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        boardString.append(board[y][x].symbol(new Coord(x, y)));
      }
      boardString.append("\n");
    }

    return boardString.toString();
  }

  /**
   * Method to take damage to the board given the type of damage
   *
   * @param opponentShots shots the opponent took
   * @param damageType    type of damage as an enum Status
   */
  @Override
  public void takeDamage(List<Coord> opponentShots, Status damageType) {
    for (Coord shot : opponentShots) {
      board[shot.y][shot.x].hit(shot);
    }
  }

  /**
   * Method to see if the coordinates are valid to place one the board
   *
   * @param points array of the coordinates
   * @return returns a boolean of if the placement is valid or not
   */
  private boolean validPlacement(Coord[] points) {
    for (Coord coord : points) {
      if (!onBoard(coord)) {
        return false;
      }
    }

    for (Coord coord : points) {
      if (!board[coord.y][coord.x].isWater()) {
        return false;
      }
    }

    return true;
  }

  /**
   * Places the ships on the board
   *
   * @param ships array list of ships to place
   */
  private void placeShips(ArrayList<AgamePiece> ships) {
    for (AgamePiece ship : ships) {
      int x = random.nextInt(height);
      int y = random.nextInt(width);
      Coord randomCord = new Coord(x, y);
      Coord[] shipCords = ship.placeCoordinates(randomCord, random.nextBoolean());

      while (!validPlacement(shipCords)) {
        x = random.nextInt(height);
        y = random.nextInt(width);
        randomCord = new Coord(x, y);
        shipCords = ship.placeCoordinates(randomCord, random.nextBoolean());
      }

      for (Coord coordinate : shipCords) {
        board[coordinate.y][coordinate.x] = ship;
      }
    }
  }

  /**
   * Determines if a coordinate is on the board
   *
   * @param coord shot as a coordinate
   * @return returns a boolean of if the shot is on board
   */
  public boolean onBoard(Coord coord) {
    return coord.x >= 0 && coord.x < width && coord.y >= 0 && coord.y < height;
  }
}
