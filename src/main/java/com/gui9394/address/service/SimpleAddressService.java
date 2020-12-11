package com.gui9394.address.service;

import com.gui9394.address.dto.SimpleAddressDto;

public interface SimpleAddressService extends AddressProvider<SimpleAddressDto> {

  @Override
  default Class<SimpleAddressDto> getProviderType() {
    return SimpleAddressDto.class;
  }

}
