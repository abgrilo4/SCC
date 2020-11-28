package com.example.demo.rest;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.example.demo.entities.Entity;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.utils.LikesComparator;

public class HomeService {

	private List<Entity> ranking;

	public HomeService(EntityRepository entities)
	{
		Iterator<Entity> it = entities.findAll().iterator();
		while(it.hasNext())
		{
			ranking.add(it.next());
		}
	}

	@SuppressWarnings("null")
	public List<String> getRanking()
	{
		List<String> result = null;
        Collections.sort(ranking, new LikesComparator());
        for(Entity e: ranking)
        {
        	result.add(e.getId());
        }
        return result;
	}


}
