package cs3500.pa04.controller;

import cs3500.pa03.model.AgamePiece;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.ShipType;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * To represent a controller of an AI player in a game of BattleSalvo
 */
public class AiController {

  private final Player aiPlayer;

  /**
   * Convenience constructor which randomly initializes the AI player
   *
   * @param rand the rand
   */
  public AiController(Random rand) {
    this.aiPlayer = new AiPlayer(rand);
  }

  /**
   * Convenience constructor which initializes the AI player
   */
  public AiController() {
    this.aiPlayer = new AiPlayer();
  }


  /**
   * Gets shots.
   *
   * @return the shots
   */
  public List<Coord> getShots() {
    List<Coord> shots = aiPlayer.takeShots();

    return shots;
  }

  /**
   * Set up this players board and returns a list of ships on the baord
   *
   * @param height the height of the board
   * @param width  the width of the board
   * @param specs  the specs of all positions on the board
   * @return the list of ships on the board
   */
  public List<AgamePiece> setup(int height, int width, Map<ShipType, Integer> specs) {
    List<AgamePiece> ships = aiPlayer.setup(height, width, specs);


    return ships;
  }


  /**
   * Report damage by returning the list of shots that caused damage
   *
   * @param shots the shots
   * @return the list of shots that caused damage
   */
  public List<Coord> reportDamage(List<Coord> shots) {
    return aiPlayer.reportDamage(shots);
  }

  /**
   * Returns the list of successful hits.
   *
   * @param shots the shots that were successful hits
   */
  public void successfulHits(List<Coord> shots) {
    aiPlayer.successfulHits(shots);

  }

  /**
   * End game.
   */
  public void endGame() {
    System.out.println("Game is done");
  }


}
