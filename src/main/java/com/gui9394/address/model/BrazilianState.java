package com.gui9394.address.model;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum BrazilianState {

  AC("Acre"), //
  AL("Alagoas"), //
  AM("Amazonas"), //
  AP("Amapá "), //
  BA("Bahia"), //
  CE("Ceará"), //
  DF("Distrito Federal"), //
  ES("Espírito Santo"), //
  GO("Goiás"), //
  MA("Maranhão"), //
  MT("Mato Grosso"), //
  MS("Mato Grosso do Sul"), //
  MG("Minas Gerais"), //
  PA("Pará"), //
  PB("Paraíba"), //
  PR("Paraná"), //
  PE("Pernambuco"), //
  PI("Piauí"), //
  RJ("Rio de Janeiro"), //
  RN("Rio Grande do Norte"), //
  RO("Rondônia"), //
  RS("Rio Grande do Sul"), //
  RR("Roraima"), //
  SC("Santa Catarina"), //
  SE("Sergipe"), //
  SP("São Paulo"), //
  TO("Tocantins");

  private String name;

  public static BrazilianState valueOfName(String name) {
    return Stream.of(values()) //
        .filter(state -> state.name.equals(name)) //
        .findFirst() //
        .orElse(null);
  }

}
