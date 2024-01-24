package cs3500.pa03.view;

import cs3500.pa03.model.Iboard;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to represent the view portion of the program and takes in inputs and outputs outputs
 */
public class View {
  public static final String seperator = "------------------------------------------------------";
  private final Appendable appendable;
  private final Scanner scan;

  /**
   * Constructor to set up the view
   *
   * @param appendable appendable for output
   * @param readable   readable for inputs
   */
  public View(Appendable appendable, Readable readable) {
    this.appendable = appendable;
    this.scan = new Scanner(readable);
  }

  /**
   * Method to display a board
   *
   * @param board battleship board
   */
  public void displayBoard(Iboard board) {
    try {
      this.appendable.append(board.showBoard());
      this.appendable.append("\n");
    } catch (IOException e) {
      exceptionOccured();
    }
  }

  /**
   * Method to display a message
   *
   * @param message message to be displayed
   */
  public void displayMessage(String message) {
    try {
      this.appendable.append(message);
      this.appendable.append("\n");
    } catch (IOException e) {
      exceptionOccured();
    }

  }

  /**
   * Method to put a separator
   */
  public void putSeparator() {
    try {
      this.appendable.append(seperator);
      this.appendable.append("\n");
    } catch (IOException e) {
      exceptionOccured();
    }
  }

  /**
   * Method to get an int
   *
   * @return integer
   */
  public int getInt() {
    return scan.nextInt();
  }

  /**
   * Method to handle the exception
   */
  private void exceptionOccured() {
    try {
      this.appendable.append("An exception occurred.");
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

}
