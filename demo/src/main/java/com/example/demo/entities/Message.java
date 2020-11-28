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
	private  String messageId;
	private  String userId;
	private  String message;
	private  String forumId;
	private  List<String> replies;
	
	public Message(String userId, String message, String forumId, String messageId)
	{
		this.userId = userId;
		this.message = message;
		this.forumId = forumId;
		this.messageId = messageId;
		replies = new LinkedList<String>();
	}
	
	public void addReply(String messageId)
	{
		replies.add(messageId);
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getForumId() {
		return forumId;
	}
	public void setForumId(String forumId) {
		this.forumId = forumId;
	}	
}

	
	
