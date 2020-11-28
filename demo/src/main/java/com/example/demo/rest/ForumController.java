package com.example.demo.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Forum;
import com.example.demo.requests.AddMessageRequest;
import com.example.demo.requests.AddReplyRequest;
import com.example.demo.requests.CreateForumRequest;
import com.example.demo.requests.EditForumRequest;



@RestController
@RequestMapping("/forum")
public class ForumController {
	
	ForumService forumService;

	@Autowired
	public ForumController(ForumService forumService)
	{
		this.forumService = forumService;
	}
	
	@PostMapping()
	public String createForum(@RequestBody CreateForumRequest createForumRequest)
	{
		return forumService.createForum(CreateForumRequest.getEntityId(), CreateForumRequest.getName(), CreateForumRequest.getDescription());
	}
	
	@PutMapping(path= "/edit/{id}")
	public Forum editForum(@PathVariable String id, @RequestBody EditForumRequest editForumRequest) throws ClassNotFoundException, IOException
	{
		return forumService.editForum(id, EditForumRequest.getName(), EditForumRequest.getDescription());
	}
	
	@GetMapping(path = "/get/{id}")
	public Forum getForum(@PathVariable String id) throws ClassNotFoundException, IOException
	{
		return forumService.getForum(id);
	}
	
	@PutMapping(path = "/addMessage/{id}")
	public void addMessage(@PathVariable String id, @RequestBody AddMessageRequest messageRequest)
	{
		forumService.addMessage(messageRequest.getUserId(), messageRequest.getTextMessage(),id);
	}
	
	@PutMapping(path = "/addReply/{entityId}")
	public void addReply(@PathVariable String entityId, @RequestBody AddReplyRequest replyRequest)
	{
		forumService.addReply(entityId, AddReplyRequest.getTextMessage(), AddReplyRequest.getReply());
	}
}
