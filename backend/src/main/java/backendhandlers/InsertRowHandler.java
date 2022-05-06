package backendhandlers;

import com.google.gson.Gson;
import databaseaccessor.DatabaseProxy;
import backendhandlers.replcommands.ObjectOrganizer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertRowHandler implements Route {
  private final ObjectOrganizer objectOrganizer;
  private static final Gson GSON = new Gson();


  public InsertRowHandler(ObjectOrganizer objectOrganizer) {
    this.objectOrganizer = objectOrganizer;
  }

  /**
   * @param request  The request object providing information about the HTTP request
   * @param response The response object providing functionality for modifying the response
   * @return The content to be set in the response
   * @throws Exception implementation can choose to throw exception
   */
  @Override
  public Object handle(Request request, Response response) throws Exception {
    JSONObject data = new JSONObject(request.body());
    String tableName = data.getString("tableName");
    String fileName = null;
    try {
      fileName = objectOrganizer.getFileName();
    } catch (Exception e) {
      System.out.println("ERROR: fileName is not known");
    }
    DatabaseProxy db = new DatabaseProxy(fileName);
    List<String> headers = this.getHeaders(tableName, db);
    JSONArray values = data.getJSONArray("values");
    List<String> valueList = jsonArrToStrArr(values);
    String sqlString = this.buildSqlString(tableName, headers, valueList);
    System.out.println("SQL: " + sqlString);
    int insertRowQ = db.executeWCommands(sqlString);
    if (insertRowQ == -1) { //means an error was thrown
      return GSON.toJson("ERROR: was not able to execute insertion command");
    }
    db.closeConn();
    return GSON.toJson("Success");
  }

  /**
   * @param tableName     The tableName from which I will be getting the headers from
   * @param databaseProxy A class that I use to establish the connection to the database and
   *                      execute SQL commands
   * @return A List of Strings that represent the column names of the table
   */
  private List<String> getHeaders(String tableName, DatabaseProxy databaseProxy) {
    if (tableName == null) {
      throw new IllegalArgumentException("ERROR: Cannot get null table.");
    }
    ResultSet dbRes = null;
    try {
      dbRes = databaseProxy.executeSQLCommand("SELECT * FROM " + tableName + ";");
    } catch (SQLException throwables) {
      System.out.println("ERROR: " + throwables.getMessage());
    }
    ResultSetMetaData dbResMeta = null;
    try {
      if (dbRes != null) {
        dbResMeta = dbRes.getMetaData();
      }
    } catch (SQLException throwables) {
      System.out.println("ERROR: " + throwables.getMessage());
    }

    // Get the column headers
    int numCols = 0;
    try {
      if (dbResMeta != null) {
        numCols = dbResMeta.getColumnCount();
      }
    } catch (SQLException throwables) {
      System.out.println("ERROR: " + throwables.getMessage());
    }
    List<String> columnNames = new ArrayList<>();
    for (int i = 1; i <= numCols; i++) {
      try {
        columnNames.add(dbResMeta.getColumnName(i));
      } catch (SQLException throwables) {
        System.out.println("ERROR: " + throwables.getMessage());
      }
    }
    return columnNames;

  }


  /**
   * @param jsonArray The JSON array object that I will be converting to a String
   * @return A string representation of a JSON array
   */
  private static List<String> jsonArrToStrArr(JSONArray jsonArray) {
    List<String> rowList = new ArrayList<>();
    for (int i = 0; i < jsonArray.length(); i++) {
      try {
        rowList.add(jsonArray.getString(i));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return rowList;
  }

  /**
   * @param tableName The name of the table I will be inserting a row into
   * @param headers   The column names of the table
   * @param values    The values I will be inserting into the table
   * @return The SQL string that when executed will insert the row into the table
   */
  private String buildSqlString(String tableName, List<String> headers, List<String> values) {
    //INSERT INTO table_name (column1, column2, column3, ...)
    //VALUES (value1, value2, value3, ...);
    StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " (");
    //adding headers to sql string
    for (int i = 0; i < headers.size() - 1; i++) {
      insertQuery.append(headers.get(i)).append(", ");
    }
    insertQuery.append(headers.get(headers.size() - 1)).append(") VALUES (");
    //now will add values to string
    for (int i = 0; i < values.size() - 1; i++) {
      insertQuery.append("'").append(values.get(i)).append("'").append(", ");
    }
    insertQuery.append("'").append(values.get(headers.size() - 1)).append("')");
    return String.valueOf(insertQuery);
  }

}
