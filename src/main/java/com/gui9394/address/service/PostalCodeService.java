package com.gui9394.address.service;

import com.gui9394.address.dto.PostalCodeAddressDto;

public interface PostalCodeService extends AddressProvider<PostalCodeAddressDto> {

  @Override
  default Class<PostalCodeAddressDto> getProviderType() {
    return PostalCodeAddressDto.class;
  }

}
