package com.example.demo.requests;

public class CreateCalendarRequest {

	private static String entityId;
	private static String name;	
	private static String description;	

	
	public CreateCalendarRequest( String entityId, String name, String description)
	{
		 this.entityId = entityId;
		 this.name = name;
		 this.description = description;
	}


	public static String getEntityId() {
		return entityId;
	}


	public static void setEntityId(String entityId) {
		CreateCalendarRequest.entityId = entityId;
	}


	public static String getName() {
		return name;
	}


	public static void setName(String name) {
		CreateCalendarRequest.name = name;
	}


	public static String getDescription() {
		return description;
	}


	public static void setDescription(String description) {
		CreateCalendarRequest.description = description;
	}

}
