package com.example.demo.entities;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document
public class Forum {

	@Id
	private String id;
	private String entityId;
	private List<String> textMessages;
	
	public Forum(String entityId, String name, String description, String id)
	{
		textMessages = new LinkedList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}
	
	public void addMessage(String textMessage)
	{
		textMessages.add(textMessage);
	}
	
	public void addReply(String replyId)
	{
		textMessages.add(replyId);
	}
}
