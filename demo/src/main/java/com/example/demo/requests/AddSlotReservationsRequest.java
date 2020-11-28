package com.example.demo.requests;

import java.util.Set;

public class AddSlotReservationsRequest {

	private static String calendarId;
	private static Set<String> slots;
	
	public AddSlotReservationsRequest(String calendarId, Set<String> slots)
	{
		this.calendarId = calendarId;
		this.slots = slots;
	}

	public static String getCalendarId() {
		return calendarId;
	}

	public static void setCalendarId(String calendarId) {
		AddSlotReservationsRequest.calendarId = calendarId;
	}

	public static Set<String> getSlots() {
		return slots;
	}

	public static void setSlots(Set<String> slots) {
		AddSlotReservationsRequest.slots = slots;
	}
}
