package com.gui9394.address.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@ToString
public class Address {

  private String postalCode;

  private String street;

  private String number;

  private String district;

  private String city;

  private BrazilianState state;

}
