package cs3500.pa03;

/**
 * Represents the printing of a sequence of lines to output
 */
public class PrintInteraction implements Interaction {
  String[] lines;

  /**
   * Constructor to take in the lines to be added to the print
   *
   * @param lines String that is the output lines
   */
  public PrintInteraction(String... lines) {
    this.lines = lines;
  }

  /**
   * Method to apply a print interaction to the in StringBuilder
   *
   * @param in  Program input as a string builder
   * @param out Program output as a string builder
   */
  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");
    }
  }
}
