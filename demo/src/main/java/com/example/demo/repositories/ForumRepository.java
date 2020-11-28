package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Forum;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

@Repository
public interface ForumRepository extends CosmosRepository<Forum, String>{

}
