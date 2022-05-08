package backendhandlers;

import com.google.gson.Gson;
import databaseaccessor.DatabaseProxy;
import replcommands.ObjectOrganizer;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteHandler implements Route {

  private static final Gson GSON = new Gson();
  private final ObjectOrganizer objectOrganizer;

  public DeleteHandler(ObjectOrganizer objectOrganizer) {
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
    JSONObject reqData = new JSONObject(request.body());
    String tableName = reqData.getString("tableName");
    JSONObject updateConditionsJSON = reqData.getJSONObject("updateConditions");
    Map<String, String>
        updateConditionsMap = GSON.fromJson(String.valueOf(updateConditionsJSON), Map.class);
    String sqlString = this.buildSqlString(tableName, updateConditionsMap);
    System.out.println("SQL: " + sqlString);
    DatabaseProxy db = new DatabaseProxy(objectOrganizer.getFileName());
    int deleteQueryRes = db.executeWCommands(sqlString);

    if (deleteQueryRes == -1) { //means an error was thrown
      return GSON.toJson("ERROR: was not able to execute deletion command");
    }
    //closing connection so I don't have multiple connections to the same database
    db.closeConn();
    return GSON.toJson("Success");
  }

  /**
   * @param tableName  The tableName I will be deleting a row from
   * @param conditions The conditions for deleting
   * @return The SQL string that will be used for the deletion
   */
  public String buildSqlString(String tableName, Map<String, String> conditions) {
    //DELETE FROM table_name WHERE condition;
    //example condition: "name" = "Jillian"
    StringBuilder deleteQuery = new StringBuilder("DELETE FROM " + tableName + " WHERE ");

    List<String> colsToUpdate = new ArrayList<>(conditions.keySet());

    for (int i = 0; i < colsToUpdate.size(); i++) {
      String currCol = colsToUpdate.get(i);
      deleteQuery.append(currCol).append("=").append("'").append(conditions.get(currCol))
          .append("'");
      if (i != colsToUpdate.size() - 1) {
        deleteQuery.append(" AND ");
      }
    }
    deleteQuery.append(";");

    return deleteQuery.toString();
  }
}
