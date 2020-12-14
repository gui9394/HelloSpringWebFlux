package com.gui9394.address.service.impl.open_street_map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gui9394.address.model.BrazilianState;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenStreetMapResponseDto {

  private OpenStreetMapAddressDto address;

  @Getter
  @ToString
  public static class OpenStreetMapAddressDto {

    @JsonProperty("postcode")
    private String postalcode;

    @JsonProperty("road")
    private String street;

    @JsonProperty("suburb")
    private String district;

    @JsonProperty("city")
    private String city;

    private BrazilianState state;

    @JsonProperty("state")
    public void setState(String state) {
      this.state = BrazilianState.valueOfName(state);
    }

  }

}
