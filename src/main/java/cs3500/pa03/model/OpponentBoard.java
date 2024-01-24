package cs3500.pa03.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the opponents board
 */
public class OpponentBoard implements Iboard {
  private final int height;
  private final int width;
  private Status[][] board;

  /**
   * Constructor that sets up the board
   *
   * @param height height of the board
   * @param width  width of the board
   */
  public OpponentBoard(int height, int width) {
    this.height = height;
    this.width = width;
    generateBoard(new ArrayList<>());
  }

  /**
   * Method to report what shots hit the ships on the board
   *
   * @param opponetShots list of shots the opponent sent
   * @return returns the successful shots
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponetShots) {
    ArrayList<Coord> damage = new ArrayList<>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Coord curPoint = new Coord(x, y);
        if (board[y][x].equals(Status.HIT) && opponetShots.contains(curPoint)) {
          damage.add(curPoint);
        }
      }
    }
    return damage;
  }

  /**
   * method to generate a board given a list of ships
   *
   * @param ships list of ships to place on the board
   */
  @Override
  public void generateBoard(ArrayList<AgamePiece> ships) {
    this.board = new Status[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = Status.WATER;
      }
    }
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
        String curSymbol;
        if (board[y][x].equals(Status.HIT)) {
          curSymbol = "X";
        } else if (board[y][x].equals(Status.MISS)) {
          curSymbol = "M";
        } else {
          curSymbol = "~";
        }
        boardString.append(curSymbol);
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
      board[shot.y][shot.x] = damageType;
    }
  }

  /**
   * Determines if the shot is on the board
   *
   * @param coord shot as a coordinate
   * @return returns if the shot is on the board
   */
  @Override
  public boolean onBoard(Coord coord) {
    return coord.x >= 0 && coord.x < width && coord.y >= 0 && coord.y < height;
  }
}
