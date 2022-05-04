package edu.brown.cs.student;

import edu.brown.cs.student.main.databaseaccessor.SQLTable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SQLTableTest {
  @Test
  public void testGetters() {
    String tableName = "example";
    List<String> headers = new ArrayList<>();
    headers.add("first");
    headers.add("second");
    List<Map<String, String>> rows = new ArrayList<>();
    Map<String, String> firstRow = new HashMap<>();
    firstRow.put("first", "firstVal");
    firstRow.put("second", "secondVal");
    rows.add(firstRow);
    SQLTable sqlTable = new SQLTable(tableName, headers, rows);
    assertEquals(sqlTable.getheaders(), headers);
    assertEquals(sqlTable.getNumRows(), rows.size());
  }
}
