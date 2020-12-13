package com.gui9394.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.gui9394.address.model.BrazilianState;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SimpleAddressDto implements AddressDto {

  @Pattern(regexp = "^\\d{8}$")
  private String postalCode;

  @NotBlank
  private String street;

  @NotBlank
  private String number;

  @NotBlank
  private String district;

  @NotBlank
  private String city;

  @NotNull
  private BrazilianState state;

}
