package databaseaccessor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is my DatabaseProxy class. It connects to a sqlite3 database and performs queries on tables
 * if they have the right permissions
 */
public class DatabaseProxy {

  private static Connection conn = null;
  private final String filename;

  /**
   * @param filename name of the sqlite3 database
   */
  public DatabaseProxy(String filename) {
    this.filename = filename;
    //opening the initial connection
    this.openConn();
  }


  /**
   * @param sql sql command string
   * @return the result of the passed in sql command
   * <p>
   * This is a method where I execute my sql commands that have R permissions, since those are the
   * commands that return a ResultSet. I first make sure my database table has the right permissions
   * to execute the passed in command.
   */
  public ResultSet executeSQLCommand(String sql) throws SQLException {
    try {
      PreparedStatement roleFinder = conn.prepareStatement(sql);
      return roleFinder.executeQuery();
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    return null;
  }

  /**
   * This is a helper method I use to open a connection to a sqlite3 database. I open the first
   * connection here and also open new connections if I want to make multiple queries to the same
   * DatabaseProxy instance since I close the connection after every query.
   */
  private void openConn() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    String urlToDB = "jdbc:sqlite:" + this.filename;
    try {
      conn = DriverManager.getConnection(urlToDB);
    } catch (SQLException throwable) {
      System.out.println("ERROR: " + throwable.getMessage());
    }
    // these two lines tell the database to enforce foreign keys during operations,
    // and should be present
    Statement stat = null;
    try {
      stat = conn.createStatement();
    } catch (SQLException throwable) {
      System.out.println("ERROR: " + throwable.getMessage());
    }
    try {
      if (stat != null) {
        stat.executeUpdate("PRAGMA foreign_keys=ON;");
      }
    } catch (SQLException throwable) {
      System.out.println("ERROR: " + throwable.getMessage());
    }
  }

  /**
   * This is a method that I use to close the connection. This is useful in the case where I
   * instantiate a DatabaseProxy class for each different Command class because I do not
   * want to have multiple connections to the same Database.
   */
  public void closeConn() {
    try {
      conn.close();
    } catch (SQLException throwables) {
      System.out.println("ERROR: " + throwables.getMessage());
    }
  }

  /**
   * @return the connection
   * This method is for JUnit testing purposes to ensure my connection is closed when I want it to be
   */
  public Connection getConn(){
    return conn;
  }

  /**
   * @param sql sql command string
   * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
   * for SQL statements that return nothing, or -1 if an error occurs.
   * <p>
   * This method executes my commands with W/RW permissions.
   */
  public int executeWCommands(String sql) {
    try {
      PreparedStatement roleFinder = conn.prepareStatement(sql);
      return roleFinder.executeUpdate();
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    return -1;
  }
}
