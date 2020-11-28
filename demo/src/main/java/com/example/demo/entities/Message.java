package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import java.util.Set;
@Document
public class Message implements Serializable{

	@Id
	private static String userId;
	private static String message;
	private static String forumId;
	private static String messageId;
	private static List<String> replies;
	
	public Message(String userId, String message, String forumId, String messageId)
	{
		this.userId = userId;
		this.message = message;
		this.forumId = forumId;
		this.messageId = messageId;
		replies = new LinkedList<>();
	}
	
	public static void addReply(String messageId)
	{
		replies.add(messageId);
	}
	
	public static String getUserId() {
		return userId;
	}
	public static void setUserId(String userId) {
		Message.userId = userId;
	}
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		Message.message = message;
	}
	public static String getForumId() {
		return forumId;
	}
	public static void setForumId(String forumId) {
		Message.forumId = forumId;
	}	
}

	
	
