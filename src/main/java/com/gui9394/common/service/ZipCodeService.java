package com.gui9394.common.service;

import com.gui9394.common.model.Address;

import reactor.core.publisher.Mono;

public interface ZipCodeService {

    Mono<Address> getAddress(String zipCode);

}
