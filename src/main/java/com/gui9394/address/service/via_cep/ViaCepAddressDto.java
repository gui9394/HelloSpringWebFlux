package com.gui9394.address.service.via_cep;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gui9394.address.model.BrazilianState;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class ViaCepAddressDto {

  private String postalCode;
  
  @JsonProperty("logradouro")
  private String street;
  
  @JsonProperty("bairro")
  private String district;
  
  @JsonProperty("localidade")
  private String city;
  
  @JsonProperty("uf")
  private BrazilianState state;
  
  @JsonProperty("cep")
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode.replaceAll("\\D", "");
  }

}
