package com.gui9394.address.service;

import com.gui9394.address.dto.SimpleAddressDto;

public interface SimpleAddressService extends AddressServiceStrategy<SimpleAddressDto> {

  @Override
  default Class<SimpleAddressDto> getStrategyType() {
    return SimpleAddressDto.class;
  }

}
