package com.example.demo.rest;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Entity;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.utils.LikesComparator;

@Service
public class HomeService {

	private List<Entity> ranking = new LinkedList<Entity>();

	public HomeService(EntityRepository entities)
	{
		Iterator<Entity> it = entities.findAll().iterator();
		while(it.hasNext())
		{
			ranking.add(it.next());
		}
	}

	public List<String> getRanking()
	{
		List<String> result = new LinkedList<String>();
        Collections.sort(ranking, new LikesComparator());
        for(Entity e: ranking)
        {
        	result.add(e.getId());
        }
        return result;
	}
}
