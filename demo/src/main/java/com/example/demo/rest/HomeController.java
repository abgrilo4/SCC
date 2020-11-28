package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class HomeController {

	HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService)
	{
		this.homeService = homeService;
	}
	
	@GetMapping(path = "/ranking", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public List<String> getRanking(@RequestBody String entityId)
	{
		return homeService.getRanking();
	}
}
