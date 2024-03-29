package com.example.demo.rest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Entity;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.utils.ServiceUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class EntityService {

	private final EntityRepository entities;
	private Environment environment;
	private boolean usesCache;
	private int likes;

	@Autowired
	public EntityService(EntityRepository entities, Environment environment)
	{
		this.entities = entities;
		this.environment = environment;
		this.usesCache = this.environment.getProperty("azure.jedis.use").equals("true");
		likes = 0;
	}

	public String createEntity(String name, String description)
	{
		String entityId = UUID.randomUUID().toString();
		Entity entity = new Entity();
		entity.setName(name);
		entity.setId(entityId);
		entity.setDescription(description);

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
				String cachedResource = jedisClient.hget("Entity", id);
				if(cachedResource != null)
				{
					entity = ServiceUtils.deserializeEntity(cachedResource);
					miss = false;
				}
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
				jedisClient.hset("Entity", id, ServiceUtils.serializeEntity(entity));
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
				String cachedResource = jedisClient.hget("Entity", entityId);
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
				jedisClient.hset("Entity", entityId, ServiceUtils.serializeEntity(entity));
				entities.save(entity);
			}
		}
		return entity;
	}

	public Entity like(String entityId) throws IOException, ClassNotFoundException {
		boolean miss = true;
		Entity entity = null;

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("Entity", entityId);
				if(cachedResource != null)
				{
					entity = ServiceUtils.deserializeEntity(cachedResource);
					entity.like();
					miss = false;
				}
			}
		}

		if(miss || !usesCache)
		{

			try {
				entity = entities.findById(entityId).get();
				entity.like();
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
				jedisClient.hset("Entity", entityId, ServiceUtils.serializeEntity(entity));
				entities.save(entity);
			}
		}
		return entity;
	}

	
	public int getLikes(String entityId) throws IOException, ClassNotFoundException
	{
		boolean miss = true;
		Entity entity = null;

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("Entity", entityId);
				if(cachedResource != null)
				{
					entity = ServiceUtils.deserializeEntity(cachedResource);
					miss = false;
				}
			}
		}

		if(miss || !usesCache)
		{

			try {
				entity = entities.findById(entityId).get();
			}
			catch(Exception e) {
				return -1;
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
			}
		}
		return entity.getLikes();
	}

	}

