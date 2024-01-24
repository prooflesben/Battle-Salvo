package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.ShipType;
import java.util.HashMap;
import java.util.Map;

/**
 * To represent attributes of a fleet of ships
 *
 * @param carrier    the number of carriers in the fleet
 * @param battleship the number of battleships in the fleet
 * @param destroyer  the number of destroyers in the fleet
 * @param submarine  the number of submarines in the fleet
 */
public record FleetSpecJson(
    @JsonProperty("CARRIER") int carrier,
    @JsonProperty("BATTLESHIP") int battleship,
    @JsonProperty("DESTROYER") int destroyer,
    @JsonProperty("SUBMARINE") int submarine
) {

  /**
   * Get specs map.
   *
   * @return the map
   */
  public Map<ShipType, Integer> getSpecs() {
    HashMap<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, carrier);
    specs.put(ShipType.BATTLESHIP, battleship);
    specs.put(ShipType.DESTROYER, destroyer);
    specs.put(ShipType.SUBMARINE, submarine);

    return specs;

  }
}
