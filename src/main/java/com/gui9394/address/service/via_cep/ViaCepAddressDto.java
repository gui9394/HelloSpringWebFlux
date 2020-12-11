package com.gui9394.address.service.via_cep;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class ViaCepAddressDto {

  @JsonProperty("cep")
  private String zipCode;

  @JsonProperty("logradouro")
  private String street;

  @JsonProperty("bairro")
  private String district;

  @JsonProperty("localidade")
  private String city;

  @JsonProperty("uf")
  private String state;

}
