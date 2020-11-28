package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;

@Document
public class Calendar implements Serializable{

	@Id
	private String id;
	private String entityId;
	private String name;
	private String description;
	private byte[] photo;
	private Set<Date> dates;
	private Set<String> reservationIds;	
	
	public Calendar(String entityId, String id, String name, String description)
	{
		this.id = id;
		this.entityId = entityId;
		this.name = name;
		this.description = description;
		dates = new HashSet<>();
		reservationIds = new HashSet<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String id) {
		this.entityId = id;
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
	
	public Set<Date> getDates() {
		return dates;
	}

	public String addReservation(Date date, String reservationId)
	{
		dates.remove(date);
		reservationIds.add(reservationId);
		
		return reservationId;
	}
	
	public void cancelReservation(String reservationId)
	{
		reservationIds.remove(reservationId);
	}
}
