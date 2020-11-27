package com.example.demo.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Entity;
import com.example.demo.entities.Forum;
import com.example.demo.requests.AddMessageRequest;
import com.example.demo.requests.AddReplyRequest;

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
	
	@PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String createForum(@RequestBody String entityId, String name, String description)
	{
		return forumService.createForum(entityId, name, description);
	}
	
	@GetMapping(path = "/get/{entityId}")
	public Forum getForum(@RequestBody String entityId) throws ClassNotFoundException, IOException
	{
		return forumService.getForum(entityId);
	}
	
	@PutMapping(path = "/{entityId}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void addMessage(@PathVariable String id, @RequestBody AddMessageRequest messageRequest)
	{
		forumService.addMessage(messageRequest.getUserId(), messageRequest.getTextMessage(),id);
	}
	
	@PutMapping(path = "/{entityId}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void addReply(@PathVariable String entityId, @RequestBody AddReplyRequest replyRequest)
	{
		forumService.addReply(entityId, AddReplyRequest.getTextMessage(), AddReplyRequest.getReply());
	}
}
