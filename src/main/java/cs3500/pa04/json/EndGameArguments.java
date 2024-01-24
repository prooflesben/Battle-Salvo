package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * To represent JSON for the end of a game
 *
 * @param result outcome of the game, win, lose, or draw
 * @param reason the reason the game ended
 */
public record EndGameArguments(
    @JsonProperty("result") String result,
    @JsonProperty("reason") String reason
) {
}
