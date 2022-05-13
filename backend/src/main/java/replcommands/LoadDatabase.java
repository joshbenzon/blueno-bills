package replcommands;


import backendhandlers.TableHandler;

import java.sql.SQLException;

/**
 * a repl command which takes a sqlite3 filename as input and loads the file into the backend.
 */
public class LoadDatabase implements Command {

  /**
   * @return the REPL command name
   */
  @Override
  public String getName() {
    return "load-database";
  }

  /**
   * @param args            represents the arguments that the command executes upon
   * @param objectOrganizer the ObjectOrganizer object for the repl
   */
  @Override
  public void run(String[] args, ObjectOrganizer objectOrganizer) {
    try {
      objectOrganizer.setTable(TableHandler.loadDatabase(args[0]));
      System.out.println("Success! " + args[0] + " loaded to the backend.");
      objectOrganizer.setFileName(args[0]);
    } catch (SQLException e) {
      System.out.println("ERROR: Could not load database");
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR: Invalid file name");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("ERROR: File name required");
    }
  }
}
