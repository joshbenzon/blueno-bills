package replcommands;

import client.ClientRequestGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for testing purposes and uses a REPL command to test that a POST request to the
 * /insert endpoint works.
 */
public class InsertRowCommand implements Command {
  /**
   * @return the REPL command name
   */
  @Override
  public String getName() {
    return "insert-row";
  }

  /**
   * @param args            represents the arguments that the command executes upon
   * @param objectOrganizer the ObjectOrganizer object for the repl
   * @throws Exception an exception thrown if an error occurs when running this command
   */
  @Override
  public void run(String[] args, ObjectOrganizer objectOrganizer) throws Exception {
    String tableName = "Students";
    List<String> values = new ArrayList<>();
    values.add("8");
    values.add("Bob@brown.edu");
    values.add("100");
    values.add("30");
    values.add("10");
    //SQL COMMAND: INSERT INTO horoscopes (horoscope_id,horoscope) VALUES (13, "Leo")
    ClientRequestGenerator.makeInsertPostRequest(tableName, values);
  }

}
