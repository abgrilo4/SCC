package com.example.demo.rest;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Entity;
import com.example.demo.requests.CreateEntityRequest;
import com.example.demo.requests.UpdateEntityRequest;


@RestController
@RequestMapping("/entity")
public class EntityController {
	
	EntityService entityService;

	@Autowired
	public EntityController(EntityService entityService)
	{
		this.entityService = entityService;
	}
	
	@PostMapping()
	public String createEntity(@RequestBody CreateEntityRequest createEntityRequest)
	{
		return entityService.createEntity(CreateEntityRequest.getName(), CreateEntityRequest.getDescription());
	}
	
	@GetMapping(path = "/get/{entityId}")
	public Entity getCalendar(@PathVariable String entityId) throws ClassNotFoundException, IOException
	{
		return entityService.getEntity(entityId);
	}
	
	@DeleteMapping(path = "/delete/{entityId}")
	public void deleteEntity(@PathVariable String entityId)
	{
		entityService.deleteEntity(entityId);
	}
	
	@PutMapping(path = "/update/{entityId}")
	public void updateEntity(@PathVariable String entityId, @RequestBody UpdateEntityRequest updateEntityRequest) throws ClassNotFoundException, IOException
	{
		entityService.updateEntity(entityId, UpdateEntityRequest.getName(), UpdateEntityRequest.getDescription(), UpdateEntityRequest.getPhoto(), UpdateEntityRequest.getCalendarIds(), UpdateEntityRequest.getListed());
	}
	
	@PutMapping(path = "/like/{entityId}")
	public void like(@PathVariable String entityId) throws ClassNotFoundException, IOException
	{
		entityService.like(entityId);
	}
	
	@GetMapping(path = "/getLikes/{entityId}")
	public Entity getLikes(@PathVariable String entityId) throws ClassNotFoundException, IOException
	{
		return entityService.getLikes(entityId);
	}
}
