package com.example.demo.entities;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document
public class Entity {

	private static final String recommendations = "RECOMMENDATIONS";
	private static final String frontpage = "FRONTPAGE";

	@Id
	private String id;
	private String name;
	private String description;
	private byte[] photo;
	private List<String> calendarIds;
	private int numberOfLikes;
	private Forum forum;
	private String listed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public List<String> getCalendarIds() {
		return calendarIds;
	}

	public void addCalendarId(String calendarId) {
		calendarIds.add(calendarId);
	}
	
	
	
	
	




}
