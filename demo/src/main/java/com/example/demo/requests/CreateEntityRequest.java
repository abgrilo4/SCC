package com.example.demo.requests;


public class CreateEntityRequest {

	private static String name;
	private static String description;
	
	public CreateEntityRequest(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		CreateEntityRequest.name = name;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		CreateEntityRequest.description = description;
	}
}