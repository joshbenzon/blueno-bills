package edu.brown.cs.student.main;

import edu.brown.cs.student.main.backendhandlers.DeleteHandler;
import edu.brown.cs.student.main.backendhandlers.InsertRowHandler;
import edu.brown.cs.student.main.backendhandlers.TableHandler;
import edu.brown.cs.student.main.backendhandlers.UpdateHandler;
import edu.brown.cs.student.main.replcommands.LoadDatabase;
import edu.brown.cs.student.main.replcommands.ObjectOrganizer;
import edu.brown.cs.student.main.replcommands.REPL;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The Main class of our project. This is where execution begins.
 */

public final class Main {

  private static final int DEFAULT_PORT = 4567;
  private static ObjectOrganizer objectOrganizer;
  private REPL repl;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private final String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {

    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);

    objectOrganizer = new ObjectOrganizer();
    this.repl = new REPL(new BufferedReader(new InputStreamReader(System.in)), objectOrganizer);
    this.repl.addCommand(new LoadDatabase());

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    try {
      this.repl.run();
    } catch (Exception e) {
      System.out.println("ERROR: Could not run REPL");
    }

  }

  private static void runSparkServer(int port) {
    Spark.port(port);
    Spark.externalStaticFileLocation("src/main/resources/static");

    Spark.options("/*", (request, response) -> {
      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
      }

      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");

      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
      }

      return "OK";
    });

    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

    // Put Routes Here
    Spark.get("/table", new TableHandler(objectOrganizer));
    Spark.post("/update", new UpdateHandler(objectOrganizer));
    Spark.post("/insert", new InsertRowHandler(objectOrganizer));
    Spark.post("/delete", new DeleteHandler(objectOrganizer));

    Spark.init();
  }
}
