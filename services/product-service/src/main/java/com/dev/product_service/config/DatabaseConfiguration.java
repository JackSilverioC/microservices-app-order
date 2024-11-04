package com.dev.product_service.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public class DatabaseConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(mongoUri);
    }

    @Override
    protected String getDatabaseName() {
        return "product-service";
    }

    @Bean
    public MongoTemplate reactiveMongoTemplate(){
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
