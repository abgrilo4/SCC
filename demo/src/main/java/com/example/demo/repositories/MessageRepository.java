package com.example.demo.repositories;

import com.example.demo.entities.Message;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

public interface MessageRepository extends CosmosRepository<Message, String> {

}
