package com.gui9394.hello_web_flux.handler;

import java.util.UUID;

import com.gui9394.hello_web_flux.model.Person;
import com.gui9394.hello_web_flux.repository.PersonRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PersonHandler {

  private final PersonRepository repository;

  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(Person.class)
        .map(p -> {
          p.setId(UUID.randomUUID());

          return p;
        })
        .flatMap(repository::save)
        .flatMap(p -> ServerResponse.ok().bodyValue(p));
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    return request.bodyToMono(Person.class)
        .flatMap(repository::save)
        .flatMap(p -> ServerResponse.ok().bodyValue(p));
  }

  public Mono<ServerResponse> findById(ServerRequest request) {
    try {
      UUID id = UUID.fromString(request.pathVariable("id"));

      return repository.findById(id)
          .flatMap(p -> ServerResponse.ok().bodyValue(p))
          .switchIfEmpty(ServerResponse.notFound().build());
    } catch (IllegalArgumentException e) {
      return ServerResponse.badRequest().build();
    }
  }

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok().body(repository.findAll(), Person.class);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    try {
      UUID id = UUID.fromString(request.pathVariable("id"));

      return repository.deleteById(id)
          .flatMap(p -> ServerResponse.ok().build());
    } catch (IllegalArgumentException e) {
      return ServerResponse.badRequest().build();
    }
  }

}