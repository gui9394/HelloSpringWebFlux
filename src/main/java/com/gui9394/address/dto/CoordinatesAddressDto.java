package com.gui9394.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CoordinatesAddressDto implements AddressDto {

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotBlank
  private String number;

}
