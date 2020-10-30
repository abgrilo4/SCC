package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

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
	
	public String createEntity(String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		Entity entity = new Entity();
		entity.setName(name);
		entity.setId(String.valueOf(1));
		entity.setDescription(description);
		entity.setPhoto(photo);
		entity.setCalendarIds(calendarIds);
		entity.setListed(listed);

		entities.save(entity);
		return entity.getId();
	}
	
	public void deleteEntity(String entityId)
	{
		entities.deleteById(entityId);
	}
	
	public void updateEntity(String entityId, String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		 Entity current = entities.findById(entityId).get();
		 current.setName(name);
		 current.setDescription(description);
		 current.setPhoto(photo);
		 current.setCalendarIds(calendarIds);
		 current.setListed(listed);
		 
		 entities.save(current);
	}

}
