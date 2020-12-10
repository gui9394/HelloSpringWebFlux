package com.gui9394.via_cep.service.impl;

import java.time.Duration;

import com.gui9394.common.model.Address;
import com.gui9394.common.service.ZipCodeService;
import com.gui9394.via_cep.dto.ViaCepAddressDto;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ViaCepZipCodeServiceImpl implements ZipCodeService {

    private final WebClient webClient = WebClient.builder().build();

    public Mono<Address> getAddress(String zipCode) {
        return webClient //
                .get() //
                .uri("viacep.com.br/ws/{zipCode}/json/", zipCode) //
                .accept(MediaType.APPLICATION_JSON) //
                .retrieve() //
                .bodyToMono(ViaCepAddressDto.class) //
                .map(ViaCepAddressDto::toEntity) //
                .delaySubscription(Duration.ofSeconds(5)) //
                .doOnNext(a -> log.debug("Endereco encontrado {}", a)) //
                .doOnError( //
                        WebClientResponseException.class,
                        e -> log.error("Erro ao buscar o cep response {}", e.getResponseBodyAsString()));
    }

}
