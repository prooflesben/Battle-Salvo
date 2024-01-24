package cs3500.pa04;

import cs3500.pa03.controller.Controller;
import cs3500.pa04.controller.AiController;
import cs3500.pa04.controller.ProxyController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * To represent the driver and entry point of BattleSalvo
 */
public class Driver {
  /**
   * This method connects to the server at the given host and port, builds a proxy referee
   * to handle communication with the server, and sets up a client player.
   *
   * @param host the server host
   * @param port the server port
   * @throws IOException if there is a communication issue with the server
   */
  private static void runClient(String host, int port)
      throws IOException, IllegalStateException {
    Socket server = new Socket(host, port);
    ProxyController proxyDealer = new ProxyController(server, new AiController());

    proxyDealer.run();
  }

  /**
   * The main entrypoint into the code as the Client. Given a host and port as parameters, the
   * client is run. If there is an issue with the client or connecting,
   * an error message will be printed.
   *
   * @param args The expected parameters are the server's host and port
   */
  public static void main(String[] args) {
    if (args.length == 2) {
      String host = args[0];
      int port = Integer.parseInt(args[1]);
      try {
        Driver.runClient(host, port);
      } catch (IOException e) {
        throw new RuntimeException(e);

      }
    } else {
      Controller controller = new Controller(new InputStreamReader(System.in), System.out);
      controller.playGame();
    }
  }
}
