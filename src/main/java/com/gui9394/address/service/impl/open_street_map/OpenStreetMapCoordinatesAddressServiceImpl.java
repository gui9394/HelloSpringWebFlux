package com.gui9394.address.service.impl.open_street_map;

import java.util.function.Function;

import com.gui9394.address.dto.CoordinatesAddressDto;
import com.gui9394.address.model.Address;
import com.gui9394.address.service.CoordinatesAddressService;
import com.gui9394.address.service.impl.open_street_map.OpenStreetMapResponseDto.OpenStreetMapAddressDto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@ToString
public class OpenStreetMapCoordinatesAddressServiceImpl implements CoordinatesAddressService {

  private final WebClient webClient = WebClient.builder() //
      .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) //
      .build();

  @Override
  public Mono<Address> getAddress(CoordinatesAddressDto dto) {
    return webClient.get() //
        .uri(
            "https://nominatim.openstreetmap.org/reverse?format=json&zoom=18&addressdetails=1&lat={latitude}&lon={longitude}",
            dto.getLatitude(), //
            dto.getLongitude())
        .retrieve() //
        .bodyToMono(OpenStreetMapResponseDto.class) //
        .map(OpenStreetMapResponseDto::getAddress) //
        .map(mapToAddress(dto.getNumber())) //
        .doOnNext(a -> log.debug("Endereco {} encontrado a partir das coordenadas", a)) //
        .doOnError( //
            WebClientResponseException.class,
            e -> log.error("Erro ao buscar endereco pelas coordenadas response {}", e.getResponseBodyAsString()));
  }

  private Function<OpenStreetMapAddressDto, Address> mapToAddress(String number) {
    return openStreetMapAddress -> Address.builder() //
        .postalCode(openStreetMapAddress.getPostalcode()) //
        .street(openStreetMapAddress.getStreet()) //
        .number(number) //
        .district(openStreetMapAddress.getDistrict()) //
        .city(openStreetMapAddress.getCity()) //
        .state(openStreetMapAddress.getState()) //
        .build();
  }

}
