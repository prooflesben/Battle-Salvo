package cs3500.pa03.view;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.InputInteraction;
import cs3500.pa03.Interaction;
import cs3500.pa03.PrintInteraction;
import cs3500.pa03.TestAuto;
import cs3500.pa03.model.AgamePiece;
import cs3500.pa03.model.Battleship;
import cs3500.pa03.model.Carrier;
import cs3500.pa03.model.Destroyer;
import cs3500.pa03.model.Iboard;
import cs3500.pa03.model.MyBoard;
import cs3500.pa03.model.OpponentBoard;
import cs3500.pa03.model.Submarine;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Method to test View
 */
class ViewTest {
  public final String seperator = "------------------------------------------------------";

  Iboard boardOneOfEach;
  Iboard oppBoard;
  Random random = new Random(4);
  ArrayList<AgamePiece> ships;
  String boardShips = """
      ~~~BBBBB
      ~~SSS~~~
      ~~~~~C~~
      ~~D~~C~~
      ~~D~~C~~
      ~~D~~C~~
      ~~D~~C~~
      ~~~~~C~~
          """;
  String boardOpp =
      """
          ~~~~~~~~
          ~~~~~~~~
          ~~~~~~~~
          ~~~~~~~~
          ~~~~~~~~
          ~~~~~~~~
          ~~~~~~~~
          ~~~~~~~~
          """;
  MockAppendable mockAppendable = new MockAppendable();


  /**
   * Method to set up data
   */
  @BeforeEach
  void init() {
    ships = new ArrayList<>(
        Arrays.asList(new Carrier(), new Battleship(), new Submarine(), new Destroyer()));
    boardOneOfEach = new MyBoard(8, 8, ships, random);
    oppBoard = new OpponentBoard(8, 8);
  }

  /**
   * Method to test displayBoard with ships
   */
  @Test
  void displayBoardShips() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();

    Interaction[] interactions = {
        new PrintInteraction(boardShips)
    };

    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    View view = new View(realOutput, input);
    view.displayBoard(boardOneOfEach);

    assertEquals(output.toString(), realOutput.toString());
  }

  /**
   * Method to test displayBoard with no ships
   */
  @Test
  void displayBoardNoShips() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();

    Interaction[] interactions = {
        new PrintInteraction(boardOpp)
    };

    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    View view = new View(realOutput, input);
    view.displayBoard(oppBoard);

    assertEquals(output.toString(), realOutput.toString());
  }

  /**
   * Method to test displayMessage
   */
  @Test
  void displayMessage() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();
    String message = "Test message";

    Interaction[] interactions = {
        new PrintInteraction(message)
    };

    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    View view = new View(realOutput, input);
    view.displayMessage(message);

    assertEquals(output.toString(), realOutput.toString());
  }

  /**
   * Method to test putSeparator
   */
  @Test
  void putSeparator() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();
    String message = "Test message";

    Interaction[] interactions = {
        new PrintInteraction(message),
        new PrintInteraction(seperator)
    };

    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    View view = new View(realOutput, input);
    view.displayMessage(message);
    view.putSeparator();

    assertEquals(output.toString(), realOutput.toString());

  }



  /**
   * Method to test getInt
   */
  @Test
  void getInt() {
    StringBuilder inputBuild = new StringBuilder();
    StringBuilder output = new StringBuilder();

    Interaction[] interactions = {
        new InputInteraction("5")
    };

    TestAuto.buildInteractions(inputBuild, output, interactions);

    StringBuilder realOutput = new StringBuilder();
    StringReader input = new StringReader(inputBuild.toString());

    View view = new View(realOutput, input);
    int intInput = view.getInt();

    assertEquals(5, intInput);

  }

  @Test
  void testException() {
    StringReader reader = new StringReader("");

    View view = new View(mockAppendable, reader);

    assertThrows(RuntimeException.class, () -> view.displayBoard(boardOneOfEach));
    assertThrows(RuntimeException.class, () -> view.displayMessage("boardOneOfEach"));
    assertThrows(RuntimeException.class, () -> view.putSeparator());

  }
}