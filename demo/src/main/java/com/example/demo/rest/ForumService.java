package com.example.demo.rest;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Forum;
import com.example.demo.repositories.ForumRepository;

public class ForumService {

	private final ForumRepository forums;
	
	@Autowired
	public ForumService(ForumRepository forums)
	{
		this.forums = forums;
	}
	
	public void addMessage(String entityId, String textMessage)
	{
		Forum forum = forums.findById(entityId).get();
		forum.addMessage(textMessage);
	}
	
	public void addReply(String entityId, String textMessage, String reply)
	{
		Forum forum = forums.findById(entityId).get();
		forum.addReply(textMessage, reply);
	}
}
