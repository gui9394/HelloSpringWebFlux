package com.gui9394.address.service.impl;

import static java.util.stream.Collectors.toConcurrentMap;

import java.util.Collection;
import java.util.Map;

import com.gui9394.address.dto.AddressDto;
import com.gui9394.address.model.Address;
import com.gui9394.address.service.AddressService;
import com.gui9394.address.service.AddressServiceStrategy;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.ToString;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
@ToString
public class AddressServiceImpl implements AddressService {

  private final Map<Class<AddressDto>, AddressServiceStrategy<AddressDto>> providers;

  public AddressServiceImpl(Collection<AddressServiceStrategy<? extends AddressDto>> providers) {
    this.providers = providers.stream() //
        .map(AddressServiceStrategy.class::cast) //
        .map(p -> Tuples.of(p.getStrategyType(), p)) //
        .collect(toConcurrentMap(Tuple2::getT1, Tuple2::getT2));
  }

  public <T extends AddressDto> Mono<Address> getAddress(T dto) {
    return Mono.justOrEmpty(providers.get(dto.getClass())) //
        .flatMap(s -> s.getAddress(dto)) //
        .switchIfEmpty(
            Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Tipo do endereco desconhecido")));
  }

}
