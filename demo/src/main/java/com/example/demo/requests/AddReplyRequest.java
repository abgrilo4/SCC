package com.example.demo.requests;

public class AddReplyRequest {

	private static String textMessage;
	private static String reply;
	
	public AddReplyRequest(String textMessage, String reply)
	{
		this.textMessage = textMessage;
		this.reply = reply;
	}

	public static String getTextMessage() {
		return textMessage;
	}

	public static void setTextMessage(String textMessage) {
		AddReplyRequest.textMessage = textMessage;
	}

	public static String getReply() {
		return reply;
	}

	public static void setReply(String reply) {
		AddReplyRequest.reply = reply;
	}

	
	
}
