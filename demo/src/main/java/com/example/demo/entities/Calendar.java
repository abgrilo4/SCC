package com.example.demo.entities;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document
public class Calendar {

	@Id
	private String id;
	private String name;
	private String description;
	private byte[] photo;
	private Set<Date> dates;
	private Set<String> reservationIds;	

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
	
	public void setAvailablePeriod(Date date)
	{
		dates.add(date);
	}
	
	public String addReservation(Date date)
	{
		dates.remove(date);
		String reservationId = UUID.randomUUID().toString();
		reservationIds.add(reservationId);
		
		return reservationId;
	}
	
	public void cancelReservation(String reservationId)
	{
		reservationIds.remove(reservationId);
	}
}
