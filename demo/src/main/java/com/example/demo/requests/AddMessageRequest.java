package com.example.demo.requests;

public class AddMessageRequest {

	private static String userId;
	private String textMessage;
	
	public AddMessageRequest( String userId, String textMessage)
	{
		 this.userId = userId;
		 this.textMessage = textMessage;
	}

	public String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		AddMessageRequest.userId = userId;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	
}
