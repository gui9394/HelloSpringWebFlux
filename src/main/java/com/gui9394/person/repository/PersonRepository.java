package com.gui9394.person.repository;

import java.util.UUID;

import com.gui9394.person.model.Person;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, UUID> {

    Mono<Person> findByTaxId(String taxId);

}
