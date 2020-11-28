package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Message;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

@Repository
public interface MessageRepository extends CosmosRepository<Message, String> {

}
