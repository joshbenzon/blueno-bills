package replcommands;


import client.ClientRequestGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is for testing purposes and uses a REPL command to test that a POST request to the
 * /delete endpoint works.
 */
public class DeleteRowCommand implements Command {
  /**
   * @return the name of this REPL command
   */
  @Override
  public String getName() {
    return "delete-row";
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
    //SQL Command: DELETE FROM horoscopes WHERE horoscope_id = '13'
    conditions.put("StudentID", "8");
    ClientRequestGenerator.makeDeletePostRequest(tableName, conditions);
  }
}
