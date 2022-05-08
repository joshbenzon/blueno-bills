package websockets;

import org.eclipse.jetty.websocket.api.Session;

public class UserInfo {
  private final Session userSession;
  private final String userName;

  public UserInfo(Session userSession, String userName) {
    this.userSession = userSession;
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public Session getSession() {
    return userSession;
  }
}
