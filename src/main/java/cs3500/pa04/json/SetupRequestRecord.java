package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.ShipType;
import java.util.Map;

/**
 * To represent the JSON sent to the server for setup
 *
 * @param width     the width of the board
 * @param height    the height of the board
 * @param fleetSpec the number of each ship to be placed on the board
 */
public record SetupRequestRecord(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpecJson fleetSpec
) {
  public Map<ShipType, Integer> getSpecs() {
    return fleetSpec.getSpecs();
  }
}
