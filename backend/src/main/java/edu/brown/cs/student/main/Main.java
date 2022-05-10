package edu.brown.cs.student.main;


import WebScraping.WebScraper;
import backendhandlers.DeleteHandler;
import backendhandlers.InsertRowHandler;
import backendhandlers.TableHandler;
import backendhandlers.UpdateHandler;

import replcommands.DeleteRowCommand;
import replcommands.InsertRowCommand;
import replcommands.LoadDatabase;
import replcommands.ObjectOrganizer;
import replcommands.PrintStudentsCommand;
import replcommands.REPL;
import replcommands.UpdateRowCommand;
import freemarker.template.Configuration;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.eclipse.jetty.websocket.api.Session;

import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;
import websockets.SocketHandler;
import websockets.UserInfo;

import spark.Spark;

import java.io.File;
import java.io.IOException;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Main class of our project. This is where execution begins.
 */

public final class Main {
  private static final int DEFAULT_PORT = 4567;
  //  private static final int DEFAULT_PORT = 3000;
  private static ObjectOrganizer objectOrganizer;
  private final String[] args;
  public static int nextUserNumber = 1; // assign to username for next connecting user
  public static Map<Session, UserInfo> users = new ConcurrentHashMap<>();

 private Main(String[] args) {
   this.args = args;
 }

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
    runSparkServer(DEFAULT_PORT);
  }

  private void run() {
//    new WebScraper();

    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);

    objectOrganizer = new ObjectOrganizer();
    REPL repl = new REPL(new BufferedReader(new InputStreamReader(System.in)), objectOrganizer);

    repl.addCommand(new LoadDatabase());
    repl.addCommand(new PrintStudentsCommand());
    repl.addCommand(new InsertRowCommand());
    repl.addCommand(new UpdateRowCommand());
    repl.addCommand(new DeleteRowCommand());

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    try {
      repl.run();
    } catch (Exception e) {
      System.out.println("ERROR: Could not run REPL");
    }
  }

  private static void runSparkServer(int port) {
    // new stuff
    String localHost = "127.0.0.1";
    Spark.ipAddress(localHost);

    Spark.port(port);
    Spark.externalStaticFileLocation("src/main/resources/static");

    FreeMarkerEngine engine = createEngine();
    Spark.webSocket("/home", SocketHandler.class);
    Spark.get("", new HomePage(), engine);

    Spark.init();

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

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    File templates = new File("src/main/resources/public");

    try {
      config.setDirectoryForTemplateLoading(templates);

    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n", templates);
      System.exit(1);
    }

    return new FreeMarkerEngine(config);
  }

  // sends a message from one user to all users, along with a list of current usernames
  public static void broadcastMessage(String sender, String message) {
    List<Session> sessionsToSend = new ArrayList<>();
    List<String> usersList = new ArrayList<>();

    for (UserInfo user : users.values()) {
      sessionsToSend.add(user.getSession());
      usersList.add(user.getUserName());
    }

    sessionsToSend.stream().filter(Session::isOpen).forEach(session -> {
      try {
        session.getRemote().sendString(String.valueOf(new JSONObject()
            .put("userlist", usersList)
        ));

      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public static class HomePage implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
      return new ModelAndView(null, "websocket.ftl");
    }
  }
}
