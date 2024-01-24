package cs3500.pa03;


/**
 * Class to auto add interactions to input and output
 */
public class TestAuto {
  /**
   * Class to auto add interactions to input and output
   *
   * @param input        StringBuilder used for input
   * @param output       StringBuilder used for output
   * @param interactions array of interactions
   */
  public static void buildInteractions(
      StringBuilder input, StringBuilder output, Interaction[] interactions) {
    for (Interaction inter : interactions) {
      inter.apply(input, output);
    }
  }
}

