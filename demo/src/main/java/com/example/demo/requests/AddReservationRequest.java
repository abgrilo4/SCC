package com.example.demo.requests;

public class AddReservationRequest {

	private static String calendarId;
	private static String date;
	private static String name;
	
	public AddReservationRequest(String calendarId, String date, String name)
	{
		this.calendarId = calendarId;
		this.date = date;
		this.name = name;
	}

	public static String getCalendarId() {
		return calendarId;
	}

	public static void setCalendarId(String calendarId) {
		AddReservationRequest.calendarId = calendarId;
	}

	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		AddReservationRequest.date = date;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		AddReservationRequest.name = name;
	}
}
