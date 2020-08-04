package com.gui9394.hello_web_flux.repository;

import java.util.UUID;

import com.gui9394.hello_web_flux.model.Person;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveSortingRepository<Person, UUID> {
}