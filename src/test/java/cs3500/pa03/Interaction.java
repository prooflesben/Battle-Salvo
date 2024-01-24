package cs3500.pa03;

/**
 * Interface to represent an interaction used for testing
 */
public interface Interaction {
  /**
   * Method to apply input to either the input or output string builder
   *
   * @param sb1 StringBuilder input
   * @param sb2 StringBuilder output
   */
  void apply(StringBuilder sb1, StringBuilder sb2);
}
