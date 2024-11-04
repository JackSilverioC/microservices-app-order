package com.dev.order_service.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Optional;

@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventories")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    Optional<Boolean> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default Optional<Boolean> fallbackMethod(String skuCode, Integer quantity, Throwable throwable){
        log.info("Cannot get stock check for skucode: {}, failure reason: {}", skuCode, throwable.getMessage());
        return Optional.of(false);
    }
}
