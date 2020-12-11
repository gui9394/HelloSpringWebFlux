package com.gui9394.person.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.gui9394.address.dto.AddressDto;

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
  @NotNull
  private LocalDate dateOfBirth;

  @Valid
  @NotNull
  private AddressDto address;

}