package edu.brown.cs.student;

import edu.brown.cs.student.main.databaseaccessor.DatabaseProxy;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SQLCommandsTest {

  @Test
  public void testSelectCommand() throws SQLException {
    String fileName = "../data/StudentData.sqlite3";
    DatabaseProxy databaseProxy = new DatabaseProxy(fileName);
    ResultSet students = databaseProxy.executeSQLCommand("SELECT * FROM Students");
    int rows = 0;
    while(students.next()){
      rows++;
    }
    assertEquals(rows,7);
  }

}
