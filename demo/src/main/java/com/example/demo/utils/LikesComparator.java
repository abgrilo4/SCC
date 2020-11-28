package com.example.demo.utils;
import java.util.Comparator;

import com.example.demo.entities.Entity;


public class LikesComparator implements Comparator<Entity>{

	public int compare(Entity e1, Entity e2) {
		if (e1.getLikes() != e2.getLikes()) {
			return e1.getLikes() - e2.getLikes();
		}
		return e1.getName().compareTo(e2.getName());
	}
}
