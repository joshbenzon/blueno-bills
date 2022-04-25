package edu.brown.cs.student.main.replcommands;


import edu.brown.cs.student.main.backendhandlers.TableHandler;
import edu.brown.cs.student.main.databaseaccessor.SQLTable;

import java.sql.SQLException;

/**
 * a repl command which takes a sqlite3 filename as input and loads the file into the backend.
 */
public class LoadDatabase implements Command {

  @Override
  public String getName() {
    return "load-database";
  }

  @Override
  public void run(String[] args, ObjectOrganizer objectOrganizer) {
    try {
      String tableLoaded = TableHandler.loadDatabase(args[0]);
      objectOrganizer.setTable(tableLoaded);
      System.out.println("Success! " + args[0] + " loaded to the backend.");
      System.out.println("SQL Table: " + tableLoaded);
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
