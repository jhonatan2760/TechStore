package com.jhonatansouza.techstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
public class DynamoDbAsync {

    @Bean
    public DynamoDbAsyncClient getDynamoAsyncClient(){
        return DynamoDbAsyncClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("fakeMyKeyId","fakeSecretAccessKey")))
                .endpointOverride(URI.create("http://localhost:8000")).build();
    }

}
