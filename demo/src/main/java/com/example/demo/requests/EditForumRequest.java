package com.example.demo.requests;

public class EditForumRequest {

	private static String name;
	private static String description;
	
	public EditForumRequest(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		EditForumRequest.name = name;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		EditForumRequest.description = description;
	}
}
