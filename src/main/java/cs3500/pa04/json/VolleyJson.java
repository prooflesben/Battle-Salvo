package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * To represent JSON of a volley, a list of coordinates between players of BattleSalvo
 *
 * @param coords the coordinate of the shots the player has taken
 */
public record VolleyJson(
    @JsonProperty("coordinates") List<Coord> coords
) {
}
