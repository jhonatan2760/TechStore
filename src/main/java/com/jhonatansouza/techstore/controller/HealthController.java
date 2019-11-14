package com.jhonatansouza.techstore.controller;

import com.jhonatansouza.techstore.configuration.DynamoDbAsync;
import com.jhonatansouza.techstore.controller.response.HealthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @Autowired
    private DynamoDbAsync dynamoDbAsync;

    @GetMapping("/health")
    public Mono<ResponseEntity> health() throws ExecutionException, InterruptedException {
        /*this.dynamoDbAsync.getDynamoAsyncClient().createTable(CreateTableRequest.builder()
                .tableName("products")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("uuid").keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder().attributeName("uuid").attributeType(ScalarAttributeType.S).build())
                .provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(10l).writeCapacityUnits(10l).build())
                .build()).get();*/
        return Mono.just(new HealthResponse()).map(ResponseEntity::ok);
    }

}
