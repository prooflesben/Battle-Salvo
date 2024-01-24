package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Direction;

/**
 * To represent JSON for ships in BattleSalvo
 *
 * @param coord the starting coordinate of the ship
 * @param length the length of the ship
 * @param direction the direction the ship is oriented
 */
public record ShipJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") Direction direction
) {
}
