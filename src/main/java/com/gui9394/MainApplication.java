package com.gui9394;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableReactiveMongoAuditing(auditorAwareRef = "auditorProvider")
@EnableReactiveMongoRepositories
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  public ReactivePageableHandlerMethodArgumentResolver a() {
    return new ReactivePageableHandlerMethodArgumentResolver();
  }

  @Bean("auditorProvider")
  public ReactiveAuditorAware<String> auditorProvider() {
    return () -> Mono.just("Teste");
  }

}
