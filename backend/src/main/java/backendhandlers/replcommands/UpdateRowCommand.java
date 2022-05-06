package backendhandlers.replcommands;


import client.ClientRequestGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is for testing purposes to see if a POST request to my /update endpoint works.
 */
public class UpdateRowCommand implements Command {
  /**
   * @return the REPL command name
   */
  @Override
  public String getName() {
    return "update-row";
  }

  /**
   * @param args            represents the arguments that the command executes upon
   * @param objectOrganizer the ObjectOrganizer object for the repl
   * @throws Exception an exception thrown if an error occurs when running this command
   */
  @Override
  public void run(String[] args, ObjectOrganizer objectOrganizer) throws Exception {
    String tableName = "Students";
    Map<String, String> conditions = new HashMap<>();
    Map<String, String> oldColToNewVals = new HashMap<>();
    oldColToNewVals.put("mealSwipes", "99");
    conditions.put("StudentID", "8");
    ClientRequestGenerator.makeUpdatePostRequest(tableName, oldColToNewVals, conditions);
  }
}
