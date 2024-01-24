package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple utils class used to hold static methods that help with serializing and deserializing JSON.
 */
public class JsonUtils {

  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(String name, Record record) throws
      IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode args = mapper.convertValue(record, JsonNode.class);
      MessageJson messageJson = new MessageJson(name, args);

      return mapper.convertValue(messageJson, JsonNode.class);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   * Converts a given string of a record object to a JsonNode with empty arguments
   *
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(String name) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode args = mapper.createObjectNode();
      MessageJson messageJson = new MessageJson(name, args);

      return mapper.convertValue(messageJson, JsonNode.class);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
}
