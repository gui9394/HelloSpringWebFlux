package com.gui9394.address.service.via_cep;

import java.time.Duration;
import java.util.function.Function;

import com.gui9394.address.dto.ZipCodeAddressDto;
import com.gui9394.address.model.Address;
import com.gui9394.address.service.ZipCodeService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
class ViaCepZipCodeServiceImpl implements ZipCodeService {

    private final WebClient webClient = WebClient.builder().build();

    @Override
    public Mono<Address> getAddress(ZipCodeAddressDto dto) {
        return webClient //
                .get() //
                .uri("viacep.com.br/ws/{zipCode}/json/", dto.getZipCode()) //
                .accept(MediaType.APPLICATION_JSON) //
                .retrieve() //
                .bodyToMono(ViaCepAddressDto.class) //
                .map(mapToAddress(dto.getNumber())) //
                .delaySubscription(Duration.ofSeconds(5)) //
                .doOnNext(a -> log.debug("Endereco encontrado {}", a)) //
                .doOnError( //
                        WebClientResponseException.class,
                        e -> log.error("Erro ao buscar o cep response {}", e.getResponseBodyAsString()));
    }

    private Function<ViaCepAddressDto, Address> mapToAddress(String number) {
        return viaCepAddress -> Address.builder() //
                .zipCode(viaCepAddress.getZipCode().replaceAll("\\D", "")) //
                .street(viaCepAddress.getStreet()) //
                .number(number) //
                .district(viaCepAddress.getDistrict()) //
                .city(viaCepAddress.getCity()) //
                .state(viaCepAddress.getState()) //
                .build();
    }

}
