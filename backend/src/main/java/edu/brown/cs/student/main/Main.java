package edu.brown.cs.student.main;

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
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.article;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.b;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;

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
    runSparkServer(DEFAULT_PORT);
  }

  private final String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  public static int nextUserNumber = 1; //Assign to username for next connecting user

  public static Map<Session, UserInfo> users = new ConcurrentHashMap<>();

  private void run() {

    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);

    objectOrganizer = new ObjectOrganizer();
    this.repl = new REPL(new BufferedReader(new InputStreamReader(System.in)), objectOrganizer);
    this.repl.addCommand(new LoadDatabase());
    this.repl.addCommand(new PrintStudentsCommand());
    this.repl.addCommand(new InsertRowCommand());
    this.repl.addCommand(new UpdateRowCommand());
    this.repl.addCommand(new DeleteRowCommand());

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

    // web sockets
//     FreeMarkerEngine engine = createEngine();
//     Spark.webSocket("", SocketHandler.class);
//     Spark.get("", new Main.HomePage(), engine);

    // pure frontend
//    FreeMarkerEngine engine = createEngine();
//    Spark.get("/ws", new Main.HomePage(), engine);
  
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
  
  // web sockets
  //Sends a message from one user to all users, along with a list of current usernames
//   public static void broadcastMessage(String sender, String message) {
//     List<Session> sessionsToSend = new ArrayList<>();
//     List<String> usersList = new ArrayList<>();

//     for (UserInfo user : users.values()) {
//       sessionsToSend.add(user.getSession());
//       usersList.add(user.getUserName());
//     }

//     sessionsToSend.stream().filter(Session::isOpen).forEach(session ->{
//       try {
//         session.getRemote().sendString(String.valueOf(new JSONObject()
//             .put("userlist", usersList)
//         ));
//       } catch (Exception e) {
//         e.printStackTrace();
//       }
//     });

//   }

//   private static FreeMarkerEngine createEngine() {
//     Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//     File templates = new File("src/main/resources/public");
//     try {
//       config.setDirectoryForTemplateLoading(templates);
//     } catch (IOException ioe) {
//       System.out.printf("ERROR: Unable use %s for template loading.%n", templates);
//       System.exit(1);
//     }
//     return new FreeMarkerEngine(config);
//   }

//   public static class HomePage implements TemplateViewRoute {
//     @Override
//     public ModelAndView handle(Request request, Response response) throws Exception {
//       // TODO Auto-generated method stub
//       return new ModelAndView(null, "websocket.ftl");
//     }
//   }

    // pure frontend
//  private static FreeMarkerEngine createEngine() {
//    Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//    File templates = new File("src/main/resources/public");
//    try {
//      config.setDirectoryForTemplateLoading(templates);
//    } catch (IOException ioe) {
//      System.out.printf("ERROR: Unable use %s for template loading.%n", templates);
//      System.exit(1);
//    }
//    return new FreeMarkerEngine(config);
//  }
//
//  //Sends a message from one user to all users, along with a list of current usernames
//  public static void broadcastMessage(String sender, String message, int room) {
//    List<Session> sessionsToSend = new ArrayList<>();
//    List<String> usersList = new ArrayList<>();
//
//
//
//    sessionsToSend.stream().filter(Session::isOpen).forEach(session ->{
//      try {
//        session.getRemote().sendString(String.valueOf(new JSONObject()
//            .put("userMessage", createHtmlMessageFromSender(sender, message))
//            .put("userlist", usersList)
//        ));
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    });
//
//  }
//
//  //Builds a HTML element with a sender-name, a message, and a timestamp,
//  private static String createHtmlMessageFromSender(String sender, String message) {
//    return article(
//        b(sender + " says:"),
//        span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
//        p(message)
//    ).render();
//  }
//
//  public static class HomePage implements TemplateViewRoute {
//    @Override
//    public ModelAndView handle(Request request, Response response) throws Exception {
//      // TODO Auto-generated method stub
//      return new ModelAndView(null, "websocket.ftl");
//    }
//  }
}
