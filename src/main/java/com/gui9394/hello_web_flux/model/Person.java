package com.gui9394.hello_web_flux.model;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@ToString
public class Person {

  @MongoId
  @Setter
  private UUID id;

  private String firstName;

  private String lastName;

}