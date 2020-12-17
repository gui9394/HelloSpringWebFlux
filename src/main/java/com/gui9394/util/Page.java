package com.gui9394.util;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Page<T> {

  List<T> getContent();

  int getPageNumber();

  int getPageSize();

  long getTotalElements();

  int getTotalElementsOnPage();

  int getTotalPages();

  <U> Page<U> map(Function<? super T, ? extends U> converter);

  public static <T> Page<T> of(long totalElements, List<T> content, Pageable pageable) {
    return new PageImpl<>(totalElements, content, pageable);
  }

  public static <T> Mono<Page<T>> of(Mono<Long> totalElements, Flux<T> content, Pageable pageable) {
    return totalElements //
        .zipWith(content //
            .collectList() //
            .map(Collections::unmodifiableList)) //
        .map(tuple -> of(tuple.getT1(), tuple.getT2(), pageable));
  }

  public static class PageImpl<T> implements Page<T> {

    private long totalElements;

    private List<T> content;

    @JsonIgnore
    private Pageable pageable;

    public PageImpl(long totalElements, List<T> content, Pageable pageable) {
      Assert.notNull(totalElements, "Total de elementos nao pode ser nulo!");
      Assert.notNull(content, "Conteudo nao pode ser nulo!");
      Assert.notNull(pageable, "Pageable nao pode ser nulo!");

      this.totalElements = totalElements;
      this.content = content;
      this.pageable = pageable;
    }

    @Override
    public int getPageNumber() {
      return pageable.getPageNumber();
    }

    @Override
    public int getPageSize() {
      return pageable.getPageSize();
    }

    @Override
    public long getTotalElements() {
      return totalElements;
    }

    @Override
    public int getTotalElementsOnPage() {
      return getContent().size();
    }

    @Override
    public int getTotalPages() {
      return (getPageSize() == 0) //
          ? 1 //
          : (int) Math.ceil((double) getTotalElements() / (double) getPageSize());
    }

    @Override
    public List<T> getContent() {
      return content;
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> map) {
      Assert.notNull(map, "Funcao nao pode ser nula!");
      return new PageImpl<>(totalElements, content.stream().map(map::apply).collect(toList()), pageable);
    }

  }

}
