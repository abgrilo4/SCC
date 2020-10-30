package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Entity;
import com.example.demo.repositories.EntityRepository;

public class EntityService {

	private final EntityRepository entities;
	
	@Autowired
	public EntityService(EntityRepository entities)
	{
		this.entities = entities;
	}
	
	public String createEntity(String name)
	{
		Entity entity = new Entity();
		entity.setName(name);
		entity.setId(String.valueOf(1));
		entities.save(entity);
		return entity.getId();
	}

}
