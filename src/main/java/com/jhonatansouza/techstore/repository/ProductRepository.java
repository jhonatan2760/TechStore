package com.jhonatansouza.techstore.repository;

import com.jhonatansouza.techstore.model.ProductItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface ProductRepository {

    Mono<ProductItem> save(ProductItem productItem);
    Mono<ProductItem> findById(String id);
    Flux<ProductItem> getAll() throws ExecutionException, InterruptedException;
}
