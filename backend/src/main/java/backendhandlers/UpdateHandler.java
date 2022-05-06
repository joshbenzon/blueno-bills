package backendhandlers;

import com.google.gson.Gson;
import databaseaccessor.DatabaseProxy;
import backendhandlers.replcommands.ObjectOrganizer;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateHandler implements Route {

  private static final Gson GSON = new Gson();
  private final ObjectOrganizer objectOrganizer;

  public UpdateHandler(ObjectOrganizer objectOrganizer) {
  this.objectOrganizer = objectOrganizer;
  }

  @Override
  public Object handle(Request request, Response response) throws Exception {
    JSONObject reqData = new JSONObject(request.body());
    String tableName = reqData.getString("tableName");
    JSONObject colNameToOldValJSON = reqData.getJSONObject("conditions");

    //original row data
    Map<String, String>
        conditionsMap = GSON.fromJson(String.valueOf(colNameToOldValJSON), Map.class);

    //row data updates
    JSONObject colNameToNewValJSON = reqData.getJSONObject("colNameToNewVal");
    Map<String, String>
        colNameToNewVal = GSON.fromJson(String.valueOf(colNameToNewValJSON), Map.class);

    String sqlString = this.buildSqlString(tableName, conditionsMap, colNameToNewVal);
    System.out.println("SQL: " + sqlString);
    DatabaseProxy db = new DatabaseProxy(objectOrganizer.getFileName());
    int updateQueryRes = db.executeWCommands(sqlString);
    if (updateQueryRes == -1) { //means an error was thrown
      return GSON.toJson("ERROR: was not able to execute update command");
    }
    db.closeConn();
    return GSON.toJson("Success");
  }

  /**
   * @param tableName       The name of the table whose row I will update
   * @param conditionsMap   The condition(s) that will determine which row to update. This map maps
   *                        a/multiple column names to a particular value that exists in the table
   * @param colNameToNewVal A map that maps one or multiple column headers to the new value that \
   *                        the row will contain
   * @return a SQL command String
   */
  private String buildSqlString(String tableName, Map<String, String> conditionsMap,
                                Map<String, String> colNameToNewVal) {
    //UPDATE table_name
    //SET column1 = value1, column2 = value2, ...
    //WHERE condition;
    StringBuilder updateQuery = new StringBuilder("UPDATE " + tableName + " SET ");

    List<String> keyList = new ArrayList<>(colNameToNewVal.keySet());
    for (int i = 0; i < keyList.size(); i++) {
      String currCol = keyList.get(i);
      updateQuery.append(currCol).append("=").append("'").append(colNameToNewVal.get(currCol))
          .append("'");
      if (i != keyList.size() - 1) {
        updateQuery.append(", ");
      }
    }
    updateQuery.append(" WHERE ");

    List<String> conditionsList = new ArrayList<>(conditionsMap.keySet());
    for (int i = 0; i < conditionsList.size(); i++) {
      String currCol = conditionsList.get(i);
      updateQuery.append(currCol).append("=").append("'").append(conditionsMap.get(currCol))
          .append("'");
      if (i != conditionsList.size() - 1) {
        updateQuery.append(" AND ");
      }
    }
    updateQuery.append(";");

    return updateQuery.toString();
  }

}
