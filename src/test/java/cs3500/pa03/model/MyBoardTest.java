package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test MyBoard
 */
class MyBoardTest {
  Iboard boardOneOfEach;
  Random random = new Random(4);
  ArrayList<AgamePiece> ships;
  Iboard board9x15;

  /**
   * Method to set up data
   */
  @BeforeEach
  void init() {
    ships = new ArrayList<>(
        Arrays.asList(new Carrier(), new Battleship(), new Submarine(), new Destroyer()));
    boardOneOfEach = new MyBoard(8, 8, ships, random);
    ArrayList<AgamePiece> ships9 = new ArrayList<>();
    ships9.add(new Carrier());
    ships9.add(new Carrier());
    ships9.add(new Battleship());
    ships9.add(new Submarine());
    ships9.add(new Submarine());
    ships9.add(new Destroyer());
    ships9.add(new Destroyer());
    board9x15 = new MyBoard(9, 15, ships9, new Random(9));
  }

  /**
   * Method to test generateBoard
   */
  @Test
  void generateBoard() {
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

    assertEquals(board, boardOneOfEach.showBoard());

    boardOneOfEach = new MyBoard(8, 8, ships);

    assertFalse(boardOneOfEach.showBoard().equals(board));

    String board2 = """
        ~~~~~~~~S~~~~~~
        ~CCCCCC~S~~~~~~
        ~~~DDDD~S~~~~~~
        ~~~~~BBBBB~~~~~
        ~~~~~~~~~~~~~~~
        ~~~~SSS~~~~~~~~
        ~~CCCCCC~~~~~~~
        ~~DDDD~~~~~~~~~
        ~~~~~~~~~~~~~~~
            """;

    assertEquals(board2, board9x15.showBoard());

  }

  /**
   * Method to test showBoard
   */
  @Test
  void showBoard() {
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

    assertEquals(board, boardOneOfEach.showBoard());
  }

  /**
   * Method to test reportDamage
   */
  @Test
  void reportDamage() {
    List<Coord> damage = new ArrayList<>(Arrays.asList(new Coord(3, 0), new Coord(2, 1),
        new Coord(5, 2), new Coord(2, 3)));

    List<Coord> expectedDamage = boardOneOfEach.reportDamage(damage);

    assertEquals(expectedDamage, damage);

    List<Coord> damage2 = new ArrayList<>(Arrays.asList(new Coord(0, 0)));

    List<Coord> expectedDamage2 = boardOneOfEach.reportDamage(damage2);

    assertEquals(expectedDamage2, new ArrayList<Coord>());

    List<Coord> damage3 = new ArrayList<>(Arrays.asList(new Coord(0, 0), new Coord(2, 3)));

    List<Coord> expectedDamage3 = boardOneOfEach.reportDamage(damage3);

    assertEquals(expectedDamage3, new ArrayList<>(Arrays.asList(new Coord(2, 3))));
  }

  /**
   * Method to test takeDamage
   */
  @Test
  void takeDamage() {
    ArrayList<Coord> damage = new ArrayList<>(Arrays.asList(new Coord(3, 0), new Coord(2, 1),
        new Coord(5, 2), new Coord(2, 3)));

    boardOneOfEach.takeDamage(damage, Status.HIT);
    String board = """
        ~~~XBBBB
        ~~XSS~~~
        ~~~~~X~~
        ~~X~~C~~
        ~~D~~C~~
        ~~D~~C~~
        ~~D~~C~~
        ~~~~~C~~
            """;

    assertEquals(board, boardOneOfEach.showBoard());


  }

  /**
   * Method to test onBoard
   */
  @Test
  void onBoard() {
    Coord p1 = new Coord(-5, 5);
    assertFalse(boardOneOfEach.onBoard(p1));

    Coord p2 = new Coord(4, -6);
    assertFalse(boardOneOfEach.onBoard(p2));

    Coord p3 = new Coord(30, 5);
    assertFalse(boardOneOfEach.onBoard(p3));

    Coord p4 = new Coord(4, 60);
    assertFalse(boardOneOfEach.onBoard(p4));

    Coord p5 = new Coord(5, 5);
    assertTrue(boardOneOfEach.onBoard(p5));


  }
}