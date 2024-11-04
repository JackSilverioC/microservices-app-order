package com.dev.order_service.kafka;

public record InventoryKafka(
        String skuCode,
        Integer quantity
) {
}
