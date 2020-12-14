package com.gui9394.address.service;

import com.gui9394.address.dto.AddressDto;
import com.gui9394.address.model.Address;

import reactor.core.publisher.Mono;

public interface AddressService {

  <T extends AddressDto> Mono<Address> getAddress(T dto);

}
