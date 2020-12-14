package com.gui9394.address.service.impl.simple;

import com.gui9394.address.dto.SimpleAddressDto;
import com.gui9394.address.model.Address;
import com.gui9394.address.service.SimpleAddressService;

import org.springframework.stereotype.Service;

import lombok.ToString;
import reactor.core.publisher.Mono;

@Service
@ToString
public class SimpleAddressServiceImpl implements SimpleAddressService {

  @Override
  public Mono<Address> getAddress(SimpleAddressDto dto) {
    return Mono.just( //
        Address.builder() //
            .postalCode(dto.getPostalCode()) //
            .street(dto.getStreet()) //
            .number(dto.getNumber()) //
            .district(dto.getDistrict()) //
            .city(dto.getCity()) //
            .state(dto.getState()) //
            .build());
  }

}
