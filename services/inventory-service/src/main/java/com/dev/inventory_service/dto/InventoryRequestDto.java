package com.dev.inventory_service.dto;

public record InventoryRequestDto(
        String skuCode,
        Integer quantity
) {
}
