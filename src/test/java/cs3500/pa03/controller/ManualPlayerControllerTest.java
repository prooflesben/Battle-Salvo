package cs3500.pa03.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.InputInteraction;
import cs3500.pa03.Interaction;
import cs3500.pa03.PrintInteraction;
import cs3500.pa03.TestAuto;
import cs3500.pa03.model.AgamePiece;
import cs3500.pa03.model.Battleship;
import cs3500.pa03.model.Carrier;
import cs3500.pa03.model.Destroyer;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Iboard;
import cs3500.pa03.model.MyBoard;
import cs3500.pa03.model.OpponentBoard;
import cs3500.pa03.model.Submarine;
import cs3500.pa03.view.View;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManualPlayerControllerTest {
  public final String seperator = "------------------------------------------------------";
  Iboard userBoard;
  Iboard oppBoard;
  Random random = new Random(4);
  ArrayList<AgamePiece> ships;
  String board = """
      ~~~BBBBB
      ~~SSS~~~
      ~~~~~C~~
      ~~D~~C~~
      ~~D~~C~~
      ~~D~~C~~
      ~~D~~C~~
      ~~~~~C~~
          """;
  int height = 8;
  int width = 8;

  @BeforeEach
  void init() {
    ships = new ArrayList<>(
        Arrays.asList(new Carrier(), new Battleship(), new Submarine(), new Destroyer()));
    userBoard = new MyBoard(8, 8, ships, random);
    oppBoard = new OpponentBoard(8, 8);
  }

  @Test
  void getShots() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();

    Interaction[] interactions = {
        new InputInteraction("-1 5 2 10 10 2 5 -1 4 0 0 0 3 4 2 4"),
        new PrintInteraction("Opponent's Board"),
        new PrintInteraction(oppBoard.showBoard()),
        new PrintInteraction("Your Board"),
        new PrintInteraction(userBoard.showBoard()),
        new PrintInteraction("Please enter 4 shots."),
        new PrintInteraction(seperator),
        new PrintInteraction("One of your shots was invalid please enter 4 shots."),
        new PrintInteraction(seperator)
    };
    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    ManualPlayerController controller = new ManualPlayerController(new View(realOutput, input));
    controller.getShots(4, 8, 8, userBoard, oppBoard);

    assertEquals(output.toString(), realOutput.toString());
  }

  @Test
  void gameResultWin() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();
    String reason = "You sunk all the opponents ships";

    Interaction[] interactions = {
        new PrintInteraction("You win"),
        new PrintInteraction(reason)

    };
    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    ManualPlayerController controller = new ManualPlayerController(new View(realOutput, input));
    controller.gameResult(GameResult.WIN, reason);

    assertEquals(output.toString(), realOutput.toString());

  }

  @Test
  void gameResultLoss() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();
    String reason = "You suck";

    Interaction[] interactions = {
        new PrintInteraction("You lose"),
        new PrintInteraction(reason)
    };
    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    ManualPlayerController controller = new ManualPlayerController(new View(realOutput, input));
    controller.gameResult(GameResult.LOSE, reason);

    assertEquals(output.toString(), realOutput.toString());

  }

  @Test
  void gameResultDraw() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();
    String reason = "You suck";

    Interaction[] interactions = {
        new PrintInteraction("You draw"),
        new PrintInteraction(reason)
    };
    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    ManualPlayerController controller = new ManualPlayerController(new View(realOutput, input));
    controller.gameResult(GameResult.DRAW, reason);

    assertEquals(output.toString(), realOutput.toString());

  }
}