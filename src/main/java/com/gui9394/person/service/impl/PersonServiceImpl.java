package com.gui9394.person.service.impl;

import com.gui9394.common.service.ZipCodeService;
import com.gui9394.person.dto.CreatePersonDto;
import com.gui9394.person.model.Person;
import com.gui9394.person.repository.PersonRepository;
import com.gui9394.person.service.PersonService;

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

    private final ZipCodeService zipCodeService;

    private final PersonRepository personRepository;

    public Mono<Person> create(CreatePersonDto dto) {
        final var createNewPerson = zipCodeService.getAddress(dto.getZipCode()) //
                .flatMap(address -> personRepository.save( //
                        Person.create( //
                                dto.getFirstName(), //
                                dto.getLastName(), //
                                dto.getTaxId(), //
                                dto.getDateOfBirth(), //
                                address))) //
                .doOnNext(person -> log.debug("Nova pessoa cadastrada com sucesso {}", person));

        final var errorConflict = Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Pessoa ja cadastrada"))
                .doOnError(ResponseStatusException.class, exception -> log.error("Pessoa ja cadastrada"));

        return personRepository.findByTaxId(dto.getTaxId()) //
                .flatMap(r -> errorConflict) //
                .doOnNext(p -> log.debug("Nao existe cadastro de pessoa com {}", dto.getTaxId())) //
                .switchIfEmpty(createNewPerson) //
                .cast(Person.class);
    }

    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

}
