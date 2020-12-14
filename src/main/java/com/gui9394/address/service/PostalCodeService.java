package com.gui9394.address.service;

import com.gui9394.address.dto.PostalCodeAddressDto;

public interface PostalCodeService extends AddressServiceStrategy<PostalCodeAddressDto> {

  @Override
  default Class<PostalCodeAddressDto> getStrategyType() {
    return PostalCodeAddressDto.class;
  }

}
