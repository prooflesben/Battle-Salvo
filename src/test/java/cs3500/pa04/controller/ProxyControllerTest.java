package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Direction;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.EndGameArguments;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.FleetSpecJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupRequestRecord;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.json.VolleyJson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * To represent tests of the proxy controller
 */
class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController controller;

  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Check that the server returns a guess when given a hint.
   */
  @Test
  public void testJoin() {
    ShipJson shipJson1 = new ShipJson(new Coord(0, 0), 5, Direction.VERTICAL);
    ShipJson shipJson2 = new ShipJson(new Coord(0, 8), 6, Direction.HORIZONTAL);
    ShipJson shipJson3 = new ShipJson(new Coord(1, 0), 3, Direction.VERTICAL);
    ShipJson shipJson4 = new ShipJson(new Coord(4, 0), 4, Direction.VERTICAL);
    List<ShipJson> shipJsons = new ArrayList<>();
    shipJsons.add(shipJson1);
    shipJsons.add(shipJson2);
    shipJsons.add(shipJson3);
    shipJsons.add(shipJson4);

    // Prepare sample message
    FleetJson fleetJson = new FleetJson(shipJsons);
    JsonNode sampleMessage = createSampleMessage("join", fleetJson);

    MockSocket socket = new MockSocket(this.testLog, List.of(sampleMessage.toString()));

    // Create a Dealer
    try {
      this.controller = new ProxyController(socket, new AiController());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // run the dealer and verify the response
    this.controller.run();

    String expected = "{\"method-name\":\"join\",\"arguments\":{\"name\":\"miayim\",\""
        + "game-type\":\"SINGLE\"}}\n";
    expected = expected.replaceAll("\\r\\n|\\r|\\n", System.lineSeparator());
    String real = logToString().replaceAll("\\r\\n|\\r|\\n", System.lineSeparator());
    assertEquals(expected, real);
  }


  @Test
  public void testSetup() {
    FleetSpecJson fleetSpecJson =  new FleetSpecJson(1, 1, 1, 1);
    SetupRequestRecord setupRequestRecord = new SetupRequestRecord(10, 12,
        fleetSpecJson);
    JsonNode jsonNode = createSampleMessage("setup", setupRequestRecord);

    // Create socket with sample input
    MockSocket socket = new MockSocket(this.testLog, List.of(jsonNode.toString()));

    // Create a dealer
    try {
      this.controller = new ProxyController(socket, new AiController());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testTakeShots() {
    FleetSpecJson fleetSpecJson =  new FleetSpecJson(1, 1, 1, 1);
    SetupRequestRecord setupRequestRecord = new SetupRequestRecord(10, 12,
        fleetSpecJson);

    JsonNode jsonNode = createSampleMessage("take-shots", setupRequestRecord);

    // Create socket with sample input
    MockSocket socket = new MockSocket(this.testLog, List.of(jsonNode.toString()));

    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);

    // Create a dealer
    try {
      AiController aiController = new AiController();
      aiController.setup(10, 12, specs);
      this.controller = new ProxyController(socket, aiController);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testReportDamage() {
    Coord coord1 =  new Coord(0, 1);
    Coord coord2 =  new Coord(0, 2);
    Coord coord3 =  new Coord(0, 3);
    Coord coord4 =  new Coord(0, 4);

    List<Coord> coords = new ArrayList<>();
    coords.add(coord1);
    coords.add(coord2);
    coords.add(coord3);
    coords.add(coord4);

    VolleyJson volleyJson =  new VolleyJson(coords);

    JsonNode jsonNode = createSampleMessage("report-damage", volleyJson);

    // Create socket with sample input
    MockSocket socket = new MockSocket(this.testLog, List.of(jsonNode.toString()));

    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);

    // Create a dealer
    try {
      AiController aiController = new AiController();
      aiController.setup(10, 12, specs);
      this.controller = new ProxyController(socket, aiController);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testSuccessfulHits() {
    Coord coord1 =  new Coord(0, 1);
    Coord coord2 =  new Coord(0, 2);
    Coord coord3 =  new Coord(0, 3);
    Coord coord4 =  new Coord(0, 4);

    List<Coord> coords = new ArrayList<>();
    coords.add(coord1);
    coords.add(coord2);
    coords.add(coord3);
    coords.add(coord4);

    VolleyJson volleyJson =  new VolleyJson(coords);

    JsonNode jsonNode = createSampleMessage("successful-hits", volleyJson);

    // Create socket with sample input
    MockSocket socket = new MockSocket(this.testLog, List.of(jsonNode.toString()));

    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);

    // Create a dealer
    try {
      AiController aiController = new AiController();
      aiController.setup(10, 12, specs);
      this.controller = new ProxyController(socket, aiController);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testEndGame() {
    EndGameArguments endGameArguments = new EndGameArguments("WIN", "you won!");

    JsonNode jsonNode = createSampleMessage("end-game", endGameArguments);

    // Create socket with sample input
    MockSocket socket = new MockSocket(this.testLog, List.of(jsonNode.toString()));

    // Create a dealer
    try {
      AiController aiController = new AiController(new Random(6));
      this.controller = new ProxyController(socket, aiController);
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    this.controller.run();
    responseToClass(MessageJson.class);
  }

  @Test
  public void testInvalidMessage() {

    FleetSpecJson fleetSpecJson =  new FleetSpecJson(1, 1, 1, 1);
    SetupRequestRecord setupRequestRecord = new SetupRequestRecord(10, 12,
        fleetSpecJson);
    JsonNode jsonNode = createSampleMessage("invalid", setupRequestRecord);

    MockSocket socket = new MockSocket(this.testLog, List.of(jsonNode.toString()));

    try {
      this.controller = new ProxyController(socket, new AiController());
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    // Run dealer and verify response.
    assertThrows(IllegalStateException.class, () -> this.controller.run());

  }



  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson = new MessageJson(messageName,
        JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }

}