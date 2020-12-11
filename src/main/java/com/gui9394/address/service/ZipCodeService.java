package com.gui9394.address.service;

import com.gui9394.address.dto.ZipCodeAddressDto;

public interface ZipCodeService extends AddressProvider<ZipCodeAddressDto> {

  @Override
  default Class<ZipCodeAddressDto> getProviderType() {
    return ZipCodeAddressDto.class;
  }

}
