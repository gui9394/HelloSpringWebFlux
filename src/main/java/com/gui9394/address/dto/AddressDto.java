package com.gui9394.address.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo( //
    use = Id.NAME, //
    include = As.PROPERTY, //
    property = "type", //
    defaultImpl = SimpleAddressDto.class)
@JsonSubTypes({ //
    @Type(value = SimpleAddressDto.class, name = "simple"), //
    @Type(value = PostalCodeAddressDto.class, name = "postalCode"), //
    @Type(value = CoordinatesAddressDto.class, name = "coordinates") })
public interface AddressDto {
}
