package com.gui9394.person.service;

import com.gui9394.person.dto.CreatePersonDto;
import com.gui9394.person.model.Person;
import com.gui9394.util.Page;

import org.springframework.data.domain.Pageable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

  Mono<Person> create(CreatePersonDto dto);

  Flux<Person> findAll(Pageable pageable);

  Mono<Page<Person>> findAll1(Pageable pageable);
  
}