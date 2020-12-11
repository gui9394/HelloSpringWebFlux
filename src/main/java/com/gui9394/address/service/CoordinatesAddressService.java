package com.gui9394.address.service;

import com.gui9394.address.dto.CoordinatesAddressDto;

public interface CoordinatesAddressService extends AddressProvider<CoordinatesAddressDto> {

  @Override
  default Class<CoordinatesAddressDto> getProviderType() {
    return CoordinatesAddressDto.class;
  }

}
