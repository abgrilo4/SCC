package com.example.demo.requests;

public class CreateForumRequest {

	private static String entityId;
	private static String name;
	private static String description;
	
	public CreateForumRequest(String entityId, String name, String description)
	{
		this.entityId = entityId;
		this.name = name;
		this.description = description;
	}

	public static String getEntityId() {
		return entityId;
	}

	public static void setEntityId(String entityId) {
		CreateForumRequest.entityId = entityId;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		CreateForumRequest.name = name;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		CreateForumRequest.description = description;
	}
}
