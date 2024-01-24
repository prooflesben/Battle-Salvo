package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.AgamePiece;
import cs3500.pa03.model.Coord;
import cs3500.pa04.json.EndGameArguments;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JoinArgumentsJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupRequestRecord;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.json.VolleyJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * To represent the controller that delegates between the AI players and the BattleSalvo
 * implementation
 */
public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AiController ai;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Construct an instance of a ProxyController.
   *
   * @param server the socket connection to the server
   * @param ai     the instance of the AI
   * @throws IOException if IO Exception occurs
   */
  public ProxyController(Socket server, AiController ai) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.ai = ai;
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }

  /**
   * Determines the type of request the server has sent and delegates to the
   * corresponding helper method with the message arguments.
   *
   * @param message the MessageJSON used to determine what the server has sent
   */
  private void delegateMessage(MessageJson message) {
    String name = message.methodName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin();
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots();
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  /**
   * Handles the join message arguments to join the server
   *
   */
  private void handleJoin() {
    JoinArgumentsJson joinArgumentsJson = new JoinArgumentsJson("miayim", "SINGLE");

    this.respond("join", joinArgumentsJson);
  }

  /**
   * Gets a list of ShipJsons from the list of ships
   *
   * @param ships a list of ships on the board
   */
  private List<ShipJson> getShipJsons(List<AgamePiece> ships) {
    ArrayList<ShipJson> shipJsons = new ArrayList<>();
    for (AgamePiece ship : ships) {
      shipJsons.add(ship.toJson());
    }
    return shipJsons;
  }

  /**
   * Handles the setup of the boards and responds with a fleet JSON
   *
   * @param arguments JSON node
   */
  private void handleSetup(JsonNode arguments) {
    SetupRequestRecord setupJson = this.mapper.convertValue(arguments, SetupRequestRecord.class);

    List<AgamePiece> ships = this.ai.setup(setupJson.height(), setupJson.width(),
        setupJson.getSpecs());
    List<ShipJson> shipJsons = this.getShipJsons(ships);
    FleetJson fleetJson = new FleetJson(shipJsons);

    this.respond("setup", fleetJson);
  }

  /**
   * Handles response to the server based on the name and record
   *
   * @param name the name of the record
   * @param response the response record
   */
  private void respond(String name, Record response) {
    JsonNode jsonResponse = JsonUtils.serializeRecord(name, response);
    this.out.println(jsonResponse);
  }

  /**
   * Handles response to the server based on the record
   *
   * @param name the name of the record
   */
  private void respond(String name) {
    JsonNode jsonResponse = JsonUtils.serializeRecord(name);
    this.out.println(jsonResponse);
  }

  /**
   * Handles shot taking in the game of Battle Salvo by returning a volley
   *
   */
  private void handleTakeShots() {
    List<Coord> shots = this.ai.getShots();

    VolleyJson volleyJson = new VolleyJson(shots);


    this.respond("take-shots", volleyJson);
  }

  /**
   * Handles the successful hits on the opponent board
   *
   * @param arguments JSON node
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    VolleyJson successfulHitsJson = this.mapper.convertValue(arguments,
        VolleyJson.class);
    List<Coord> shots = successfulHitsJson.coords();
    this.ai.successfulHits(shots);

    respond("successful-hits");
  }

  /**
   * Handles the damage done on this board
   *
   * @param arguments JSON node
   */
  private void handleReportDamage(JsonNode arguments) {
    VolleyJson reportDamageJson = this.mapper.convertValue(arguments,
        VolleyJson.class);
    List<Coord> shots = reportDamageJson.coords();

    List<Coord> damage = this.ai.reportDamage(shots);

    VolleyJson volleyJson = new VolleyJson(damage);


    respond("report-damage", volleyJson);

  }

  /**
   * Handles the end of a game of BattleSalvo
   *
   * @param arguments JSON node
   */
  private void handleEndGame(JsonNode arguments) {
    EndGameArguments endGameJson = this.mapper.convertValue(arguments,
        EndGameArguments.class);
    this.ai.endGame();

    respond("end-game");
  }
}
