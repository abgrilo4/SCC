package com.example.demo.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document
public class Forum implements Serializable{

	@Id
	private String id;
	private String entityId;
	private List<String> textMessages;
	private String description;
	private String name;
	
	public Forum(String entityId, String name, String description, String id)
	{
		textMessages = new LinkedList<>();
		this.description = description;
		this.name = name;
		this.entityId = entityId;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
