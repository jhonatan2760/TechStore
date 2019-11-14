package com.jhonatansouza.techstore.controller;

import com.jhonatansouza.techstore.controller.request.ProductRequest;
import com.jhonatansouza.techstore.model.ProductItem;
import com.jhonatansouza.techstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/tech-store/api/v1")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public Mono<ResponseEntity<?>> createProduct(@RequestBody ProductRequest productRequest){

        return this.productService.createItem(productRequest.toItem())
                .map(ResponseEntity::ok);

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity> getProductById(@PathVariable String id){
        return this.productService.findById(id).map(ResponseEntity::ok);
    }

    @GetMapping("/")
    public Flux<ProductItem> getAll() throws ExecutionException, InterruptedException {
        return this.productService.getAll();
    }
}
