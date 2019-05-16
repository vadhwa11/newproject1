package com.test.utils;

public class SessionManagement {
	
	private String SessionId;
	private String authToken;
	public String getAuthToken() {
		return authToken;
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	

}
