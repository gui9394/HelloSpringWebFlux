package com.gui9394.address.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( //
        use = JsonTypeInfo.Id.NAME, //
        include = JsonTypeInfo.As.PROPERTY, //
        property = "type")
@JsonSubTypes({ //
        @Type(value = ZipCodeAddressDto.class, name = "zipCode"), //
})
public interface AddressAbstractDto {
}
