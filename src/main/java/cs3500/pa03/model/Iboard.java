package cs3500.pa03.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Interface to represent a board
 */
public interface Iboard {

  /**
   * Method to report what shots hit the ships on the board
   *
   * @param opponetShots list of shots the opponent sent
   * @return returns the successful shots
   */
  List<Coord> reportDamage(List<Coord> opponetShots);

  /**
   * method to generate a board given a list of ships
   *
   * @param ships list of ships to place on the board
   */
  void generateBoard(ArrayList<AgamePiece> ships);

  /**
   * Method to show the board as a String
   *
   * @return returns a string of the board
   */
  String showBoard();

  /**
   * Method to take damage to the board given the type of damage
   *
   * @param opponentShots shots the opponent took
   * @param damageType    type of damage as an enum Status
   */
  void takeDamage(List<Coord> opponentShots, Status damageType);

  /**
   * Determines if the shot is on the board
   *
   * @param shot shot as a coordinate
   * @return returns if the shot is on the board
   */
  boolean onBoard(Coord shot);


}
