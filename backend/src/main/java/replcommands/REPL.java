package replcommands;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This is the REPL class! This represents the read-evaluate-print loop. The input is read from
 * the user, evaluated, and then printed back.
 */
public final class REPL {
  private final BufferedReader bufferedReader;
  private final HashMap<String, Command> map;
  private final ObjectOrganizer organizer;

  /**
   * This is the REPLReader constructor! This takes in a reader from the Main class.
   *
   * @param reader          the buffered reader to handle the input/output
   * @param objectOrganizer the objectOrganizer that allows multiple classes to access the same
   *                        object
   */
  public REPL(BufferedReader reader, ObjectOrganizer objectOrganizer) {
    bufferedReader = reader;
    map = new HashMap<>();
    this.organizer = objectOrganizer;
  }

  /**
   * This method adds commands to a hashmap. Storing commands this way allows easy mapping
   * of inputs to the REPL and the command to be executed.
   *
   * @param command object that implements Command interface
   */
  public void addCommand(Command command) {
    map.put(command.getName(), command);
  }

  /**
   * The method reads the input from the user and prints an output depending on the input.
   *
   * @throws Error     if invalid input
   * @throws Exception if there's an exception
   */
  public void run() throws Exception {
    // keep track of our commands!
    String line;
    String[] inputs;

    try {
      while ((line = bufferedReader.readLine()) != null) {
        // parse input from terminal by space
        inputs = line.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)", 0);

        for (int i = 0; i < inputs.length; i++) {
          inputs[i] = inputs[i].replaceAll("\"", ""); //handles ""
        }

        String commandName = inputs[0]; //first word inputted is stored commandName
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length); //arguments is the array of
        // the rest of the words
        Command toExecute = map.get(commandName); //gets Command object stored under the commandName
        toExecute.run(args, this.organizer); //runs command
      }
      bufferedReader.close();


    } catch (Exception e) { //catches and prints local exceptions
      System.out.println(e.getLocalizedMessage());
    }
  }
}
