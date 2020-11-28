package com.example.demo.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.example.demo.entities.Calendar;
import com.example.demo.entities.Entity;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.utils.ServiceUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class EntityService {

	private final EntityRepository entities;
	private Environment environment;
	private boolean usesCache;

	@Autowired
	public EntityService(EntityRepository entities, Environment environment)
	{
		this.entities = entities;
		this.environment = environment;
		this.usesCache = this.environment.getProperty("azure.jedis.use").equals("true");
	}

	public String createEntity(String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		String entityId = UUID.randomUUID().toString();
		Entity entity = new Entity();
		entity.setName(name);
		entity.setId(entityId);
		entity.setDescription(description);
		entity.setPhoto(photo);
		entity.setCalendarIds(calendarIds);
		entity.setListed(listed);

		entities.save(entity);
		return entity.getId();
	}


	public Entity getEntity(String id) throws ClassNotFoundException, IOException
	{
		boolean miss = true;
		Entity entity = null;

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("entities", id);
				if(cachedResource != null)
				{
					entity = ServiceUtils.deserializeEntity(cachedResource);
					miss = false;
				}
				jedisClient.close();
			}
		}

		if(miss || !usesCache)
		{

			try {
				entity = entities.findById(id).get();
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
				jedisClient.hset("entities", id, ServiceUtils.serializeEntity(entity));
				jedisClient.close();
			}
		}
		return entity;
	}

	public void deleteEntity(String entityId)
	{
		entities.deleteById(entityId);
	}

	public Entity updateEntity(String entityId, String name, String description, byte[] photo, List<String> calendarIds, String listed)  throws ClassNotFoundException, IOException
	{
		boolean miss = true;
		Entity entity = null;

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("entities", entityId);
				if(cachedResource != null)
				{
					entity = ServiceUtils.deserializeEntity(cachedResource);
					entity.setName(name);
					entity.setDescription(description);
					entity.setName(name);
					entity.setCalendarIds(calendarIds);
					entity.setListed(listed);
					entity.setPhoto(photo);
					miss = false;
				}
				jedisClient.close();
			}
		}

		if(miss || !usesCache)
		{

			try {
				entity = entities.findById(entityId).get();
				entity.setName(name);
				entity.setDescription(description);
				entity.setName(name);
				entity.setCalendarIds(calendarIds);
				entity.setListed(listed);
				entity.setPhoto(photo);
				entities.save(entity);
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
				jedisClient.hset("entities", entityId, ServiceUtils.serializeEntity(entity));
				entities.save(entity);
				jedisClient.close();
			}
		}
		return entity;
	}

}
