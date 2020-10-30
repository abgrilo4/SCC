package com.example.demo.repositories;

import com.example.demo.entities.Forum;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

public interface ForumRepository extends CosmosRepository<Forum, String>{

}
