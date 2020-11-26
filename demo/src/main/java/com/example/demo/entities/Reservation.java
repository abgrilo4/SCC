package com.example.demo.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document
public class Reservation {

	@Id
	private String id;
	private String calendarId;
	private Date date;
	private String name;
	
	public Reservation(String id, String calendarId, Date date, String name)
	{
		this.id = id;
		this.calendarId = calendarId;
		this.date = date;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
