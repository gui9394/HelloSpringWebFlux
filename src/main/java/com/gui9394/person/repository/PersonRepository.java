package com.gui9394.person.repository;

import java.util.UUID;

import com.gui9394.person.model.Person;
import com.gui9394.util.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveSortingRepository<Person, UUID> {

  Mono<Person> findByTaxId(String taxId);

  Flux<Person> findAllByIdNotNull(Pageable pageable);

  default Mono<Page<Person>> findAll(Pageable pageable) {
    return Page.of(count(), findAllByIdNotNull(pageable), pageable);
  }

}
