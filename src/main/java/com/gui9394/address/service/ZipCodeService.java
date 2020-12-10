package com.gui9394.address.service;

import com.gui9394.address.dto.ZipCodeAddressDto;
import com.gui9394.address.model.Address;

import reactor.core.publisher.Mono;

public interface ZipCodeService {

    Mono<Address> getAddress(ZipCodeAddressDto dto);

}
