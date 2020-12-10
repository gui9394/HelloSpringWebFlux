package com.gui9394.person.service;

import com.gui9394.person.dto.CreatePersonDto;
import com.gui9394.person.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    public Mono<Person> create(CreatePersonDto dto);

    public Flux<Person> findAll();

}