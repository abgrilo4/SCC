package com.example.demo.rest;


import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.example.demo.entities.Calendar;
import com.example.demo.entities.Forum;
import com.example.demo.entities.Message;
import com.example.demo.repositories.ForumRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.utils.ServiceUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ForumService {

	private final ForumRepository forums;
	private final MessageRepository messages;
	private Environment environment;
	private boolean usesCache;
	
	@Autowired
	public ForumService(ForumRepository forums, MessageRepository messages, Environment environment)
	{
		this.forums = forums;
		this.messages = messages;
		this.environment = environment;
		this.usesCache = this.environment.getProperty("azure.jedis.use").equals("true");
	}
	
	public String createForum(String entityId, String name, String description)
	{
		String id = UUID.randomUUID().toString();
		Forum forum = new Forum(entityId, name, description, id);
		forums.save(forum);
		return id;
	}
	
	public Forum getForum(String id) throws ClassNotFoundException, IOException
	{
		boolean miss = true;
		Forum forum = null;
		
		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("forum", id);
				if(cachedResource != null)
				{
					forum = ServiceUtils.deserializeForum(cachedResource);
					miss = false;
				}
				jedisClient.close();
			}
		}
		
		if(miss || !usesCache)
		{
			
			try {
				forum = forums.findById(id).get();
			}
			catch(Exception e) {
				return null;
			}
		}
		
		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				jedisClient.hset("forums", id, ServiceUtils.serializeForum(forum));
				jedisClient.close();
			}
		}
		return forum;
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
