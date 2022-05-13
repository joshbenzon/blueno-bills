package databaseaccessor;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;
//I referenced this repo: https://github.com/cs0320-s2022/project-2-bpiekarz-jurrutic-sgundotr/blob/backend/backend/src/main/java/edu/brown/cs/student/main/databaseaccessor/SQLTable.java
/**
 * This class is a representation of a table in a SQL database
 */
public class SQLTable {
  private final String name;
      //I don't use this variable here because I only have one table but am keeping it to accommodate the potential for multiple tables
  private final List<String> headers;
  private final List<Map<String, String>> rows;


  public SQLTable(String name, List<String> headers, List<Map<String, String>> rows) {
    this.name = name;
    this.headers = headers;
    this.rows = rows;
  }

  /**
   * @return A List representation of the table's headers
   * This method is used for JUnit testing
   */
  public List<String> getHeaders() {
    return ImmutableList.copyOf(this.headers);
  }

  /**
   * @return the number of rows in the table
   * This method is used for JUnit testing
   */
  public int getNumRows() {
    return this.rows.size();
  }


}