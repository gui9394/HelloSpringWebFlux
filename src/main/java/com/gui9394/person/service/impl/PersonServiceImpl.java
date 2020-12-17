package com.gui9394.person.service.impl;

import com.gui9394.address.service.AddressService;
import com.gui9394.person.dto.CreatePersonDto;
import com.gui9394.person.model.Person;
import com.gui9394.person.repository.PersonRepository;
import com.gui9394.person.service.PersonService;
import com.gui9394.util.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

  private final AddressService addressService;

  private final PersonRepository personRepository;

  public Mono<Person> create(CreatePersonDto dto) {
    final var createPerson = addressService.getAddress(dto.getAddress()) //
        .map(a -> Person.create(dto.getFirstName(), dto.getLastName(), dto.getTaxId(), dto.getDateOfBirth(), a)) //
        .flatMap(personRepository::save) //
        .doOnNext(person -> log.debug("Nova pessoa cadastrada com sucesso {}", person));

    final var conflict = Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Pessoa ja cadastrada"))
        .doOnError(ResponseStatusException.class, exception -> log.error("Pessoa ja cadastrada"));

    return personRepository.findByTaxId(dto.getTaxId()) //
        .flatMap(r -> conflict) //
        .switchIfEmpty(createPerson) //
        .cast(Person.class);
  }

  public Flux<Person> findAll(Pageable pageable) {
    return personRepository.findAllByIdNotNull(pageable);
  }

  public Mono<Page<Person>> findAll1(Pageable pageable) {
    return personRepository.findAll(pageable);
  }

}
