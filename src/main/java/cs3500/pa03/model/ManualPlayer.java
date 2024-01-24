package cs3500.pa03.model;

import cs3500.pa03.controller.ManualPlayerController;
import java.util.List;
import java.util.Random;

/**
 * Class to represent a manual player
 */
public class ManualPlayer extends A2Player {
  private final ManualPlayerController controller;

  /**
   * Constructor to take in a controller and a random for testing
   *
   * @param c      manual player controller
   * @param random random for testing
   */
  public ManualPlayer(ManualPlayerController c, Random random) {
    super(random);
    this.controller = c;
  }

  /**
   * Constructor to take in a controller for the player
   *
   * @param c manual player controller
   */
  public ManualPlayer(ManualPlayerController c) {
    this(c, new Random());
  }

  /**
   * Get the player's name.
   * NOTE: This may not be important to your implementation for PA03, but it will be later
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "Manual Player";
  }


  /**
   * Returns this player's shots on the opponent's ABoard. The number of shots returned should
   * equal the number of ships on this player's ABoard that have not sunk.
   *
   * @return the locations of shots on the opponent's ABoard
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> shots = controller.getShots(numOfShots(), height, width, myBoard, oppBoard);

    super.oppBoard.takeDamage(shots, Status.MISS);


    return shots;
  }


  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    controller.gameResult(result, reason);
  }

}
