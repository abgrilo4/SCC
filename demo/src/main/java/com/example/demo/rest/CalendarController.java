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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Calendar;
import com.example.demo.requests.AddReservationRequest;
import com.example.demo.requests.AddSlotReservationsRequest;
import com.example.demo.requests.CreateCalendarRequest;
import com.example.demo.requests.GetCalendarRequest;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/calendar")
public class CalendarController {
	
	CalendarService calendarService;

	@Autowired
	public CalendarController(CalendarService calendarService)
	{
		this.calendarService = calendarService;
	}
	
	@PostMapping()
	public String createCalendar(@RequestBody CreateCalendarRequest createCalendarRequest)
	{
		return calendarService.createCalendar(CreateCalendarRequest.getEntityId(), CreateCalendarRequest.getName(), CreateCalendarRequest.getDescription());
	}
	
	@GetMapping(path = "/get/{calendarId}")
	public Calendar getCalendar(@PathVariable String calendarId) throws ClassNotFoundException, IOException
	{
		return calendarService.getCalendar(calendarId);
	}
	
	@PutMapping(path = "/edit/{calendarId}")
	public Calendar getCalendar(@RequestBody GetCalendarRequest getCalendarRequest) throws ClassNotFoundException, IOException
	{
		return calendarService.editCalendar(GetCalendarRequest.getCalendarId(), GetCalendarRequest.getDescription(), GetCalendarRequest.getName());
	}
	
	@DeleteMapping(path = "/delete/{entityId}")
	public void deleteCalendar(@PathVariable String entityId)
	{
		calendarService.deleteCalendar(entityId);
	}

	@PostMapping(path = "/addSlot")
	public Set<Date> addSlotReservations(@RequestBody AddSlotReservationsRequest addSlotReservationsRequest)
	{
		return calendarService.addSlotReservations(AddSlotReservationsRequest.getCalendarId(), AddSlotReservationsRequest.getSlots());
	}
	
	@PostMapping(path = "/addReservation")
	public String addReservation(@RequestBody AddReservationRequest addReservationRequest) throws ParseException
	{
		return calendarService.addReservation(AddReservationRequest.getCalendarId(), AddReservationRequest.getDate(), AddReservationRequest.getName());
	}
}
