package com.gui9394.hello_web_flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.gui9394.hello_web_flux.handler.PersonHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Routes {

  @Bean
  public RouterFunction<ServerResponse> personRoutes(PersonHandler personHandler) {
    return nest(path("person"),
        route(POST(""), personHandler::create)
        .andRoute(PUT(""), personHandler::update)
        .andRoute(GET("{id}"), personHandler::findById)
        .andRoute(GET(""), personHandler::findAll)
        .andRoute(DELETE("{id}"), personHandler::delete));
  }

}