package cs3500.pa03.controller;


import cs3500.pa03.model.AgamePiece;
import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class to act as the controller of the game
 */
public class Controller {
  private final View view;
  private final Player manualPlayer;
  private final Player computerPlayer;
  private List<AgamePiece> manualShips;
  private List<AgamePiece> computerShips;

  /**
   * Constructor to set up the Controller with a random for testing
   *
   * @param readable   readable to read input from
   * @param appendable place to output info
   * @param random     random used for testing
   */
  public Controller(Readable readable, Appendable appendable, Random random) {
    this.view = new View(appendable, readable);
    manualPlayer = new ManualPlayer(new ManualPlayerController(this.view), random);
    computerPlayer = new AiPlayer(random);
  }

  /**
   * Constructor to initialize the players and set up the view
   *
   * @param readable   readable used for getting input
   * @param appendable appendable to output info
   */
  public Controller(Readable readable, Appendable appendable) {
    this.view = new View(appendable, readable);
    manualPlayer = new ManualPlayer(new ManualPlayerController(this.view));
    computerPlayer = new AiPlayer();
  }

  /**
   * Method to get the board dimensions between 6 and 15 from the user
   *
   * @return returns array of int size 2 as x and y
   */
  private int[] getBoardDimensionsFromUser() {
    view.displayMessage("Please enter board dimensions from 6 to 15");
    int x = view.getInt();
    int y = view.getInt();

    int[] dimensions = {x, y};

    while (!validSize(dimensions)) {
      view.displayMessage("You entered invalid inputs, please enter board dimensions "
          + "from 6 to 15");
      x = view.getInt();
      y = view.getInt();
      dimensions = new int[] {x, y};
    }
    return dimensions;

  }

  /**
   * Insures the input is in the range of 6 and 15 for both the x and y
   *
   * @param size inputted dimensions
   * @return returns a boolean that says if the dimensions are valid
   */
  private boolean validSize(int[] size) {
    return size[0] <= 15 && size[0] >= 6 && size[1] <= 15 && size[1] >= 6;
  }

  /**
   * Gets the fleet from the user
   *
   * @param height the height of the board
   * @param width  the width of the board
   * @return returns the fleet as an array of ints with the order being
   *         [Carrier, Battleship, Destroyer, Submarine]
   */
  private int[] getShipsFromUser(int height, int width) {
    int limit = Math.min(height, width);
    view.displayMessage("Please enter your fleet size in order of "
        + "[Carrier, Battleship, Destroyer, Submarine]");
    view.displayMessage("Your max fleet size is less than " + limit);
    int carrier = view.getInt();
    int battle = view.getInt();
    int destroy = view.getInt();
    int sub = view.getInt();

    int[] ships = {carrier, battle, destroy, sub};

    while (!validShips(ships, limit)) {
      view.displayMessage("You entered invalid an invalid fleet");
      view.displayMessage("Please enter your fleet size in order of "
          + "[Carrier, Battleship, Destroyer, Submarine]");
      view.displayMessage("Your max fleet size is less than " + limit);

      carrier = view.getInt();
      battle = view.getInt();
      destroy = view.getInt();
      sub = view.getInt();

      ships = new int[] {carrier, battle, destroy, sub};

    }

    return ships;
  }

  /**
   * Checks if the fleet is valid
   *
   * @param ships the fleet as an array of ints in [Carrier, Battleship, Destroyer, Submarine]
   *              order
   * @param limit the max amount of ships allowed
   * @return returns boolean saying if the ships are valid or not
   */
  private boolean validShips(int[] ships, int limit) {
    int sum = 0;
    for (int shipAmount : ships) {
      sum += shipAmount;

      if (shipAmount == 0) {
        return false;
      }
    }

    return sum <= limit;
  }

  /**
   * Creates the specifications for the player's to make the ships
   *
   * @param fleet fleet as an array of ints in [Carrier, Battleship, Destroyer, Submarine]
   *              order
   * @return hashmap of how many ships for each type
   */
  private HashMap<ShipType, Integer> specifactions(int[] fleet) {
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    for (int i = 0; i < 4; i++) {
      if (i == 0) {
        specifications.put(ShipType.CARRIER, fleet[i]);
      } else if (i == 1) {
        specifications.put(ShipType.BATTLESHIP, fleet[i]);
      } else if (i == 2) {
        specifications.put(ShipType.DESTROYER, fleet[i]);
      } else {
        specifications.put(ShipType.SUBMARINE, fleet[i]);
      }
    }

    return specifications;
  }

  /**
   * Method to play the game
   */
  public void playGame() {
    view.displayMessage("Welcome to Battleship Salvo");

    int[] dimesions = getBoardDimensionsFromUser();
    int[] fleet = getShipsFromUser(dimesions[0], dimesions[1]);
    Map<ShipType, Integer> specs = specifactions(fleet);

    manualShips = manualPlayer.setup(dimesions[0], dimesions[1], specs);
    computerShips = computerPlayer.setup(dimesions[0], dimesions[1], specs);

    List<Coord> aiShots;
    List<Coord> userShots;

    List<Coord> aiSuccesfulShots;
    List<Coord> userSuccessfulShots;


    while (!gameEnd()) {
      userShots = manualPlayer.takeShots();
      aiShots = computerPlayer.takeShots();

      aiSuccesfulShots = manualPlayer.reportDamage(aiShots);
      userSuccessfulShots = computerPlayer.reportDamage(userShots);

      manualPlayer.successfulHits(userSuccessfulShots);
      computerPlayer.successfulHits(aiSuccesfulShots);
    }
    endGame();
  }

  /**
   * Method to tell if all the ships are destroyed in the given list of ships
   *
   * @param ships list of ships
   * @return returns boolean of if all the ships are destroyed
   */
  private boolean allDestroyed(List<AgamePiece> ships) {
    for (AgamePiece ship : ships) {
      if (!ship.isDestroyed()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method to determine if the game is done
   *
   * @return returns boolean of if the game is done
   */
  private boolean gameEnd() {
    return allDestroyed(computerShips) || allDestroyed(manualShips);
  }

  /**
   * Method to output statements of the end of the game and the result
   */
  private void endGame() {
    if (allDestroyed(manualShips) && allDestroyed(computerShips)) {
      manualPlayer.endGame(GameResult.DRAW, "You and the other player destroyed "
          + "all of you ships at the same time");
      computerPlayer.endGame(GameResult.DRAW, "You and the other player destroyed "
          + "all of you ships at the same time");
    } else if (allDestroyed(manualShips) && !allDestroyed(computerShips)) {
      computerPlayer.endGame(GameResult.WIN, "You destroyed all of the other players ships");
      manualPlayer.endGame(GameResult.LOSE, "The other player destroyed all of your ships");
    } else if (!allDestroyed(manualShips) && allDestroyed(computerShips)) {
      manualPlayer.endGame(GameResult.WIN, "You destroyed all of the other players ships");
      computerPlayer.endGame(GameResult.LOSE, "The other player destroyed all of your ships");
    }
  }


}
