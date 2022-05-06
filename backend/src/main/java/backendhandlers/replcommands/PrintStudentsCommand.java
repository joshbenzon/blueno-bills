package backendhandlers.replcommands;


import databaseaccessor.DatabaseProxy;

import java.sql.ResultSet;

/**
 * This class is for testing purposes and prints the horoscopes table in order for me to check
 * that my REPL commands that test my POST request endpoints are modifying the database table
 * properly.
 */
public class PrintStudentsCommand implements Command {
  /**
   * @return the REPL command name
   */
  @Override
  public String getName() {
    return "print-students";
  }

  /**
   * @param args            represents the arguments that the command executes upon
   * @param objectOrganizer the ObjectOrganizer object for the repl
   * @throws Exception an exception thrown if an error occurs when running this command
   */
  @Override
  public void run(String[] args, ObjectOrganizer objectOrganizer) throws Exception {
    String fileName = "../data/StudentData.sqlite3";
    DatabaseProxy databaseProxy = new DatabaseProxy(fileName);

    ResultSet students = databaseProxy.executeSQLCommand("SELECT * FROM Students");
    while (students.next()) {
      System.out.println("id: " + students.getString(1));
      System.out.println("email: " + students.getString(2));
      System.out.println("meal swipes: " + students.getString(3));
      System.out.println("flex points: " + students.getString(4));
      System.out.println("bear bucks: " + students.getString(5));
    }
  }
}
