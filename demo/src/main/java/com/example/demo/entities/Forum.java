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
	private LinkedList<String> textMessages;
	

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
	
	public void addReply(String textMessage, String reply)
	{
		int current = textMessages.indexOf(textMessage);
		if(textMessages.get(current).length() == 1)
			textMessages.add(current, reply);
	}
}
