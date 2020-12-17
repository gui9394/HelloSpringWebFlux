package com.gui9394.person.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gui9394.address.model.Address;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Person {

  @Id
  private UUID id;

  private String firstName;

  private String lastName;

  private String taxId;

  private LocalDate dateOfBirth;

  private Address address;

  @CreatedDate
  private LocalDateTime created;

  public static Person create(//
      String firstName, //
      String lastName, //
      String taxId, //
      LocalDate dateOfBirth, //
      Address address //
  ) {
    return Person.builder() //
        .id(UUID.randomUUID()) //
        .firstName(firstName) //
        .lastName(lastName) //
        .taxId(taxId) //
        .dateOfBirth(dateOfBirth) //
        .address(address) //
        .build();
  }

}
