package com.example.demo.requests;

public class AddMessageRequest {

	private static String userId;
	private static String textMessage;
	
	public AddMessageRequest( String userId, String textMessage)
	{
		 this.userId = userId;
		 this.textMessage = textMessage;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		AddMessageRequest.userId = userId;
	}

	public static String getTextMessage() {
		return textMessage;
	}

	public static void setTextMessage(String textMessage) {
		AddMessageRequest.textMessage = textMessage;
	}
	
	
}
