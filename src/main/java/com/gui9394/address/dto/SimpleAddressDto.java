package com.gui9394.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SimpleAddressDto implements AddressDto {

  @Pattern(regexp = "^\\d{8}$")
  private String zipCode;

  @NotBlank
  private String street;

  @NotBlank
  private String number;

  @NotBlank
  private String district;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

}
