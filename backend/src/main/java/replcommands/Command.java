package replcommands;

/**
 * This interface represents any command that is compatible with the generic REPL.
 */
public interface Command {
  /**
   * This method returns the name of the command (i.e. what is inputted into the REPL)
   *
   * @return a String representing the name of the command
   */
  String getName(); //returns the name of the command

  /**
   * This method executes the command.

   * @param args represents the arguments that the command executes upon
   * @param objectOrganizer the ObjectOrganizer object for the repl
   * @throws Exception that is determined in each command
   */
  void run(String[] args, ObjectOrganizer objectOrganizer) throws Exception; //executes command

}
