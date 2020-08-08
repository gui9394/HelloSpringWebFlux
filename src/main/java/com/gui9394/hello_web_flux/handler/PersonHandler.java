package com.gui9394.hello_web_flux.handler;

import java.util.UUID;

import com.gui9394.hello_web_flux.model.Person;
import com.gui9394.hello_web_flux.service.PersonService;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PersonHandler {

  private PersonService personService;
  private ConversionService conversionService;

  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(Person.class)
        .flatMap(personService::create)
        .flatMap(p -> ServerResponse.ok().bodyValue(p));
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    return request.bodyToMono(Person.class)
        .flatMap(personService::update)
        .flatMap(p -> ServerResponse.ok().bodyValue(p))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> findById(ServerRequest request) {
    var id = conversionService.convert(request.pathVariable("id"), UUID.class);

    return personService.findById(id)
        .flatMap(p -> ServerResponse.ok().bodyValue(p))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok().body(personService.findAll(), Person.class);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    var id = conversionService.convert(request.pathVariable("id"), UUID.class);

    return personService.deleteById(id)
        .flatMap(p -> ServerResponse.noContent().build())
        .switchIfEmpty(ServerResponse.notFound().build());
  }

}