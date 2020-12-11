package com.gui9394.address.service.via_cep;

import java.util.function.Function;

import com.gui9394.address.dto.PostalCodeAddressDto;
import com.gui9394.address.model.Address;
import com.gui9394.address.service.PostalCodeService;

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
public class ViaCepPostalCodeServiceImpl implements PostalCodeService {

  private final WebClient webClient = WebClient.builder().build();

  @Override
  public Mono<Address> getAddress(PostalCodeAddressDto dto) {
    return webClient //
        .get() //
        .uri("viacep.com.br/ws/{zipCode}/json/", dto.getPostalCode()) //
        .accept(MediaType.APPLICATION_JSON) //
        .retrieve() //
        .bodyToMono(ViaCepAddressDto.class) //
        .map(mapToAddress(dto.getNumber())) //
        .doOnNext(a -> log.debug("Endereco {} encontrado a partir do CEP", a)) //
        .doOnError( //
            WebClientResponseException.class,
            e -> log.error("Erro ao buscar endereco pelo CEP response {}", e.getResponseBodyAsString()));
  }

  private Function<ViaCepAddressDto, Address> mapToAddress(String number) {
    return viaCepAddress -> Address.builder() //
        .postalCode(viaCepAddress.getPostalCode()) //
        .street(viaCepAddress.getStreet()) //
        .number(number) //
        .district(viaCepAddress.getDistrict()) //
        .city(viaCepAddress.getCity()) //
        .state(viaCepAddress.getState()) //
        .build();
  }

}
