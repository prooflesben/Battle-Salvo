package cs3500.pa03.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.InputInteraction;
import cs3500.pa03.Interaction;
import cs3500.pa03.PrintInteraction;
import cs3500.pa03.TestAuto;
import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Class to test the controller
 */
class ControllerTest {
  String gameLoseResult = """
      Welcome to Battleship Salvo
      Please enter board dimensions from 6 to 15
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      Opponent's Board
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SSS~~
      CCCCCC
      DSSS~~
      DBBBBB
      D~~SSS
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      M~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SSS~~
      CCCXCC
      XSSS~~
      XBBXBX
      D~~SSX
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      M~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SSX~~
      XCCXXC
      XXSS~~
      XXBXBX
      X~~SSX
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      M~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SXXM~
      XXCXXX
      XXXSM~
      XXBXBX
      X~~SSX
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      M~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~XXXMM
      XXXXXX
      XXXXMM
      XXXXBX
      X~~SSX
      D~~~~~
            
      Please enter 3 shots.
      ------------------------------------------------------
      Opponent's Board
      M~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      MXXXMM
      XXXXXX
      XXXXMM
      XXXXXX
      XMMSSX
      X~~~~M
            
      Please enter 1 shots.
      ------------------------------------------------------
      You lose
      The other player destroyed all of your ships""";
  String gameWinResult = """
      Welcome to Battleship Salvo
      Please enter board dimensions from 6 to 15
      You entered invalid inputs, please enter board dimensions from 6 to 15
      You entered invalid inputs, please enter board dimensions from 6 to 15
      You entered invalid inputs, please enter board dimensions from 6 to 15
      You entered invalid inputs, please enter board dimensions from 6 to 15
      You entered invalid inputs, please enter board dimensions from 6 to 15
      You entered invalid inputs, please enter board dimensions from 6 to 15
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      You entered invalid an invalid fleet
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      You entered invalid an invalid fleet
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      You entered invalid an invalid fleet
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      You entered invalid an invalid fleet
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      You entered invalid an invalid fleet
      Please enter your fleet size in order of [Carrier, Battleship, Destroyer, Submarine]
      Your max fleet size is less than 6
      Opponent's Board
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SSS~~
      CCCCCC
      DSSS~~
      DBBBBB
      D~~SSS
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      ~~XXX~
      ~XXX~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SSS~~
      CCCXCC
      XSSS~~
      XBBXBX
      D~~SSX
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      ~~XXX~
      ~XXX~~
      XXXXXX
      ~~~~~~
      ~~~~~~
      ~~~~~~
            
      Your Board
      ~SSS~~
      XCCXCC
      XXSS~~
      XXBXBX
      X~~SSX
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      Opponent's Board
      ~~XXX~
      ~XXX~~
      XXXXXX
      ~~~XXX
      ~~~~~~
      ~XXX~~
            
      Your Board
      MSSS~~
      XXCXCC
      XXXS~~
      XXBXBX
      X~~SSX
      D~~~~~
            
      Please enter 6 shots.
      ------------------------------------------------------
      You win
      You destroyed all of the other players ships""";

  /**
   * Method to test the play game method with a win
   */
  @Test
  void playGameWin() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();

    Interaction[] interactions = {
        new InputInteraction(
            "0 6\n 7 0\n 20 8\n 9 20\n 3 20\n 20 3\n 6 6 \n 0 1 1 1\n 3 0 1 1\n 2 2 0 2\n 1 1 1 0"
                + "\n 6 8 4 2\n  1 1 1 3\n"
                + "2 0 3 0 4 0 1 1 2 1 3 1 \n0 2 1 2 2 2 3 2 4 2 5 2 \n1 5 2 5 3 5 3 3 4 3 5 3 \n"
                + "0 4 1 4 2 4 3 4 4 4 4 5 \n"),
        new PrintInteraction(gameWinResult)

    };
    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    Controller controller = new Controller(input, realOutput, new Random(4));
    controller.playGame();

    assertEquals(output.toString(), realOutput.toString());
  }

  /**
   * Method to test the play game method with a loss
   */
  @Test
  void playGameLost() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();

    Interaction[] interactions = {
        new InputInteraction("6 6\n 1 1 1 3\n" + zeroMove(6) + "\n"
            + zeroMove(6) + "\n" + zeroMove(6) + "\n"
            + zeroMove(6) + "\n" + zeroMove(4) + "\n"
            + zeroMove(4) + "\n"
            + zeroMove(2) + "\n" + zeroMove(2) + "\n"),
        new PrintInteraction(gameLoseResult)

    };
    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    Controller controller = new Controller(input, realOutput, new Random(4));
    controller.playGame();

    assertEquals(output.toString(), realOutput.toString());
  }

  String zeroMove(int amountOfMoves) {
    int[] moves = new int[amountOfMoves * 2];
    StringBuilder output = new StringBuilder();

    for (int move : moves) {
      output.append(move + " ");
    }

    return output.toString();
  }
}