package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Calendar;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

@Repository
public interface CalendarRepository extends CosmosRepository<Calendar, String> {

}
