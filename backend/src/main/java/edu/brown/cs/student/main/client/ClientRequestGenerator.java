package edu.brown.cs.student.main.client;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class is used for testing and generates requests to all of my POST request endpoints.
 * I generate requests here and see if my POST requests do what I want it to do.
 */
public class ClientRequestGenerator {
  private static final Gson GSON = new Gson();
  /**
   * In this method I make a POST request to insert a row given a specific tableName and values.
   *
   * @param tableName The tableName that I will be inserting a row into
   * @param values    The values that I will be inserting into the new row
   * @noinspection checkstyle:MagicNumber
   */
  public static void makeInsertPostRequest(String tableName, List<String> values) {
    HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(60)).build();
    String reqUri = "http://localhost:4567/insert";

    String param =
        "{\"tableName\":\"" + tableName + "\" , " + buildValuesArrayString(values) + "}";
    System.out.println("param: " + param);
    HttpRequest request =
        HttpRequest.newBuilder(URI.create(reqUri)).GET().POST(HttpRequest.BodyPublishers.ofString(
            param))
            .timeout(Duration.ofMinutes(2)).build();

    try {
      HttpResponse<String> apiResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(apiResponse.body());
      System.out.println(apiResponse.statusCode());
    } catch (IOException | InterruptedException e) {
      System.out.println("failed to send request");
    }


  }

  /**
   * @param values A list of String values
   * @return A String representation of a JSON array
   */
  private static String buildValuesArrayString(List<String> values) {
    //format: ""values" : ["",""]"
    StringBuilder sb = new StringBuilder("\"values\" : [");
    for (int i = 0; i < values.size() - 1; i++) {
      //not including last element here
      sb.append("\"").append(values.get(i)).append("\", ");
    }
    sb.append("\"").append(values.get(values.size() - 1)).append("\"]");
    return sb.toString();
  }

  /**
   * In this method I make a POST request to delete a row.
   *
   * @param tableName           The tableName I will be deleting a row from
   * @param updateConditionsMap a Map of the conditions that determines which row to delete.
   *                            The key is a column header and value is the value found in the row
   *                            I want to delete.
   * @noinspection checkstyle:MagicNumber
   */
  public static void makeDeletePostRequest(String tableName, Map<String, String>
      updateConditionsMap) {
    HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(60)).build();
    String reqUri = "http://localhost:4567/delete";
    String param =
        "{\"tableName\":\"" + tableName + "\" , " + "\"updateConditions\" "
            +
            buildStringFromMap(updateConditionsMap) + "}";
    System.out.println("param: " + param);
    HttpRequest request =
        HttpRequest.newBuilder(URI.create(reqUri)).GET().POST(HttpRequest.BodyPublishers.ofString(
            param))
            .timeout(Duration.ofMinutes(2)).build();

    try {
      HttpResponse<String> apiResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(apiResponse.body());
      System.out.println(apiResponse.statusCode());
    } catch (IOException | InterruptedException e) {
      System.out.println("failed to send request");
    }
  }

  /**
   * @param map A map that maps Strings to Strings
   * @return A String representation of a JSON Object based on the map passed in
   */
  private static String buildStringFromMap(Map<String, String> map) {
    //format: {"name" : "Jillian", "id" : "1"}
    //here the key is the column for the condition, value is what that column value to delete row is
    //ex: if I want to delete Jillian: {key: name, value: Jillian}
    StringBuilder sb = new StringBuilder(": {");
    List<String> keys = new ArrayList<>(map.keySet());
    for (int i = 0; i < keys.size(); i++) {
      String currCol = keys.get(i);
      sb.append("\"").append(currCol).append("\" : ").append("\"").append(map.get(currCol))
          .append("\"");
      if (i != keys.size() - 1) {
        //if it is not the last element I will add a comma
        sb.append(",");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  /**
   * @param tableName   The name of the table I will be updating a row from
   * @param colToNewVal A map that maps the column name to a new value
   * @param conditions  A map that maps a column name to its original value and functions as the
   *                    condition(s) that decides which row to update
   */
  public static void makeUpdatePostRequest(String tableName, Map<String, String> colToNewVal,
                                           Map<String, String> conditions) {
    HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(60)).build();
    String reqUri = "http://localhost:4567/update";
    String param =
        "{\"tableName\":\"" + tableName + "\" , " + "\"colNameToNewVal\" "
            +
            buildStringFromMap(colToNewVal) + ",\"conditions\" " + buildStringFromMap(conditions)
            +
            "}";

    System.out.println("param: " + param);
    HttpRequest request =
        HttpRequest.newBuilder(URI.create(reqUri)).GET().POST(HttpRequest.BodyPublishers.ofString(
            param))
            .timeout(Duration.ofMinutes(2)).build();

    try {
      HttpResponse<String> apiResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(apiResponse.body());
      System.out.println(apiResponse.statusCode());
    } catch (IOException | InterruptedException e) {
      System.out.println("failed to send request");
    }
  }

}
