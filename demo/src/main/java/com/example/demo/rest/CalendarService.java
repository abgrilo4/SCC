package com.example.demo.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Calendar;
import com.example.demo.entities.Entity;
import com.example.demo.entities.Reservation;
import com.example.demo.repositories.CalendarRepository;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.utils.ServiceUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class CalendarService{

	private EntityRepository entityRepository;
	private CalendarRepository calendarRepository;
	private ReservationRepository reservationRepository;
	private SimpleDateFormat dateFormat;
	private Environment environment;
	private boolean usesCache;

	@Autowired
	public CalendarService(EntityRepository entityRepository, CalendarRepository calendarRepository, ReservationRepository reservationRepository, Environment environment)
	{
		this.entityRepository = entityRepository;
		this.calendarRepository = calendarRepository;
		this.reservationRepository = reservationRepository;
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
		this.environment = environment;
		this.usesCache = this.environment.getProperty("azure.jedis.use").equals("true");
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

	public Calendar getCalendar(String calendarId) throws ClassNotFoundException, IOException
	{
		boolean miss = true;
		Calendar calendar = null;

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("Calendar", calendarId);
				if(cachedResource != null)
				{
					calendar = ServiceUtils.deserializeCalendar(cachedResource);
					miss = false;
				}
			}
		}

		if(miss || !usesCache)
		{

			try {
				calendar = calendarRepository.findById(calendarId).get();
			}
			catch(Exception e) {
				return null;
			}
		}

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				jedisClient.hset("calendars", calendarId, ServiceUtils.serializeCalendar(calendar));
			}
		}
		return calendar;
	}

	public void deleteCalendar(String entityId)
	{
		calendarRepository.deleteById(entityId);
	}

	public Calendar editCalendar(String calendarId, String description, String name) throws ClassNotFoundException, IOException
	{
		boolean miss = true;
		Calendar calendar = null;

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				String cachedResource = jedisClient.hget("Calendar", calendarId);
				if(cachedResource != null)
				{
					calendar = ServiceUtils.deserializeCalendar(cachedResource);
					calendar.setDescription(description);
					miss = false;

				}
			}
		}

		if(miss || !usesCache)
		{

			try {
				calendar = calendarRepository.findById(calendarId).get();
				calendar.setDescription(description);
				calendarRepository.save(calendar);
			}
			catch(Exception e) {
				return null;
			}
		}

		if(usesCache)
		{
			JedisPoolConfig config = new JedisPoolConfig();
			ServiceUtils.getPool(config);
			JedisPool jedis = new JedisPool(config, this.environment.getProperty("azure.redis.Hostname"), 6379, 1000, this.environment.getProperty("azure.jedis.cacheKey"), 1);
			try(Jedis jedisClient = jedis.getResource()) {
				jedisClient.hset("Calendar", calendarId, ServiceUtils.serializeCalendar(calendar));
				calendarRepository.save(calendar);
			}
		}
		return calendar;
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
