package databaseaccessor;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class SQLTable {
  private String name;
  private List<String> headers;
  private List<Map<String, String>> rows;
  private static final Gson GSON = new Gson();

  public SQLTable(String name, List<String> headers, List<Map<String, String>> rows) {
    this.name = name;
    this.headers = headers;
    this.rows = rows;
  }

  public List<String> getheaders() {
    return ImmutableList.copyOf(this.headers);
  }

  public int getNumRows() {
    return this.rows.size();
  }



}