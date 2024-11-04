package com.dev.inventory_service.kafka;

public record InventoryKafka(
        String skuCode,
        Integer quantity
) {
}
