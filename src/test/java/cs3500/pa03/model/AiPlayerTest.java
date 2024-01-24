package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Method to test AIPlayer
 */
class AiPlayerTest {
  AiPlayer player;
  Player tenPlayer;


  /**
   * Method to set up the data
   */
  @BeforeEach
  void init() {
    player = new AiPlayer(new Random(4));
    HashMap<ShipType, Integer> spec = new HashMap<>();
    spec.put(ShipType.CARRIER, 1);
    spec.put(ShipType.BATTLESHIP, 1);
    spec.put(ShipType.SUBMARINE, 1);
    spec.put(ShipType.DESTROYER, 1);
    player.setup(8, 8, spec);

    tenPlayer = new AiPlayer();
  }

  /**
   * Method to test name
   */
  @Test
  void name() {
    assertEquals("Ben's AI Player", player.name());
  }

  /**
   * Method to test take shots
   */
  @Test
  void takeShots() {
    ArrayList<Coord> allShots = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
      List<Coord> shot1 = player.takeShots();
      allShots.addAll(shot1);
      List<Coord> shot2 = player.takeShots();
      for (Coord shot : shot2) {
        assertFalse(allShots.contains(shot));
      }
      allShots.addAll(shot2);
    }
  }

  /**
   * Method to test take shots
   */
  @Test
  void takeShotsNumber() {
    List<Coord> shot = player.takeShots();
    assertEquals(4, shot.size());

    ArrayList<Coord> damage = new ArrayList<>();
    damage.add(new Coord(2, 1));
    damage.add(new Coord(3, 1));
    damage.add(new Coord(4, 1));
    player.reportDamage(damage);

    shot = player.takeShots();

    assertEquals(3, shot.size());

    damage.clear();

    damage.add(new Coord(3, 0));
    damage.add(new Coord(4, 0));
    damage.add(new Coord(5, 0));
    damage.add(new Coord(6, 0));
    damage.add(new Coord(7, 0));

    player.reportDamage(damage);

    shot = player.takeShots();

    assertEquals(2, shot.size());
  }


  @Test
  void testTargetShots() {
    List<Coord> shotsWork = List.of(new Coord(1, 1));
    player.successfulHits(shotsWork);

    List<Coord> playerShots = player.takeShots();

    assertTrue(playerShots.contains(new Coord(1, 0)));
    assertTrue(playerShots.contains(new Coord(2, 1)));
    assertTrue(playerShots.contains(new Coord(0, 1)));
    assertTrue(playerShots.contains(new Coord(1, 2)));



  }

}