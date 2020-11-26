package com.example.demo.repositories;

import com.example.demo.entities.Reservation;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

public interface ReservationRepository extends CosmosRepository<Reservation, String>{

}
