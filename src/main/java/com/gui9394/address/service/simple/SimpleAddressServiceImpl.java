package com.gui9394.address.service.simple;

import com.gui9394.address.dto.SimpleAddressDto;
import com.gui9394.address.model.Address;
import com.gui9394.address.service.SimpleAddressService;

import org.springframework.stereotype.Service;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@ToString
public class SimpleAddressServiceImpl implements SimpleAddressService {

  @Override
  public Mono<Address> getAddress(SimpleAddressDto dto) {
    log.info("Convertendo SimpleAddressDto para Address");

    return Mono.just( //
        Address.builder() //
            .zipCode(dto.getZipCode()) //
            .street(dto.getStreet()) //
            .number(dto.getNumber()) //
            .district(dto.getDistrict()) //
            .city(dto.getCity()) //
            .state(dto.getState()) //
            .build());
  }

}
