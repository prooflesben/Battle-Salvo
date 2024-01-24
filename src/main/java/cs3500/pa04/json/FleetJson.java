package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * To represent JSON for a fleet of ships made up of a list of ShipJson
 *
 * @param ships in the fleet
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipJson> ships
) {



}
