package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/media")
public class MediaController {
	
	MediaService mediaService;

	@Autowired
	public MediaController(MediaService mediaService)
	{
		this.mediaService = mediaService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String upload(@RequestBody byte[] bytes)
	{
		return mediaService.upload(bytes);
	}
	
	@GetMapping(path = "/{blobId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] download(@PathVariable String blobId)
	{
		return mediaService.download(blobId);
	}

}
