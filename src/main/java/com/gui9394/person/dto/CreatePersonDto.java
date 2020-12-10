package com.gui9394.person.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreatePersonDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^\\d{11}$")
    @NotBlank
    private String taxId;

    @PastOrPresent
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^\\d{8}$")
    @NotBlank
    private String zipCode;

}