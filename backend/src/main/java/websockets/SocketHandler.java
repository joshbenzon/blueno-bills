package websockets;

import edu.brown.cs.student.main.Main;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class SocketHandler {
  private String sender, msg;

  @OnWebSocketConnect
  public void onConnect(Session user) throws Exception {
    System.out.println(user.getLocalAddress());
    System.out.println(user.getRemoteAddress());
    String urlString = user.getUpgradeRequest().getRequestURI().toString();

    System.out.println(urlString);
    String query = urlString.split("\\?")[1];


    String username = "User" + Main.nextUserNumber++;
    username = urlString.split("username=")[1];

    UserInfo thisUser = new UserInfo(user, username);
    Main.users.put(user, thisUser);
    Main.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
  }

  @OnWebSocketClose
  public void onClose(Session user, int statusCode, String reason) {
    String username = Main.users.get(user).getUserName();
    Main.users.remove(user);
    Main.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
  }

  @OnWebSocketMessage
  public void onMessage(Session user, String message) {
    String username = Main.users.get(user).getUserName();
    Main.broadcastMessage(sender = username, msg = message);
  }
}
