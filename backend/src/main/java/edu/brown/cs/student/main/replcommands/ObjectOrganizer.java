package edu.brown.cs.student.main.replcommands;


import edu.brown.cs.student.main.databaseaccessor.SQLTable;

/**
 * This is the ObjectOrganizer! This class represents "dependency injection," which allows all
 * the classes to use the object with getter and setter methods, instead of directly constructing
 * them.
 * // Source: https://github.com/cs0320-s2022/project-1-amroueh-jurrutic-mquezad1/tree/master/src/
 * main/java/edu/brown/cs/student/main
 */
public class ObjectOrganizer {

  // a field representing a database. the map contains table names as a key and the value
  // is a list of maps where each inner map represents a row in the table, mapping header name
  // to header value
  private String fileName;
  private SQLTable table;
  /**
   * This is the ObjectOrganizer constructor!
   */
  public ObjectOrganizer() {
  }

  /**
   * a setter for table field.
   *
   * @param table the new table
   */
  public void setTable(SQLTable table) {
    this.table = table;
  }

  /**
   * a getter for table field.
   *
   * @return the table field
   */
  public SQLTable getTable() {
    return this.table;
  }

  /**
   * setter for the file name.
   * @param fileName a string to set the fileName field to
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * a getter for the fileName field.
   * @return the fileName field
   */
  public String getFileName() {
    return fileName;
  }


}
