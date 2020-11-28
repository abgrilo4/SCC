package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService)
	{
		this.homeService = homeService;
	}
	
	@GetMapping(path = "/ranking")
	public List<String> getRanking()
	{
		return homeService.getRanking();
	}
}
