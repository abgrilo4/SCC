package com.example.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.Base64;

import com.example.demo.entities.Calendar;
import com.example.demo.entities.Entity;
import com.example.demo.entities.Forum;

import redis.clients.jedis.JedisPoolConfig;

public class ServiceUtils {
	
	public static Calendar deserializeCalendar(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Calendar calendar = (Calendar) ois.readObject();
        ois.close();
        return calendar;
    }
	
	public static String serializeCalendar(Calendar calendar) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(calendar);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
	
	public static Entity deserializeEntity(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Entity entity = (Entity) ois.readObject();
        ois.close();
        return entity;
    }
	
	public static String serializeEntity(Entity entity) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(entity);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
	
	public static Forum deserializeForum(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Forum forum = (Forum) ois.readObject();
        ois.close();
        return forum;
    }
	
	public static String serializeForum(Forum forum) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(forum);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
	
	public static void getPool(JedisPoolConfig poolConfig) {
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
    }
}
