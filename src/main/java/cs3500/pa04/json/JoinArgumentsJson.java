package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * To represent the JSON for the arguments needed ot join the server
 *
 * @param gitUser the GitHub username of the user
 * @param gameType the type of game the user would like to play, SINGLE or MULTI
 */
public record JoinArgumentsJson(
    @JsonProperty("name") String gitUser,
    @JsonProperty("game-type") String gameType
) {
}
