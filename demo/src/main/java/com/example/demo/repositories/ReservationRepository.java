package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Reservation;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

@Repository
public interface ReservationRepository extends CosmosRepository<Reservation, String>{

}
