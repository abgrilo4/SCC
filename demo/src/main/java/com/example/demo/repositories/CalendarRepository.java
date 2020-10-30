package com.example.demo.repositories;

import com.example.demo.entities.Calendar;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

public interface CalendarRepository extends CosmosRepository<Calendar, String> {

}
