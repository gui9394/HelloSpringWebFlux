package com.gui9394.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostalCodeAddressDto implements AddressDto {

  @Pattern(regexp = "^\\d{8}$")
  @NotBlank
  private String postalCode;

  @NotBlank
  private String number;

}
