package com.example.demo.requests;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public class UpdateEntityRequest {

	private static String name;
	private static String description;
	private static byte[] photo;
	private static List<String> calendarIds;
	private static String listed;
	
	public UpdateEntityRequest(String entityId, @RequestBody String name, String description, byte[] photo, List<String> calendarIds, String listed)
	{
		this.name = name;
		this.description = description;
		this.photo = photo;
		this.calendarIds = calendarIds;
		this.listed = listed;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		UpdateEntityRequest.name = name;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		UpdateEntityRequest.description = description;
	}

	public static byte[] getPhoto() {
		return photo;
	}

	public static void setPhoto(byte[] photo) {
		UpdateEntityRequest.photo = photo;
	}

	public static List<String> getCalendarIds() {
		return calendarIds;
	}

	public static void setCalendarIds(List<String> calendarIds) {
		UpdateEntityRequest.calendarIds = calendarIds;
	}

	public static String getListed() {
		return listed;
	}

	public static void setListed(String listed) {
		UpdateEntityRequest.listed = listed;
	}
}
