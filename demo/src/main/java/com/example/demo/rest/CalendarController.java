package com.example.demo.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Calendar;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/entity")
public class CalendarController {
	
	CalendarService calendarService;

	@Autowired
	public CalendarController(CalendarService calendarService)
	{
		this.calendarService = calendarService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String createCalendar(@RequestBody String entityId, String name, String description)
	{
		return calendarService.createCalendar(entityId, name, description);
	}
	
	@GetMapping(path = "/get/{calendarId}")
	public Calendar getCalendar(@RequestBody String calendarId) throws ClassNotFoundException, IOException
	{
		return calendarService.getCalendar(calendarId);
	}
	
	@DeleteMapping(path = "/delete/{entityId}")
	public void deleteCalendar(@PathVariable String entityId)
	{
		calendarService.deleteCalendar(entityId);
	}

	@PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Set<Date> addSlotReservations(@PathVariable String calendarId, Set<String> slots)
	{
		return calendarService.addSlotReservations(calendarId, slots);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public String addReservation(@PathVariable String calendarId, String date, String name) throws ParseException
	{
		return calendarService.addReservation(calendarId, date, name);
	}
}
