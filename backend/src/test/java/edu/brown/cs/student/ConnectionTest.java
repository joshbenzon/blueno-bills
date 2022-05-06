package edu.brown.cs.student;


import databaseaccessor.DatabaseProxy;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ConnectionTest {
  @Test
  public void testOpenConn() throws SQLException {
    String fileName = "../data/StudentData.sqlite3";
    DatabaseProxy databaseProxy = new DatabaseProxy(fileName);
    assertFalse(databaseProxy.getConn().isClosed());
  }

  @Test
  public void testClosedConn() throws SQLException {
    String fileName = "../data/StudentData.sqlite3";
    DatabaseProxy databaseProxy = new DatabaseProxy(fileName);
    databaseProxy.closeConn();
    assertTrue(databaseProxy.getConn().isClosed());
  }

}
