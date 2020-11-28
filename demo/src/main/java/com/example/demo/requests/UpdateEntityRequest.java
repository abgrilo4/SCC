package com.example.demo.requests;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public class UpdateEntityRequest {

	public UpdateEntityRequest(String entityId, @RequestBody String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		
	}
}
