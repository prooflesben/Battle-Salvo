package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To test EndGameArguments JSON
 */
class EndGameArgumentsTest {
  EndGameArguments endGameArguments;

  @BeforeEach
  void init() {
    endGameArguments = new EndGameArguments("win", "You are cool");
  }

  @Test
  void getFields() {
    assertEquals("win", endGameArguments.result());
    assertEquals("You are cool", endGameArguments.reason());
  }
}