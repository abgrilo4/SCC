package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Entity;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

@Repository
public interface EntityRepository extends CosmosRepository<Entity, String>{
	


}
