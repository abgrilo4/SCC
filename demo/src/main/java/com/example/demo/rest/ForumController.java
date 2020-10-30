package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/forum")
public class ForumController {
	
	ForumService forumService;

	@Autowired
	public ForumController(ForumService forumService)
	{
		this.forumService = forumService;
	}
	
	@PutMapping(path = "/{entityId}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void addMessage(@PathVariable String entityId, @RequestBody String textMessage)
	{
		forumService.addMessage(entityId, textMessage);
	}
	
	
	@PutMapping(path = "/{entityId}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void addReply(@PathVariable String entityId, @RequestBody String textMessage, String reply)
	{
		forumService.addReply(entityId, textMessage, reply);
	}

}
