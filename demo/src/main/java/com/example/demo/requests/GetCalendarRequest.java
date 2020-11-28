package com.example.demo.requests;

public class GetCalendarRequest {

	private static String description;
	private static String name;
	
	public GetCalendarRequest(String description, String name)
	{
		this.description = description;
		this.name = name;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		GetCalendarRequest.description = description;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		GetCalendarRequest.name = name;
	}
}
