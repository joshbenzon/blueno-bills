package backendhandlers;

import com.google.gson.Gson;
import databaseaccessor.DatabaseProxy;
import databaseaccessor.SQLTable;
import replcommands.ObjectOrganizer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * a class with methods for handling tables in a database.
 */
public class TableHandler implements Route {

  // the object organizer for the handle to get the database from
  private static ObjectOrganizer ob;
  private static DatabaseProxy dp;
  private static Set<String> tableNames;
  private static SQLTable table;
  private static final Gson GSON = new Gson();
  /**
   * a constructor for TableHandler.
   *
   * @param objectOrganizer the object organizer for the table handler, which will have the database
   */
  public TableHandler(ObjectOrganizer objectOrganizer) {
    ob = objectOrganizer;
  }

  /**
   * a static method which given a sqlite3 database file name, loads it into the backend.
   *
   * @param filename the name of the sqlite3 file
   * @return a Map from Table name to Table
   * @throws SQLException             if there is an error running one of the sql commands
   * @throws IllegalArgumentException if the filename parameter isn't a valid file
   */
  public static SQLTable loadDatabase(String filename)
      throws SQLException, IllegalArgumentException {

    // checking if the filename is valid
    File db = new File(filename);
    if (!db.exists()) {
      throw new IllegalArgumentException("ERROR: Invalid file name");
    }
    // initialize a table
    dp = new DatabaseProxy(filename);
    tableNames = getTableNames();
    String tableName = tableNames.iterator().next();
    table = getTable(tableName);
    //closing connection here, so I don't have multiple connects to same table open
    dp.closeConn();
    return table;
  }

  //TODO: javadocs
  //in this case: only 1 table but keeping it like this in case table added in future
  public static Set<String> getTableNames()
      throws SQLException, IllegalStateException {

//        PreparedStatement ps = .prepareStatement("SELECT tbl_name FROM sqlite_master;");
    ResultSet dbRes = dp.executeSQLCommand("SELECT tbl_name FROM sqlite_master;");

    Set<String> tableNames = new HashSet<>();
    while (dbRes.next()) {
      tableNames.add(dbRes.getString(1));
    }

    return tableNames;
  }

  //TODO: javadocs
  public static SQLTable getTable(String tableName)
      throws SQLException, IllegalStateException, IllegalArgumentException {
    if (tableName == null) {
      throw new IllegalArgumentException("ERROR: Cannot get null table.");
    }

    // Check the requested table exists.
    if (!tableNames.contains(tableName)) {
      throw new IllegalArgumentException(
          "ERROR: Table \"" + tableName + "\" does not exist.");
    }

    // Prepare a statement to get everything from the table.
//        PreparedStatement ps = SimpleProxy.prepareStatement("SELECT * FROM " + tableName + ";");
    ResultSet dbRes = dp.executeSQLCommand("SELECT * FROM " + tableName + ";");
    ResultSetMetaData dbResMeta = dbRes.getMetaData();

    // Get the column headers
    int numCols = dbResMeta.getColumnCount();
    List<String> columnNames = new ArrayList<>();
    for (int i = 1; i <= numCols; i++) {
      columnNames.add(dbResMeta.getColumnName(i));
    }


    // Add each row of the db to a list
    List<Map<String, String>> rows = new ArrayList<>();
    while (dbRes.next()) {
      Map<String, String> curRow = new LinkedHashMap<>();
      for (int i = 1; i <= numCols; i++) {

        curRow.put(
            dbResMeta.getColumnName(i),
            dbRes.getString(i)
        );
      }

      rows.add(curRow);
    }
    return new SQLTable(tableName, columnNames, rows);
  }



  @Override
  public Object handle(Request request, Response response) {
    if (ob.getTable() == null) {
      return "ERROR: Database not loaded";
    }
    try {
      return GSON.toJson(ob.getTable());
    } catch (IllegalStateException | IllegalArgumentException e) {
      return GSON.toJson(e.getMessage());
    }
  }
}
