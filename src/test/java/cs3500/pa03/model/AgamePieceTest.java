package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test AGamePiece
 */
class AgamePieceTest {
  AgamePiece carrier;
  AgamePiece battleship;
  AgamePiece submarine;
  AgamePiece destroyer;
  AgamePiece water;
  Coord p0 = new Coord(0, 0);
  Coord p01 = new Coord(0, 1);
  Coord p02 = new Coord(0, 2);
  Coord p03 = new Coord(0, 3);
  Coord p04 = new Coord(0, 4);
  Coord p05 = new Coord(0, 5);

  Coord p10 = new Coord(1, 0);
  Coord p20 = new Coord(2, 0);
  Coord p30 = new Coord(3, 0);
  Coord p40 = new Coord(4, 0);
  Coord p50 = new Coord(5, 0);

  /**
   * Method to set up the data
   */
  @BeforeEach
  void init() {
    carrier = new Carrier();
    battleship = new Battleship();
    submarine = new Submarine();
    destroyer = new Destroyer();
    water = new Water();
  }

  /**
   * Method to test placeCoordinates
   */
  @Test
  void placeCoordinates() {

    assertTrue(Arrays.equals(new Coord[] {p0, p01, p02, p03, p04, p05},
        carrier.placeCoordinates(p0, false)));
    assertTrue(Arrays.equals(new Coord[] {p0, p01, p02, p03, p04},
        battleship.placeCoordinates(p0, false)));
    assertTrue(Arrays.equals(new Coord[] {p0, p01, p02, p03},
        destroyer.placeCoordinates(p0, false)));
    assertTrue(Arrays.equals(new Coord[] {p0, p01, p02},
        submarine.placeCoordinates(p0, false)));

    assertTrue(Arrays.equals(new Coord[] {p0, p10, p20, p30, p40, p50},
        carrier.placeCoordinates(p0, true)));
    assertTrue(Arrays.equals(new Coord[] {p0, p10, p20, p30, p40},
        battleship.placeCoordinates(p0, true)));
    assertTrue(Arrays.equals(new Coord[] {p0, p10, p20, p30},
        destroyer.placeCoordinates(p0, true)));
    assertTrue(Arrays.equals(new Coord[] {p0, p10, p20},
        submarine.placeCoordinates(p0, true)));
  }

  /**
   * Method to test symbol
   */
  @Test
  void symbol() {
    Coord p0 = new Coord(0, 0);
    carrier.placeCoordinates(p0, false);
    battleship.placeCoordinates(p0, false);
    destroyer.placeCoordinates(p0, false);
    submarine.placeCoordinates(p0, false);
    water.placeCoordinates(p0, false);


    assertEquals("C", carrier.symbol(p0));
    assertEquals("B", battleship.symbol(p0));
    assertEquals("D", destroyer.symbol(p0));
    assertEquals("S", submarine.symbol(p0));
    assertEquals("~", water.symbol(p0));

    assertThrows(IllegalArgumentException.class, () -> carrier.symbol(new Coord(-1, 1)));
    assertThrows(IllegalArgumentException.class, () -> battleship.symbol(new Coord(-1, 1)));
    assertThrows(IllegalArgumentException.class, () -> destroyer.symbol(new Coord(-1, 1)));
    assertThrows(IllegalArgumentException.class, () -> submarine.symbol(new Coord(-1, 1)));


  }

  /**
   * Method to test is water
   */
  @Test
  void isWater() {
    assertFalse(carrier.isWater());
    assertFalse(submarine.isWater());
    assertFalse(destroyer.isWater());
    assertFalse(battleship.isWater());
    assertTrue(water.isWater());
  }

  /**
   * Method to test toString
   */
  @Test
  void testToString() {
    assertEquals("C", carrier.toString());
    assertEquals("B", battleship.toString());
    assertEquals("D", destroyer.toString());
    assertEquals("S", submarine.toString());
    assertEquals("~", water.toString());
  }

  /**
   * Method to tet hit
   */
  @Test
  void hit() {
    carrier.placeCoordinates(p0, false);
    battleship.placeCoordinates(p0, false);
    destroyer.placeCoordinates(p0, false);
    submarine.placeCoordinates(p0, false);
    water.placeCoordinates(p0, false);

    carrier.hit(p0);
    battleship.hit(p0);
    destroyer.hit(p0);
    submarine.hit(p0);
    water.hit(p0);

    assertEquals("X", carrier.symbol(p0));
    assertEquals("X", battleship.symbol(p0));
    assertEquals("X", destroyer.symbol(p0));
    assertEquals("X", submarine.symbol(p0));
    assertEquals("M", water.symbol(p0));

    init();
    carrier.placeCoordinates(p0, false);
    battleship.placeCoordinates(p0, false);
    destroyer.placeCoordinates(p0, false);
    submarine.placeCoordinates(p0, false);
    water.placeCoordinates(p0, false);

    carrier.hit(p0);
    battleship.hit(p0);
    destroyer.hit(p0);
    submarine.hit(p0);
    water.hit(p0);



    assertThrows(IllegalArgumentException.class, () -> carrier.symbol(new Coord(-1, 1)));
    assertThrows(IllegalArgumentException.class, () -> battleship.symbol(new Coord(-1, 1)));
    assertThrows(IllegalArgumentException.class, () -> destroyer.symbol(new Coord(-1, 1)));
    assertThrows(IllegalArgumentException.class, () -> submarine.symbol(new Coord(-1, 1)));
  }

  /**
   * Method to test isDestroyed
   */
  @Test
  void isDestroyed() {
    carrier.placeCoordinates(p0, false);
    assertFalse(carrier.isDestroyed());
    for (int i = 0; i < 6; i++) {
      carrier.hit(new Coord(0, i));
    }

    assertTrue(carrier.isDestroyed());

  }

}