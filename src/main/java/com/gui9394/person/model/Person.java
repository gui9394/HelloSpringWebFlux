package com.gui9394.person.model;

import java.time.LocalDate;
import java.util.UUID;

import com.gui9394.common.model.Address;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Person {

    @Id
    private UUID id = UUID.randomUUID();

    private String firstName;

    private String lastName;

    private String taxId;

    private LocalDate dateOfBirth;

    private Address address;

    public static Person create(//
            String firstName, //
            String lastName, //
            String taxId, //
            LocalDate dateOfBirth, //
            Address address //
    ) {
        return new Person(UUID.randomUUID(), firstName, lastName, taxId, dateOfBirth, address);
    }


}
