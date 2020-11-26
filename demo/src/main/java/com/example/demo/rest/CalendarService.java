package com.example.demo.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Calendar;
import com.example.demo.entities.Entity;
import com.example.demo.entities.Reservation;
import com.example.demo.repositories.CalendarRepository;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.repositories.ReservationRepository;

public class CalendarService {
	
	private EntityRepository entityRepository;
	private CalendarRepository calendarRepository;
	private ReservationRepository reservationRepository;
	private SimpleDateFormat dateFormat;
	
	@Autowired
	public CalendarService(EntityRepository entityRepository, CalendarRepository calendarRepository, ReservationRepository reservationRepository)
	{
		this.entityRepository = entityRepository;
		this.calendarRepository = calendarRepository;
		this.reservationRepository = reservationRepository;
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
	}
	
	public String createCalendar(String entityId, String name, String description)
	{
		Calendar calendar = new Calendar(entityId, UUID.randomUUID().toString(), name, description);
		calendarRepository.save(calendar);
		
		 Entity entity = entityRepository.findById(entityId).get();
		 entity.addCalendarId(calendar.getId());
		 entityRepository.save(entity);
		 
		 return calendar.getId();
	}
	
	public Calendar getCalendar(String calendarId)
	{
		try {
			return calendarRepository.findById(calendarId).get();
		}
		catch(Exception e) {
			//do nothing
		}
		return null;
	}

	public Set<Date> addSlotReservations(String calendarId, Set<String> slots)
	{
		try {
			Calendar calendar = calendarRepository.findById(calendarId).get();
			
			for(String date : slots)
			{
				Date parseDate = dateFormat.parse(date);
				calendar.setAvailablePeriod(parseDate);
			}
			
			calendarRepository.save(calendar);
			return calendar.getDates();
			
		}
		catch(Exception e) {
			//do nothing
		}
		return null;
	}
	
	public String addReservation(String calendarId, String date, String name) throws ParseException
	{
		Date parseDate = dateFormat.parse(date);
		try {
			Calendar calendar = calendarRepository.findById(calendarId).get();
			
			if(calendar.getDates().contains(parseDate))
			{
				Reservation reservation = new Reservation(UUID.randomUUID().toString(), calendarId, parseDate, name);
				reservationRepository.save(reservation);
				calendar.addReservation(parseDate, reservation.getId());
				calendarRepository.save(calendar);
			}
		}
		catch(Exception e) {
			//do nothing
		}
		return null;
		
		
		
	}
}
