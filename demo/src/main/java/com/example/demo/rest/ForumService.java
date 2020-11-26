package com.example.demo.rest;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Forum;
import com.example.demo.entities.Message;
import com.example.demo.repositories.ForumRepository;
import com.example.demo.repositories.MessageRepository;

public class ForumService {

	private final ForumRepository forums;
	private final MessageRepository messages;
	
	@Autowired
	public ForumService(ForumRepository forums, MessageRepository messages)
	{
		this.forums = forums;
		this.messages = messages;
	}
	
	public String createForum(String entityId, String name, String description)
	{
		String id = UUID.randomUUID().toString();
		Forum forum = new Forum(entityId, name, description, id);
		forums.save(forum);
		return id;
	}
	
	public Forum getForum(String id)
	{
		if(forums.findById(id) != null)
			return forums.findById(id).get();
		else
			return null;
	}
	
	public String addMessage(String userId, String textMessage, String id)
	{
		Forum forum = forums.findById(id).get();
		String messageId = UUID.randomUUID().toString();
		Message message = new Message(userId, textMessage, id, messageId);
		messages.save(message);
		forum.addMessage(messageId);
		return messageId;
	}
	
	public String addReply(String id, String messageId, String reply)
	{
		Forum forum = forums.findById(id).get();
		Message message = messages.findById(messageId).get();
		String replyId = UUID.randomUUID().toString();
		Message.addReply(messageId);
		messages.save(message);
		forum.addReply(replyId);
		forums.save(forum);
		return replyId;
	}
}
