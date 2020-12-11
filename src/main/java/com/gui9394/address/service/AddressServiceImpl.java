package com.gui9394.address.service;

import static java.util.stream.Collectors.toConcurrentMap;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

import com.gui9394.address.dto.AddressDto;
import com.gui9394.address.model.Address;

import org.springframework.stereotype.Service;

import lombok.ToString;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
@ToString
public class AddressServiceImpl implements AddressService {

  private final ConcurrentMap<Class<AddressDto>, AddressProvider<AddressDto>> providers;

  @SuppressWarnings("unchecked")
  public AddressServiceImpl(Collection<AddressProvider<? extends AddressDto>> providers) {
    this.providers = providers.stream() //
        .map(p -> (AddressProvider<AddressDto>) p) //
        .map(p -> Tuples.of(p.getProviderType(), p)) //
        .collect(toConcurrentMap(Tuple2::getT1, Tuple2::getT2));
  }

  @Override
  public Mono<Address> getAddress(AddressDto dto) {
    return providers.get(dto.getClass()).getAddress(dto);
  }

}
