package com.gui9394.address.service;

import com.gui9394.address.dto.CoordinatesAddressDto;

public interface CoordinatesAddressService extends AddressServiceStrategy<CoordinatesAddressDto> {

  @Override
  default Class<CoordinatesAddressDto> getStrategyType() {
    return CoordinatesAddressDto.class;
  }

}
