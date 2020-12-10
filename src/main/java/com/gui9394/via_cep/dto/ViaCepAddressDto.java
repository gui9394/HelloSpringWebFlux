package com.gui9394.via_cep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gui9394.common.model.Address;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ViaCepAddressDto {

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

    public Address toEntity() {
        return Address.builder() //
                .zipCode(zipCode.replaceAll("\\D", "")) //
                .street(street) //
                .district(district) //
                .city(city) //
                .state(state) //
                .build();
    }

}
