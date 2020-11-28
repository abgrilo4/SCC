package com.example.demo.requests;

public class GetCalendarRequest {

	private static String calendarId;
	private static String description;
	private static String name;
	
	public GetCalendarRequest(String calendarId, String description, String name)
	{
		this.calendarId = calendarId;
		this.description = description;
		this.name = name;
	}

	public static String getCalendarId() {
		return calendarId;
	}

	public static void setCalendarId(String calendarId) {
		GetCalendarRequest.calendarId = calendarId;
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
