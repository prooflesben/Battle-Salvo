package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class to test OpponentBoard
 */
class OpponentBoardTest {
  Iboard opponetBoard = new OpponentBoard(8, 8);

  @BeforeEach
  void init() {
    opponetBoard = new OpponentBoard(8, 8);
  }

  @Test
  void reportDamage() {
    ArrayList<Coord> shots =
        new ArrayList<>(Arrays.asList(new Coord(4, 4), new Coord(0, 0)));

    ArrayList<Coord> shots2 =
        new ArrayList<>(Arrays.asList(new Coord(4, 4), new Coord(0, 0),
            new Coord(3, 9)));

    opponetBoard.takeDamage(shots, Status.HIT);

    List<Coord> report = opponetBoard.reportDamage(shots2);

    ArrayList<Coord> reportExpected =
        new ArrayList<>(Arrays.asList(new Coord(0, 0), new Coord(4, 4)));

    assertEquals(reportExpected, report);

  }

  /**
   * Method to test generateBoard
   */
  @Test
  void generateBoard() {
    String board5x9 =
        """
            ~~~~~~~~~
            ~~~~~~~~~
            ~~~~~~~~~
            ~~~~~~~~~
            ~~~~~~~~~
            """;

    Iboard oppBoard5x9 = new OpponentBoard(5, 9);

    assertEquals(board5x9, oppBoard5x9.showBoard());

  }

  /**
   * Method to test showBoard
   */
  @Test
  void showBoard() {
    String board =
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

    assertEquals(board, opponetBoard.showBoard());


  }

  /**
   * Method to test onBoard
   */
  @Test
  void onBoard() {
    Coord p1 = new Coord(-5, 5);
    assertFalse(opponetBoard.onBoard(p1));

    Coord p2 = new Coord(4, -6);
    assertFalse(opponetBoard.onBoard(p2));

    Coord p3 = new Coord(30, 5);
    assertFalse(opponetBoard.onBoard(p3));

    Coord p4 = new Coord(4, 60);
    assertFalse(opponetBoard.onBoard(p4));

    Coord p5 = new Coord(5, 5);
    assertTrue(opponetBoard.onBoard(p5));
  }

  /**
   * Method to test takeDamage
   */
  @Test
  void takeDamage() {
    ArrayList<Coord> shots =
        new ArrayList<>(Arrays.asList(new Coord(4, 4), new Coord(0, 0)));
    String board =
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

    String boardMiss =
        """
            M~~~~~~~
            ~~~~~~~~
            ~~~~~~~~
            ~~~~~~~~
            ~~~~M~~~
            ~~~~~~~~
            ~~~~~~~~
            ~~~~~~~~
            """;
    assertEquals(board, opponetBoard.showBoard());

    opponetBoard.takeDamage(shots, Status.MISS);

    assertEquals(boardMiss, opponetBoard.showBoard());

    opponetBoard.takeDamage(shots, Status.HIT);
    String boardHit =
        """
            X~~~~~~~
            ~~~~~~~~
            ~~~~~~~~
            ~~~~~~~~
            ~~~~X~~~
            ~~~~~~~~
            ~~~~~~~~
            ~~~~~~~~
            """;
    assertEquals(boardHit, opponetBoard.showBoard());


  }
}