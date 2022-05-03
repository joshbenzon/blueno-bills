package edu.brown.cs.student.main.websockets;

import org.eclipse.jetty.websocket.api.Session;

public class UserInfo {
	
	private Session userSession;
	private String userName;
	
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
