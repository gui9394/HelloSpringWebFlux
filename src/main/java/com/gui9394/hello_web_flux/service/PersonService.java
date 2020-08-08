package com.gui9394.hello_web_flux.service;

import java.util.UUID;

import javax.validation.Valid;

import com.gui9394.hello_web_flux.model.Person;
import com.gui9394.hello_web_flux.repository.PersonRepository;
import com.gui9394.hello_web_flux.validation.OnUpdate;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class PersonService {

  private final PersonRepository repository;

  public Mono<Person> create(@Valid Person person) {
    log.info("Criando novo registro de pessoa");

    person.setId(UUID.randomUUID());

    return repository.save(person);
  }

  public Mono<Person> update(@Valid @Validated(OnUpdate.class) Person person) {
    log.info("Atualizando o registro {} de pessoa", person.getId());

    return this.findById(person.getId())
        .flatMap(p -> repository.save(person));
  }

  public Mono<Person> findById(UUID id) {
    log.info("Buscando o registro {} de pessoa", id);

    return repository.findById(id);
  }

  public Flux<Person> findAll() {
    log.info("Buscando todos os registros de pessoa");

    return repository.findAll();
  }

  public Mono<Person> deleteById(UUID id) {
    log.info("Deletando o registro {} de pessoa", id);

    return this.findById(id)
        .flatMap(p -> repository.delete(p)
            .then(Mono.just(p)));
  }

}