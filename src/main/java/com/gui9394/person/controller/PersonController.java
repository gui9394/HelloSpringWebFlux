package com.gui9394.person.controller;

import javax.validation.Valid;

import com.gui9394.person.dto.CreatePersonDto;
import com.gui9394.person.model.Person;
import com.gui9394.person.service.PersonService;
import com.gui9394.util.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class PersonController {

  private final PersonService service;

  @PostMapping
  public Mono<Person> create(@RequestBody @Valid CreatePersonDto dto) {
    return service.create(dto);
  }

  @GetMapping
  public Mono<Page<Person>> findAllPaged(Pageable pageable) {
    return service.findAll1(pageable);
  }

}
