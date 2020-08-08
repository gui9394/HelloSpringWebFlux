package com.gui9394.hello_web_flux.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gui9394.hello_web_flux.validation.OnUpdate;

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
  @NotNull(groups = OnUpdate.class)
  private UUID id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

}