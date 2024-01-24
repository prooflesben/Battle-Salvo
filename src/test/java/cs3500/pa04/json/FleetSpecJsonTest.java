package cs3500.pa04.json;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To represent tests for FleetSpecJson
 */
class FleetSpecJsonTest {
  FleetSpecJson fleetSpecJson;

  @BeforeEach
  void init() {
    fleetSpecJson = new FleetSpecJson(3, 2, 1, 2);

  }

  @Test
  void getSpecs() {
    Map<ShipType, Integer> expectedMap = new HashMap<>();
    expectedMap.put(ShipType.CARRIER, 3);
    expectedMap.put(ShipType.BATTLESHIP, 2);
    expectedMap.put(ShipType.DESTROYER, 1);
    expectedMap.put(ShipType.SUBMARINE, 2);

    assertEquals(expectedMap, fleetSpecJson.getSpecs());
    assertTrue(true);
  }

  @Test
  void getFieldsTest() {
    assertEquals(3, fleetSpecJson.carrier());
    assertEquals(2, fleetSpecJson.battleship());
    assertEquals(1, fleetSpecJson.destroyer());
    assertEquals(2, fleetSpecJson.submarine());
  }
}