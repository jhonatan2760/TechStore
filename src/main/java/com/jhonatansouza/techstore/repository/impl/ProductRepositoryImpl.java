package com.jhonatansouza.techstore.repository.impl;

import com.jhonatansouza.techstore.model.ProductItem;
import com.jhonatansouza.techstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private DynamoDbAsyncClient dynamoDbAsyncClient;
    private final String tableName = "products";

    @Override
    public Mono<ProductItem> save(ProductItem productItem) {
        return Mono.fromFuture(this.dynamoDbAsyncClient
                .putItem(PutItemRequest.builder()
                        .tableName(this.tableName)
                        .item(productItem.mapper())
                        .build()).thenApplyAsync(PutItemResponse::attributes)
                        .thenApply(product -> productItem));
    }

    @Override
    public Mono<Optional<ProductItem>> findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(id).build());
        return Mono.fromFuture(this.dynamoDbAsyncClient.getItem(GetItemRequest.builder().tableName(this.tableName).key(key).build())
                    .thenApplyAsync(GetItemResponse::item)
                    .thenApply(item -> item.isEmpty() ? null : new ProductItem(item)).thenApply(Optional::ofNullable));
    }

    @Override
    public Flux<ProductItem> getAll() throws ExecutionException, InterruptedException {
        return Mono.fromFuture(this.dynamoDbAsyncClient.scan(ScanRequest.builder().tableName(this.tableName).build())
                                    .thenApplyAsync(ScanResponse::items)
                                    .thenApply(item -> item.stream()
                                            .map(it -> new ProductItem(it))
                                            .collect(Collectors.toList()))).flatMapMany(br -> Flux.fromIterable(br));
    }

    @Override
    public Mono<ProductItem> update(ProductItem productItem) {
        return Mono.fromFuture(this.dynamoDbAsyncClient.updateItem(UpdateItemRequest.builder()
                .key(productItem.keyMapper())
                .tableName(this.tableName)
                .attributeUpdates(productItem.update()).build())
                .thenApplyAsync(UpdateItemResponse::hashCode)
                .thenApply(p -> productItem));
    }

    @Override
    public Mono<Void> delete(ProductItem productItem) {
        return Mono.fromFuture(this.dynamoDbAsyncClient
                .deleteItem(DeleteItemRequest.builder().tableName(this.tableName).key(productItem.keyMapper()).build())).then();
    }


}
