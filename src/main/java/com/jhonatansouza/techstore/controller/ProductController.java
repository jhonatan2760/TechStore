package com.jhonatansouza.techstore.controller;

import com.jhonatansouza.techstore.controller.request.ProductRequest;
import com.jhonatansouza.techstore.controller.response.ErrorResponse;
import com.jhonatansouza.techstore.model.ProductItem;
import com.jhonatansouza.techstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/tech-store/api/v1")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public Mono<ResponseEntity<Object>> createProduct(@RequestBody ProductRequest productRequest, UriComponentsBuilder uriComponentsBuilder){
        return this.productService.createItem(productRequest.toItem())
                .doOnSuccess(success -> {
                  this.logger.info("Product created with success - [{}]", productRequest.toString());
                }).doOnError(err -> {
                  this.logger.error("Fail to create product - [{}]", err.toString());
                }).map(item -> {
                    URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(item.getUuid()).toUri();
                    return ResponseEntity.created(uri).build();
                }).onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Unable to create the product, contact the sysAdmin")));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity> getProductById(@PathVariable String id){
        return this.productService.findById(id)
                .doOnSuccess(success -> this.logger.info("Product found w/ success [{}]", success.get().getUuid()))
                .doOnError(error -> this.logger.error("Failed to find product w/ id [{}]", id))
                .map(item -> item.isPresent() ? ResponseEntity.ok(item) : ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public Flux<ProductItem> getAll() throws ExecutionException, InterruptedException {
        return this.productService.getAll()
                .doOnError(err -> this.logger.error("Unable to find all products."))
                .doOnComplete(() -> this.logger.info("Finded all products with success."));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<?>> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
        return this.productService.findById(id)
                //.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .flatMap(product -> {
                    ProductItem productItem = productRequest.toItem();
                    productItem.setUuid(id);
                    return this.productService.update(productItem);
                }).flatMap(it -> Mono.just(ResponseEntity.ok(it));
    }

}
