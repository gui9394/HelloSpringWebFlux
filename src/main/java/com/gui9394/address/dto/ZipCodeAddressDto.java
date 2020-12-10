package com.gui9394.address.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ZipCodeAddressDto implements AddressAbstractDto {

    @Pattern(regexp = "^\\d$")
    @NotBlank
    private String zipCode;

    @NotBlank
    private String number;

}
