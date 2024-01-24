package cs3500.pa03;

/**
 * Represents a user providing the program with  an input
 */
public class InputInteraction implements Interaction {
  String input;

  public InputInteraction(String input) {
    this.input = input;
  }

  /**
   * Method to apply an input interaction to the in StringBuilder
   *
   * @param in  Program input as a string builder
   * @param out Program output as a string builder
   */
  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input);
  }
}
