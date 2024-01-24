package cs3500.pa03.controller;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Iboard;
import cs3500.pa03.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to control and validate the user input for the manual player
 */
public class ManualPlayerController {
  private final View view;

  /**
   * Constructor to set up the view
   *
   * @param view View that gets user input and outputs statements to the user
   */
  public ManualPlayerController(View view) {
    this.view = view;
  }

  /**
   * Method to get the correct amount of shots from the uesr
   *
   * @param numOfShots the number of shots the user can make
   * @param height     the height of the board
   * @param width      the width of the board
   * @param myBoard    the users board for output reasons
   * @param oppBoard   the opponents board for output reasons
   * @return list of shots the user took
   */
  public List<Coord> getShots(int numOfShots, int height, int width, Iboard myBoard,
                              Iboard oppBoard) {
    ArrayList<Coord> shots = new ArrayList<>();
    view.displayMessage("Opponent's Board");
    view.displayBoard(oppBoard);
    view.displayMessage("Your Board");
    view.displayBoard(myBoard);
    view.displayMessage("Please enter " + numOfShots + " shots.");
    view.putSeparator();
    for (int i = 0; i < numOfShots; i++) {
      int x = view.getInt();
      int y = view.getInt();
      Coord shot = new Coord(x, y);
      shots.add(shot);
    }

    while (!validShots(shots, height, width)) {
      view.displayMessage("One of your shots was invalid please enter " + numOfShots + " shots.");
      view.putSeparator();
      shots.clear();
      for (int i = 0; i < numOfShots; i++) {
        int x = view.getInt();
        int y = view.getInt();
        Coord shot = new Coord(x, y);
        shots.add(shot);
      }
    }

    return shots;
  }

  /**
   * Method to check if the shots are in the board
   *
   * @param shots  list of shots
   * @param height height of the board
   * @param width  width of the board
   * @return boolean of if the shots are valid or not
   */
  private boolean validShots(List<Coord> shots, int height, int width) {
    for (Coord shot : shots) {
      if (shot.x < 0 || shot.x >= width || shot.y < 0 || shot.y >= height) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method to display the game result
   *
   * @param result enum of the game result
   * @param reason string of the reason of the game result
   */
  public void gameResult(GameResult result, String reason) {
    String gameResult = result.toString().toLowerCase();
    view.displayMessage("You " + gameResult);
    view.displayMessage(reason);
  }
}
