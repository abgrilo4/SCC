package com.example.demo.rest;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Calendar;
import com.example.demo.entities.Entity;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/entity")
public class EntityController {
	
	EntityService entityService;

	@Autowired
	public EntityController(EntityService entityService)
	{
		this.entityService = entityService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String createEntity(@RequestBody String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		return entityService.createEntity(name, description, photo, calendarIds, listed);
	}
	
	@GetMapping(path = "/get/{entityId}")
	public Entity getCalendar(@RequestBody String entityId) throws ClassNotFoundException, IOException
	{
		return entityService.getEntity(entityId);
	}
	
	@DeleteMapping(path = "/delete/{entityId}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void deleteEntity(@PathVariable String entityId)
	{
		entityService.deleteEntity(entityId);
	}
	
	@PutMapping(path = "/update/{entityId}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void updateEntity(@PathVariable String entityId, @RequestBody String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		entityService.updateEntity(entityId, name, description, photo, calendarIds, listed);
	}

}
