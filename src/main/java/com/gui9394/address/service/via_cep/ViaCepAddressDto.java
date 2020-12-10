package com.gui9394.address.service.via_cep;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gui9394.address.model.Address;

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

    public Address toEntity(String number) {
        return Address.builder() //
                .zipCode(zipCode.replaceAll("\\D", "")) //
                .street(street) //
                .number(number) //
                .district(district) //
                .city(city) //
                .state(state) //
                .build();
    }

}
